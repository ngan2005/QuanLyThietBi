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

    // Chỉ trả về nhân viên còn hiệu lực (isActive = true)
    public List<Employee> findAll() {
        return repo.findByIsActiveTrue();
    }

    public Employee findOne(String code) {
        return repo.findByCodeAndIsActiveTrue(code)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy nhân viên với mã: " + code));
    }

    public Employee save(Employee employee) {
        // Đảm bảo isActive = true khi tạo mới
        if (employee.getIsActive() == null) {
            employee.setIsActive(true);
        }
        return repo.save(employee);
    }

    // Soft delete: không xóa vật lý, chỉ set isActive = false
    public void delete(String code) {
        Employee employee = repo.findByCode(code)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy nhân viên với mã: " + code));
        employee.setIsActive(false);
        repo.save(employee);
    }

    public Employee capPhatThietBi(String employeeCode, String deviceCode) {
        Employee emp = repo.findByCodeAndIsActiveTrue(employeeCode)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy nhân viên với mã: " + employeeCode));

        Device dev = deviceRepo.findByCodeAndIsActiveTrue(deviceCode)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy thiết bị với mã: " + deviceCode));

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

    // Tìm kiếm chỉ trong những nhân viên còn hiệu lực (dùng @Query JPQL)
    public List<Employee> search(String name) {
        return repo.timTheoTen(name);
    }
}
