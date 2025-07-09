package com.water.waterplant.Driver;

import com.water.waterplant.common.CommonHelper;
import com.water.waterplant.enums.DRIVERSTATUS;
import com.water.waterplant.vo.Driver;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DriverManagerImpl implements DriverManager{

    private final List<Driver> driverList = new ArrayList<>();

    @PostConstruct
    public List<Driver> init(){
        Driver driver = new Driver();
        driver.setDriverId(CommonHelper.generateNewId());
        driver.setDriverName("Basha");
        driver.setStatus(DRIVERSTATUS.READY);
        driverList.add(driver);
        return driverList;
    }

    @Override
    public void addDriver(Driver driver) {
        driverList.add(driver);

    }

    @Override
    public List<Driver> getAvailableDrivers() {
        return driverList;
    }

    @Override
    public Driver getDriverById(int driverId) {
        Optional<Driver> optionalDriver = driverList.stream().filter(driver -> driver.getDriverId() == driverId).findAny();
        return optionalDriver.orElse(null);
    }
}
