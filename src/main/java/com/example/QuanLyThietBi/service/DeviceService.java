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

    public List<Device> findAll() {
        return repo.findAll();
    }

    public Device findOne(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy: " + id));

    }
    public Device save(Device device) {
        return repo.save(device);
    }
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
