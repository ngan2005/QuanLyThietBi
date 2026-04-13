package com.example.QuanLyThietBi.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "tennv")
    private String tenNV;
    private String chucVu;
    @ManyToMany(cascade = CascadeType.ALL)
    List<Device> dsThietBi = new ArrayList<>();

}
