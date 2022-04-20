package com.prodapt.registration.service;

import com.prodapt.registration.model.DeviceList;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

public interface DeviceListService {

    DeviceList save(MultipartFile file, DeviceList deviceList) throws IOException;

//    DeviceList save(DeviceList deviceList);

    List<DeviceList> findAll();

    Optional<DeviceList> findOne(Long id);

    void delete(Long id);

}
