package com.example.QuanLyThietBi.repository;

import com.example.QuanLyThietBi.model.Device;
import com.example.QuanLyThietBi.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {
    // Chỉ lấy thiết bị còn hiệu lực
    List<Device> findByIsActiveTrue();

    // Tìm thiết bị theo code
    Optional<Device> findByCode(String code);

    // Tìm thiết bị theo code và còn hiệu lực
    Optional<Device> findByCodeAndIsActiveTrue(String code);

    // Tìm kiếm thiết bị còn hiệu lực theo tên
    @Query("select d from Device d where lower(d.deviceTen) like lower(concat('%', :keyword, '%' ) ) and d.isActive = true ")
    List<Device> timDeviceTen(@Param("keyword") String keyword);
}