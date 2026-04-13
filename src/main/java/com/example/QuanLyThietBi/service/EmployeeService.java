package com.example.QuanLyThietBi.service;

import org.springframework.stereotype.Service;
import com.example.QuanLyThietBi.model.Employee;
import com.example.QuanLyThietBi.repository.EmployeeRepository;

import java.util.List;

import com.example.QuanLyThietBi.model.Device;
import com.example.QuanLyThietBi.repository.DeviceRepository;

@Service
public class EmployeeService {
    private final EmployeeRepository repo;
    private final DeviceRepository deviceRepo;

    public EmployeeService(EmployeeRepository repo, DeviceRepository deviceRepo) {
        this.repo = repo;
        this.deviceRepo = deviceRepo;
    }

    public List<Employee> findAll() {
        return repo.findAll();
    }

    public Employee findOne(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("không tìm thấy mã nhân viên: " + id));

    }

    public Employee save(Employee employee) {
        return repo.save(employee);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public Employee capPhatThietBi(Long employeeId, Long deviceId) {
        Employee emp = repo.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy mã nhân viên: " + employeeId));
        
        Device dev = deviceRepo.findById(deviceId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy mã thiết bị: " + deviceId));

        if (emp.getDsThietBi().size() >= 2) {
            throw new RuntimeException("Nhân viên này đã mượn tối đa 2 thiết bị!");
        }

        if (dev.getSoLuong() <= 0) {
            throw new RuntimeException("Thiết bị này đã hết hàng trong kho!");
        }

        // Thêm thiết bị cho nhân viên
        emp.getDsThietBi().add(dev);
        
        // Trừ đi 1 trong kho
        dev.setSoLuong(dev.getSoLuong() - 1);
        deviceRepo.save(dev);
        
        return repo.save(emp);
    }
}
