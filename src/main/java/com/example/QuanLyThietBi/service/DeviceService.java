package com.example.QuanLyThietBi.service;

import com.example.QuanLyThietBi.model.Device;
import com.example.QuanLyThietBi.repository.DeviceRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceService {
    private final DeviceRepository repo;

    public DeviceService(DeviceRepository repo) {
        this.repo = repo;
    }

    // Chỉ trả về thiết bị còn hiệu lực (isActive = true)
    public List<Device> findAll() {
        return repo.findByIsActiveTrue();
    }

    public Device findOne(String code) {
        return repo.findByCodeAndIsActiveTrue(code)
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy thiết bị với mã: " + code));
    }

    public Device save(Device device) {
        // Đảm bảo isActive = true khi tạo mới
        if (device.getIsActive() == null) {
            device.setIsActive(true);
        }
        return repo.save(device);
    }

    // Soft delete: không xóa vật lý, chỉ set isActive = false
    public void delete(String code) {
        Device device = repo.findByCode(code)
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy thiết bị với mã: " + code));
        device.setIsActive(false);
        repo.save(device);
    }

    // Tìm kiếm chỉ trong những thiết bị còn hiệu lực
    public List<Device> search(String keyword) {
        return  repo.timDeviceTen(keyword);
    }
}
