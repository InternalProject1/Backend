package com.prodapt.registration.controller;

import com.prodapt.registration.model.DeviceList;
import com.prodapt.registration.service.DeviceListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/device")
public class DeviceListController {

    @Autowired
    private DeviceListService deviceListService;

    @PostMapping("/save")
    public ResponseEntity<Map<String, Object>> createDevice(@RequestPart("file") MultipartFile file, @RequestPart("deviceList") DeviceList deviceList) throws IOException {
        Map<String, Object> map = new HashMap<>();
        try {
            deviceList = deviceListService.save(file, deviceList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (deviceList != null && deviceList.getId() != null) {
        	String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/device/files/")
                    .path("" + deviceList.getId()).toUriString();
            map.put("deviceList", deviceList);
            map.put("fileDownloadUri", fileDownloadUri);
            return ResponseEntity.ok(map);
        } else {
        	map.put("status", "FAILURE");
            map.put("message", "Device creation failed");
            return ResponseEntity.badRequest().body(map);
		}
    }

    @PutMapping("/save")
    public ResponseEntity<Map<String, Object>> updateDevice(@RequestPart("file") MultipartFile file, @RequestPart("deviceList") DeviceList deviceList) throws IOException {
        Map<String, Object> map = new HashMap<>();
        try {
            if (deviceList.getId() != null && deviceList.getId() != 0) {
                deviceList = deviceListService.save(file, deviceList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (deviceList != null) {
        	String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/device/files/")
                    .path("" + deviceList.getId()).toUriString();
            map.put("deviceList", deviceList);
            map.put("fileDownloadUri", fileDownloadUri);
            return ResponseEntity.ok(map);
        } else {
            map.put("status", "FAILURE");
            map.put("message", "Device updation failed");
            return ResponseEntity.badRequest().body(map);
        }
    }

//    @PostMapping("/save")
//    public DeviceList createDevice(@RequestBody DeviceList deviceList) {
//        return deviceListService.save(deviceList);
//    }
//    
//    @PutMapping("/update")
//    public DeviceList updateDevice(@RequestBody DeviceList deviceList) {
//        return deviceListService.save(deviceList);
//    }

    @GetMapping("/files/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable Long id) {
        Optional<DeviceList> deviceList = deviceListService.findOne(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + deviceList.get().getFilename() + "\"")
                .contentType(MediaType.valueOf(deviceList.get().getContentType()))
                .body(deviceList.get().getImageOfTheDevice());
    }

    @GetMapping("/list")
    public List<Map<String, Object>> getAllDevices() {
        return deviceListService.findAll().stream().map(device -> {
            Map<String, Object> map = new HashMap<>();
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/device/files/")
                    .path("" + device.getId())
                    .toUriString();
            map.put("deviceList", device);
            map.put("fileDownloadUri", fileDownloadUri);
            return map;
        }).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public Optional<DeviceList> getOne(@PathVariable("id") Long id) {
        return deviceListService.findOne(id);
    }

    @DeleteMapping("/{id}")
    public void deleteDevice(@PathVariable("id") Long id) {
        deviceListService.delete(id);
    }

}
