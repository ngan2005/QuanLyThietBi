<template>
  <div class="modal-overlay" v-if="hienThi" @click.self="$emit('dong')">
    <div class="modal-content premium-card">
      <h2>{{ duLieuBanDau ? 'Sửa nhân viên' : 'Thêm nhân viên mới' }}</h2>

      <form @submit.prevent="xuLyLuu">
        <div class="form-group">
          <label>Họ và Tên</label>
          <input v-model="form.tenNV" type="text" placeholder="Nhập họ tên..." required />
        </div>

        <div class="form-group">
          <label>Chức vụ</label>
          <input v-model="form.chucVu" type="text" placeholder="Nhập chức vụ..." required />
        </div>

        <div class="form-actions">
          <button type="button" class="btn-cancel" @click="$emit('dong')" :disabled="dangLuu">Hủy</button>
          <button type="submit" class="btn-primary" :disabled="dangLuu">
            {{ dangLuu ? 'Đang lưu...' : 'Lưu' }}
          </button>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    hienThi: Boolean,
    duLieuBanDau: Object,
    dangLuu: Boolean
  },
  data() {
    return {
      form: { tenNV: '', chucVu: '' }
    }
  },
  watch: {
    hienThi(giaTriMoi) {
      if (giaTriMoi) {
        if (this.duLieuBanDau) {
          this.form = { ...this.duLieuBanDau }
        } else {
          this.form = { tenNV: '', chucVu: '' }
        }
      }
    }
  },
  methods: {
    xuLyLuu() {
      this.$emit('guiDuLieu', { ...this.form })
    }
  }
}
</script>