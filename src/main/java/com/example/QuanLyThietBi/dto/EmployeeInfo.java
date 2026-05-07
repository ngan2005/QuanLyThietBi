package com.example.QuanLyThietBi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeInfo {
    private String id;
    private String code;
    private String tenNV;
    private String chucVu;
}
