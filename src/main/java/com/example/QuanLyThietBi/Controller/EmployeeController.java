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

    // Chỉ trả về nhân viên còn hiệu lực (isActive = true)
    @GetMapping
    public List<Employee> findAll() {
        return service.findAll();
    }

    @GetMapping("/{code}")
    public Employee findByCode(@PathVariable String code) {
        return service.findOne(code);
    }

    @PostMapping
    public Employee save(@RequestBody Employee employee) {
        return service.save(employee);
    }

    @PutMapping("/{code}")
    public Employee update(@PathVariable String code, @RequestBody Employee employee) {
        Employee existing = service.findOne(code);
        employee.setId(existing.getId());
        employee.setCode(existing.getCode());
        return service.save(employee);
    }

    // Soft delete: set isActive = false thay vì xóa vật lý
    @DeleteMapping("/{code}")
    public String deleteByCode(@PathVariable String code) {
        service.delete(code);
        return "Đã vô hiệu hóa nhân viên với mã: " + code;
    }

    @PostMapping("/{empCode}/cap-phat/{deviceCode}")
    public Employee capPhat(@PathVariable String empCode, @PathVariable String deviceCode) {
        return service.capPhatThietBi(empCode, deviceCode);
    }

    // Tìm kiếm chỉ trong nhân viên còn hiệu lực
    @GetMapping("/search")
    public List<Employee> searchByName(@RequestParam("name") String keyword) {
        return service.search(keyword);
    }
}
