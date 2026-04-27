<template>
  <div class="container">
    <h2>Tổng quan hệ thống</h2>

    <div class="dashboard-cards">
      <div class="card card-blue">
        <h3>Tổng số Thiết bị</h3>
        <h2>{{ tongSoThietBi }}</h2>
      </div>

      <div class="card card-green">
        <h3>Tổng số Nhân viên</h3>
        <h2>{{ tongSoNhanVien }}</h2>
      </div>
    </div>
  </div>
</template>

<script>
import thietbiService from '../services/thietbiService'
import employeeService from '../services/employeeService'

export default {
  data() {
    return {
      tongSoThietBi: 0,
      tongSoNhanVien: 0
    }
  },
  methods: {
    async taiDuLieu() {
      try {
        // Lấy dữ liệu thiết bị
        const ketQuaThietBi = await thietbiService.getAll()
        this.tongSoThietBi = ketQuaThietBi.data.length

        // Lấy dữ liệu nhân viên
        const ketQuaNhanVien = await employeeService.getAll()
        this.tongSoNhanVien = ketQuaNhanVien.data.length
      } catch (loi) {
        console.error("Lỗi khi tải dữ liệu Dashboard", loi)
      }
    }
  },
  mounted() {
    this.taiDuLieu()
  }
}
</script>

<style scoped>
.container { padding: 20px; max-width: 900px; margin: 0 auto; }
.dashboard-cards { display: flex; gap: 20px; margin-top: 20px; }

.card {
  flex: 1;
  padding: 30px;
  border-radius: 8px;
  text-align: center;
  color: white;
  border: 1px solid #444;
}

.card h3 { margin-bottom: 15px; font-weight: normal; }
.card h2 { font-size: 3rem; margin: 0; }

.card-blue { background-color: #007bff; }
.card-green { background-color: #28a745; }
</style>
