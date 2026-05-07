package com.example.QuanLyThietBi.Controller;

import com.example.QuanLyThietBi.model.Device;
import com.example.QuanLyThietBi.service.DeviceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/devices")
public class DeviceController {
    private final DeviceService service;

    public DeviceController(DeviceService service) {
        this.service = service;
    }

    // GET /devices — lấy tất cả thiết bị
    @GetMapping
    public List<Device> findAll() {
        return service.findAll();
    }

    // GET /devices/{code} — lấy thiết bị theo code
    @GetMapping("/{code}")
    public Device findOne(@PathVariable String code) {
        return service.findOne(code);
    }

    // POST /devices — thêm thiết bị mới
    @PostMapping
    public Device save(@RequestBody Device device) {
        return service.save(device);
    }

    // DELETE /devices/{code} — xóa thiết bị theo code
    @DeleteMapping("/{code}")
    public String delete(@PathVariable String code) {
        service.delete(code);
        return "Đã xóa thiết bị mã: " + code;
    }

    @PutMapping("/{code}")
    public Device update(@PathVariable String code, @RequestBody Device device) {
        Device existing = service.findOne(code);
        device.setId(existing.getId());
        device.setCode(existing.getCode());
        return service.save(device);
    }

    @GetMapping("/search")
    public List<Device> search(@RequestParam String keyword) {
        return service.search(keyword);
    }
}
