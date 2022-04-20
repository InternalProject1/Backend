package com.prodapt.registration.repository;

import com.prodapt.registration.model.DeviceList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceListRepository extends JpaRepository<DeviceList, Long> {
}
