<template>
  <div class="container">
    <h2>Quản lý Thiết bị</h2>

    <div class="toolbar">
      <input 
        v-model="tuKhoa" 
        @input="timKiem"
        placeholder="Tìm thiết bị theo tên..." 
        class="input-box"
      />
      <button class="btn-add" @click="moFormThem">Thêm Mới</button>
    </div>

    <!-- Form nhập liệu -->
    <div v-if="hienThiForm" class="form-box">
      <h3>{{ form.id ? 'Sửa Thiết Bị' : 'Thêm Thiết Bị' }}</h3>
      <div class="input-group">
        <input v-model="form.deviceTen" placeholder="Tên thiết bị..." class="input-box" />
        <input v-model="form.deviceLoai" placeholder="Loại..." class="input-box" />
        <input type="number" v-model="form.soLuong" placeholder="Số lượng..." class="input-box" />
        <select v-model="form.tinhTrang" class="input-box">
          <option :value="true">Sẵn sàng</option>
          <option :value="false">Bảo trì</option>
        </select>
        <button class="btn-save" @click="luuThietBi">Lưu</button>
        <button class="btn-cancel" @click="hienThiForm = false">Hủy</button>
      </div>
    </div>

    <!-- Bảng dữ liệu -->
    <table class="basic-table">
      <thead>
        <tr>
          <th>ID</th>
          <th>Tên Thiết Bị</th>
          <th>Loại</th>
          <th>Số lượng</th>
          <th>Tình trạng</th>
          <th>Hành động</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="tb in danhSachTB" :key="tb.id">
          <td>{{ tb.id }}</td>
          <td>{{ tb.deviceTen }}</td>
          <td>{{ tb.deviceLoai }}</td>
          <td>{{ tb.soLuong }}</td>
          <td>{{ tb.tinhTrang ? 'Sẵn sàng' : 'Bảo trì' }}</td>
          <td>
            <button class="btn-edit" @click="moFormSua(tb)">Sửa</button>
            <button class="btn-delete" @click="xoaThietBi(tb.id)">Xóa</button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import thietbiService from '../services/thietbiService'

export default {
  data() {
    return {
      danhSachTB: [],
      tuKhoa: '',
      hienThiForm: false,
      form: { id: null, deviceTen: '', deviceLoai: '', soLuong: 1, tinhTrang: true }
    }
  },
  methods: {
    async timKiem() {
      if (this.tuKhoa.trim() === '') {
        await this.taiDanhSach()
      } else {
        const ketQua = await thietbiService.search(this.tuKhoa)
        this.danhSachTB = ketQua.data
      }
    },
    async taiDanhSach() {
      const ketQua = await thietbiService.getAll()
      this.danhSachTB = ketQua.data
    },
    moFormThem() {
      this.form = { id: null, deviceTen: '', deviceLoai: '', soLuong: 1, tinhTrang: true }
      this.hienThiForm = true
    },
    moFormSua(tb) {
      this.form = { ...tb }
      this.hienThiForm = true
    },
    async luuThietBi() {
      if (!this.form.deviceTen) return alert("Vui lòng nhập tên thiết bị!")

      if (this.form.id) {
        await thietbiService.update(this.form.id, this.form)
      } else {
        await thietbiService.create(this.form)
      }
      
      this.hienThiForm = false
      this.taiDanhSach()
    },
    async xoaThietBi(id) {
      if (confirm("Bạn có chắc muốn xóa?")) {
        await thietbiService.delete(id)
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
.container { padding: 20px; max-width: 900px; margin: 0 auto; }
.toolbar { display: flex; gap: 10px; margin-bottom: 20px; }
.input-box { flex: 1; padding: 10px; border-radius: 5px; border: 1px solid #ccc; background: #fff; color: #333; }

.form-box { background: #f8f9fa; padding: 15px; border-radius: 5px; margin-bottom: 20px; border: 1px solid #ddd; }
.input-group { display: flex; gap: 10px; margin-top: 10px; flex-wrap: wrap; }

.basic-table { width: 100%; border-collapse: collapse; background: #fff; color: #333; }
.basic-table th, .basic-table td { border: 1px solid #ddd; padding: 12px; text-align: left; }
.basic-table th { background: #e9ecef; }

/* Nút bấm cơ bản */
button { padding: 8px 15px; border: none; border-radius: 4px; cursor: pointer; color: white; font-weight: bold; }
.btn-add { background: #28a745; }
.btn-save { background: #007bff; }
.btn-cancel { background: #6c757d; }
.btn-edit { background: #ffc107; color: black; margin-right: 5px; }
.btn-delete { background: #dc3545; }
</style>
