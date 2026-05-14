package com.example.QuanLyThietBi.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DeviceInfo {
    private String code;
    private String deviceTen;
    private String deviceLoai;
    private int soLuong;
    private LocalDate ngayMua;
    private Boolean tinhTrang;
    private Boolean isActive;
    private LocalDateTime createdAt;

    public DeviceInfo() {
    }

    public DeviceInfo(String code, String deviceTen, String deviceLoai, int soLuong, LocalDate ngayMua, Boolean tinhTrang, Boolean isActive, LocalDateTime createdAt) {
        this.code = code;
        this.deviceTen = deviceTen;
        this.deviceLoai = deviceLoai;
        this.soLuong = soLuong;
        this.ngayMua = ngayMua;
        this.tinhTrang = tinhTrang;
        this.isActive = isActive;
        this.createdAt = createdAt;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDeviceTen() {
        return deviceTen;
    }

    public void setDeviceTen(String deviceTen) {
        this.deviceTen = deviceTen;
    }

    public String getDeviceLoai() {
        return deviceLoai;
    }

    public void setDeviceLoai(String deviceLoai) {
        this.deviceLoai = deviceLoai;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public LocalDate getNgayMua() {
        return ngayMua;
    }

    public void setNgayMua(LocalDate ngayMua) {
        this.ngayMua = ngayMua;
    }

    public Boolean getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(Boolean tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    // Static builder for compatibility with existing code
    public static DeviceInfoBuilder builder() {
        return new DeviceInfoBuilder();
    }

    public static class DeviceInfoBuilder {
        private String code;
        private String deviceTen;
        private String deviceLoai;
        private int soLuong;
        private LocalDate ngayMua;
        private Boolean tinhTrang;
        private Boolean isActive;
        private LocalDateTime createdAt;

        public DeviceInfoBuilder code(String code) {
            this.code = code;
            return this;
        }

        public DeviceInfoBuilder deviceTen(String deviceTen) {
            this.deviceTen = deviceTen;
            return this;
        }

        public DeviceInfoBuilder deviceLoai(String deviceLoai) {
            this.deviceLoai = deviceLoai;
            return this;
        }

        public DeviceInfoBuilder soLuong(int soLuong) {
            this.soLuong = soLuong;
            return this;
        }

        public DeviceInfoBuilder ngayMua(LocalDate ngayMua) {
            this.ngayMua = ngayMua;
            return this;
        }

        public DeviceInfoBuilder tinhTrang(Boolean tinhTrang) {
            this.tinhTrang = tinhTrang;
            return this;
        }

        public DeviceInfoBuilder isActive(Boolean isActive) {
            this.isActive = isActive;
            return this;
        }

        public DeviceInfoBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public DeviceInfo build() {
            return new DeviceInfo(code, deviceTen, deviceLoai, soLuong, ngayMua, tinhTrang, isActive, createdAt);
        }
    }
}
