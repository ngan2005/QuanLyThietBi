<template>
  <div class="container">
    <h2>Quản lý Nhân viên</h2>

    <!-- Thanh công cụ -->
    <div class="toolbar">
      <input 
        v-model="tuKhoa" 
        placeholder="Nhập tên nhân viên cần tìm..." 
        @input="timKiem"
        class="input-box"
      />
      <button class="btn-add" @click="moFormThem">Thêm Mới</button>
    </div>

    <!-- Form nhập liệu (Ẩn/Hiện) -->
    <div v-if="hienThiForm" class="form-box">
      <h3>{{ form.id ? 'Sửa Nhân Viên' : 'Thêm Nhân Viên' }}</h3>
      <div class="input-group">
        <input v-model="form.tenNV" placeholder="Họ và tên..." class="input-box" />
        <input v-model="form.chucVu" placeholder="Chức vụ..." class="input-box" />
        <button class="btn-save" @click="luuNhanVien">Lưu</button>
        <button class="btn-cancel" @click="hienThiForm = false">Hủy</button>
      </div>
    </div>

    <!-- Form cấp thiết bị -->
    <div v-if="hienThiFormCapPhat" class="form-box assign-box">
      <h3>Cấp thiết bị cho: {{ nhanVienDuocChon?.tenNV }}</h3>
      <div class="input-group">
        <select v-model="formCapPhat.deviceId" class="input-box">
          <option value="" disabled>-- Chọn thiết bị --</option>
          <option v-for="tb in thietBiSanSang" :key="tb.id" :value="tb.id">
            {{ tb.deviceTen }} (Còn: {{ tb.soLuong }})
          </option>
        </select>
        <button class="btn-save" @click="capPhatThietBi">Xác nhận</button>
        <button class="btn-cancel" @click="hienThiFormCapPhat = false">Hủy</button>
      </div>
    </div>

    <!-- Bảng dữ liệu -->
    <table class="basic-table">
      <thead>
        <tr>
          <th>ID</th>
          <th>Họ và Tên</th>
          <th>Chức vụ</th>
          <th>Thiết bị đang mượn</th>
          <th>Hành động</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="nv in danhSachNV" :key="nv.id">
          <td>{{ nv.id }}</td>
          <td>{{ nv.tenNV }}</td>
          <td>{{ nv.chucVu }}</td>
          <td>
            <div v-if="layDanhSachThietBi(nv).length > 0">
              <span v-for="(tb, index) in layDanhSachThietBi(nv)" :key="index" class="device-badge">
                {{ tb.deviceTen || tb.ten || 'TB ID: ' + (tb.id || tb) }}
              </span>
            </div>
            <span v-else>Không có</span>
          </td>
          <td>
            <button class="btn-assign" @click="moFormCapPhat(nv)">Cấp thiết bị</button>
            <button class="btn-edit" @click="moFormSua(nv)">Sửa</button>
            <button class="btn-delete" @click="xoaNhanVien(nv.id)">Xóa</button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import employeeService from '../services/employeeService'
import thietbiService from '../services/thietbiService'

