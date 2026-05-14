package com.example.QuanLyThietBi.service;

import com.example.QuanLyThietBi.dto.DeviceInfo;
import com.example.QuanLyThietBi.model.Device;
import com.example.QuanLyThietBi.repository.DeviceRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    // ============ CONVERSION: Entity → DTO ============

    public DeviceInfo convertToDTO(Device device) {
        return DeviceInfo.builder()
                .code(device.getCode())
                .deviceTen(device.getDeviceTen())
                .deviceLoai(device.getDeviceLoai())
                .soLuong(device.getSoLuong())
                .ngayMua(device.getNgayMua())
                .tinhTrang(device.getTinhTrang())
                .isActive(device.getIsActive())
                .createdAt(device.getCreatedAt())
                .build();
    }

    public List<DeviceInfo> convertToDTOList(List<Device> devices) {
        return devices.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<DeviceInfo> findAllDTO() {
        return convertToDTOList(findAll());
    }

    public DeviceInfo findOneDTO(String code) {
        return convertToDTO(findOne(code));
    }
}
