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

    // GET /devices/1 — lấy thiết bị theo id
    @GetMapping("/{id}")
    public Device findOne(@PathVariable Long id) {
        return service.findOne(id);
    }

    // POST /devices — thêm thiết bị mới
    @PostMapping
    public Device save(@RequestBody Device device) {
        return service.save(device);
    }

    // DELETE /devices/1 — xóa thiết bị theo id
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "Đã xóa thiết bị id: " + id;
    }
}
