package com.prodapt.registration.service.impl;

import com.prodapt.registration.model.DeviceList;
import com.prodapt.registration.repository.DeviceListRepository;
import com.prodapt.registration.service.DeviceListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class DeviceListServiceImpl implements DeviceListService {

    @Autowired
    private DeviceListRepository deviceListRepository;

    @Override
    public DeviceList save(MultipartFile file, DeviceList deviceList) throws IOException {
        deviceList.setImageOfTheDevice(file.getBytes());
        deviceList.setContentType(file.getContentType());
        deviceList.setFilename(StringUtils.cleanPath(file.getOriginalFilename()));
        return deviceListRepository.save(deviceList);
    }

//    @Override
//    public DeviceList save(DeviceList deviceList) {
//        return deviceListRepository.save(deviceList);
//    }

    @Override
    public List<DeviceList> findAll() {
        return deviceListRepository.findAll();
    }

    @Override
    public Optional<DeviceList> findOne(Long id) {
        return deviceListRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        deviceListRepository.deleteById(id);
    }
}
