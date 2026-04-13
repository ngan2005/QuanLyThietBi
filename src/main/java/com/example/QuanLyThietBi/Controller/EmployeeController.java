package com.example.QuanLyThietBi.Controller;


import com.example.QuanLyThietBi.model.Employee;
import com.example.QuanLyThietBi.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Employee")
public class EmployeeController {
    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping
    public List<Employee> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Employee findById(@PathVariable Long id) {
        return service.findOne(id);
    }

    @PostMapping
    public Employee save(@RequestBody Employee employee) {
        return service.save(employee);
    }

    @PutMapping("/{id}")
    public Employee update(@PathVariable Long id, @RequestBody Employee employee) {
        // Cập nhật ID từ đường dẫn vào đối tượng employee, sau đó lưu lại
        employee.setId(id);
        return service.save(employee);
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable Long id) {
        service.delete(id);
        return "đã xóa nhân viên với id: " + id;
    }

    @PostMapping("/{empId}/cap-phat/{deviceId}")
    public Employee capPhat(@PathVariable Long empId, @PathVariable Long deviceId) {
        return service.capPhatThietBi(empId, deviceId);
    }
}
