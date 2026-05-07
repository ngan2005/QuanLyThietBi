package com.example.QuanLyThietBi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DevicesInfo {
    private Long id;
    private String code;
    private String deviceTen;
    private String deviceLoai;
    private int soLuong;
    private LocalDate ngayMua;
    private Boolean tinhTrang;
    private Boolean isActive;
    private LocalDateTime createdAt;
}
