package com.example.QuanLyThietBi.service;

import org.springframework.stereotype.Service;
import com.example.QuanLyThietBi.model.Employee;
import com.example.QuanLyThietBi.repository.EmployeeRepository;

import java.util.List;


@Service
public class EmployeeService {
    private final EmployeeRepository repo;

    public EmployeeService(EmployeeRepository repo) {
        this.repo = repo;
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
}
