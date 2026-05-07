package com.example.QuanLyThietBi.Controller;

import com.example.QuanLyThietBi.model.Employee;
import com.example.QuanLyThietBi.service.EmployeeService;
import com.example.QuanLyThietBi.dto.EmployeeInfo;
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
    public List<EmployeeInfo> findAll() {
        return service.findAllDTO();  // Trả DTO
    }

    @GetMapping("/{code}")
    public EmployeeInfo findByCode(@PathVariable String code) {
        return service.findOneDTO(code);  // Trả DTO
    }

    @PostMapping
    public EmployeeInfo save(@RequestBody Employee employee) {
        Employee saved = service.save(employee);
        return service.convertToDTO(saved);  // Chuyển sang DTO trước khi trả về
    }

    @PutMapping("/{code}")
    public EmployeeInfo update(@PathVariable String code, @RequestBody Employee employee) {
        Employee existing = service.findOne(code);
        employee.setId(existing.getId());
        employee.setCode(existing.getCode());
        Employee updated = service.save(employee);
        return service.convertToDTO(updated);  // Trả DTO
    }

    // Soft delete: set isActive = false thay vì xóa vật lý
    @DeleteMapping("/{code}")
    public String deleteByCode(@PathVariable String code) {
        service.delete(code);
        return "Đã vô hiệu hóa nhân viên với mã: " + code;
    }

    @PostMapping("/{empCode}/cap-phat/{deviceCode}")
    public EmployeeInfo capPhat(@PathVariable String empCode, @PathVariable String deviceCode) {
        Employee emp = service.capPhatThietBi(empCode, deviceCode);
        return service.convertToDTO(emp);  // Trả DTO
    }

    // Tìm kiếm chỉ trong nhân viên còn hiệu lực
    @GetMapping("/search")
    public List<EmployeeInfo> searchByName(@RequestParam("name") String keyword) {
        List<Employee> employees = service.search(keyword);
        return service.convertToDTOList(employees);  // Trả List DTO
    }
}
