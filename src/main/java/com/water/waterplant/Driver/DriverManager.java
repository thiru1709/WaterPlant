package com.water.waterplant.Driver;

import com.water.waterplant.vo.Driver;

import java.util.List;

public interface DriverManager {
    public void addDriver(Driver driver);
    public List<Driver> getAvailableDrivers();
}
