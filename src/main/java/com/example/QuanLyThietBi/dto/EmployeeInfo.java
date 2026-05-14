package com.example.QuanLyThietBi.dto;

public class EmployeeInfo {
    private String id;
    private String code;
    private String tenNV;
    private String chucVu;

    public EmployeeInfo() {
    }

    public EmployeeInfo(String id, String code, String tenNV, String chucVu) {
        this.id = id;
        this.code = code;
        this.tenNV = tenNV;
        this.chucVu = chucVu;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

    public static EmployeeInfoBuilder builder() {
        return new EmployeeInfoBuilder();
    }

    public static class EmployeeInfoBuilder {
        private String id;
        private String code;
        private String tenNV;
        private String chucVu;

        public EmployeeInfoBuilder id(String id) {
            this.id = id;
            return this;
        }

        public EmployeeInfoBuilder code(String code) {
            this.code = code;
            return this;
        }

        public EmployeeInfoBuilder tenNV(String tenNV) {
            this.tenNV = tenNV;
            return this;
        }

        public EmployeeInfoBuilder chucVu(String chucVu) {
            this.chucVu = chucVu;
            return this;
        }

        public EmployeeInfo build() {
            return new EmployeeInfo(id, code, tenNV, chucVu);
        }
    }
}
