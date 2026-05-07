package com.example.QuanLyThietBi.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "devices")
@EntityListeners(AuditingEntityListener.class)
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code", unique = true, updatable = false)
    private String code;

    private String deviceTen;
    private String deviceLoai;
    private int soLuong;
    private LocalDate ngayMua;
    private Boolean tinhTrang;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "is_active")
    private Boolean isActive = true;

    @PrePersist
    protected void onCreate() {
        if (this.code == null) {
            this.code = "TB-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        }
    }
}