export default {
  data() {
    return {
      danhSachNV: [],
      tuKhoa: '',
      hienThiForm: false,
      form: { id: null, tenNV: '', chucVu: '' },
      
      // Dữ liệu cho cấp phát thiết bị
      hienThiFormCapPhat: false,
      nhanVienDuocChon: null,
      thietBiSanSang: [],
      formCapPhat: { empId: null, deviceId: '' }
    }
  },
  methods: {
    // 1. Tải danh sách
    async taiDanhSach() {
      try {
        const ketQua = await employeeService.getAll()
        this.danhSachNV = ketQua.data
      } catch (loi) {
        console.error("Lỗi khi tải danh sách NV:", loi)
      }
    },

    // 2. Tìm kiếm
    async timKiem() {
      try {
        if (this.tuKhoa.trim() === '') {
          await this.taiDanhSach()
        } else {
          const ketQua = await employeeService.search(this.tuKhoa)
          this.danhSachNV = ketQua.data
        }
      } catch (loi) {
        console.error("Lỗi khi tìm kiếm:", loi)
        this.danhSachNV = []
      }
    },

    // Hàm lấy danh sách thiết bị của NV
    layDanhSachThietBi(nv) {
      return nv.dsThietBi || nv.devices || nv.thietBis || []
    },

    // 3. Mở form thêm
    moFormThem() {
      this.form = { id: null, tenNV: '', chucVu: '' }
      this.hienThiForm = true
      this.hienThiFormCapPhat = false
    },

    // 4. Mở form sửa
    moFormSua(nv) {
      this.form = { id: nv.id, tenNV: nv.tenNV, chucVu: nv.chucVu }
      this.hienThiForm = true
      this.hienThiFormCapPhat = false
    },

    // Mở form cấp phát thiết bị
    async moFormCapPhat(nv) {
      const cacThietBi = this.layDanhSachThietBi(nv);
      if (cacThietBi.length >= 2) {
        alert("Nhân viên này đã được cấp tối đa 2 thiết bị!");
        return;
      }
      
      this.nhanVienDuocChon = nv;
      this.formCapPhat.empId = nv.id;
      this.formCapPhat.deviceId = '';
      
      try {
        const ketQua = await thietbiService.getAll();
        // Lọc các thiết bị còn số lượng và đang sẵn sàng
        this.thietBiSanSang = ketQua.data.filter(tb => tb.soLuong > 0 && tb.tinhTrang === true);
        
        if (this.thietBiSanSang.length === 0) {
          alert("Không còn thiết bị nào sẵn sàng trong kho!");
          return;
        }

        this.hienThiFormCapPhat = true;
        this.hienThiForm = false;
      } catch (loi) {
        console.error("Lỗi tải danh sách thiết bị:", loi);
        alert("Lỗi tải danh sách thiết bị");
      }
    },

    // Xử lý cấp phát
    async capPhatThietBi() {
      if (!this.formCapPhat.deviceId) {
        return alert("Vui lòng chọn một thiết bị!");
      }
      
      try {
        await employeeService.capPhat(this.formCapPhat.empId, this.formCapPhat.deviceId);
        alert("Cấp thiết bị thành công!");
        this.hienThiFormCapPhat = false;
        this.taiDanhSach(); // Tải lại danh sách
      } catch (loi) {
        alert("Cấp thiết bị thất bại: " + (loi.response?.data?.message || loi.message));
      }
    },

    // 5. Lưu (Thêm mới hoặc Cập nhật)
    async luuNhanVien() {
      if (!this.form.tenNV) return alert("Vui lòng nhập tên!")

      if (this.form.id) {
        await employeeService.update(this.form.id, this.form) // Sửa
      } else {
        await employeeService.create(this.form) // Thêm
      }
      
      this.hienThiForm = false
      this.taiDanhSach()
    },

    // 6. Xóa
    async xoaNhanVien(id) {
      if (confirm("Bạn có chắc muốn xóa?")) {
        await employeeService.delete(id)
        this.taiDanhSach()
      }
    }
  },
  mounted() {
    this.taiDanhSach()
  }
}
</script>

<style scoped>
.container { padding: 20px; max-width: 1000px; margin: 0 auto; }
.toolbar { display: flex; gap: 10px; margin-bottom: 20px; }
.input-box { flex: 1; padding: 10px; border-radius: 5px; border: 1px solid #ccc; background: #fff; color: #333; }
select.input-box { cursor: pointer; }

.form-box { background: #f8f9fa; padding: 15px; border-radius: 5px; margin-bottom: 20px; border: 1px solid #ddd; }
.assign-box { border-color: #17a2b8; background: #e0f7fa; color: #333; }
.assign-box h3 { color: #333; }
.input-group { display: flex; gap: 10px; margin-top: 10px; }

.basic-table { width: 100%; border-collapse: collapse; background: #fff; color: #333; }
.basic-table th, .basic-table td { border: 1px solid #ddd; padding: 12px; text-align: left; vertical-align: middle; }
.basic-table th { background: #e9ecef; }

/* Nút bấm cơ bản */
button { padding: 8px 15px; border: none; border-radius: 4px; cursor: pointer; color: white; font-weight: bold; }
.btn-add { background: #28a745; }
.btn-save { background: #007bff; }
.btn-cancel { background: #6c757d; }
.btn-edit { background: #ffc107; color: black; margin-right: 5px; }
.btn-delete { background: #dc3545; }
.btn-assign { background: #17a2b8; margin-right: 5px; }

.device-badge {
  display: inline-block;
  background-color: #6c757d;
  color: white;
  padding: 3px 8px;
  border-radius: 12px;
  font-size: 0.85em;
  margin-right: 5px;
  margin-bottom: 5px;
}
</style>
