package com.example.QuanLyThietBi.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "devices")
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String deviceTen;
    private String deviceLoai;
    private int soLuong;
    private LocalDate ngayMua;
    private Boolean tinhTrang;

}
