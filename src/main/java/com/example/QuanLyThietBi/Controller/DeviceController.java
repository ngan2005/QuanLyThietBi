package com.example.QuanLyThietBi.Controller;

import com.example.QuanLyThietBi.dto.DeviceInfo;
import com.example.QuanLyThietBi.form.DeviceForm;
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
    public List<DeviceInfo> findAll() {
        return service.findAllDTO();
    }

    // GET /devices/{code} — lấy thiết bị theo code
    @GetMapping("/{code}")
    public DeviceInfo findOne(@PathVariable String code) {
        return service.findOneDTO(code);
    }

    // POST /devices — thêm thiết bị mới
    @PostMapping
    public DeviceInfo save(@RequestBody DeviceForm form) {
        Device device = new Device();
        device.setDeviceTen(form.getDeviceTen());
        device.setDeviceLoai(form.getDeviceLoai());
        device.setSoLuong(form.getSoLuong());
        device.setNgayMua(form.getNgayMua());
        device.setTinhTrang(form.getTinhTrang());
        Device saved = service.save(device);
        return service.convertToDTO(saved);
    }

    // DELETE /devices/{code} — xóa thiết bị theo code
    @DeleteMapping("/{code}")
    public String delete(@PathVariable String code) {
        service.delete(code);
        return "Đã xóa thiết bị mã: " + code;
    }

    @PutMapping("/{code}")
    public DeviceInfo update(@PathVariable String code, @RequestBody DeviceForm form) {
        Device existing = service.findOne(code);
        existing.setDeviceTen(form.getDeviceTen());
        existing.setDeviceLoai(form.getDeviceLoai());
        existing.setSoLuong(form.getSoLuong());
        existing.setNgayMua(form.getNgayMua());
        existing.setTinhTrang(form.getTinhTrang());
        Device updated = service.save(existing);
        return service.convertToDTO(updated);
    }

    @GetMapping("/search")
    public List<DeviceInfo> search(@RequestParam String keyword) {
        List<Device> devices = service.search(keyword);
        return service.convertToDTOList(devices);
    }
}
