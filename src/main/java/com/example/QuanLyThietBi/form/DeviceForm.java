package com.example.QuanLyThietBi.form;

import java.time.LocalDate;

public class DeviceForm {
    private String deviceTen;
    private String deviceLoai;
    private int soLuong;
    private LocalDate ngayMua;
    private Boolean tinhTrang;

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
}
