package com.water.waterplant.Vehicle;

import com.water.waterplant.enums.VEHICLESTATUS;
import com.water.waterplant.vo.Vehicle;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

@Service
public class VehicleManagerImpl implements VehicleManager{

    private List<Vehicle> vehicleList = new ArrayList<>();

    @PostConstruct
    public void init(){
        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleId(new SecureRandom().nextInt(1,999999999));
        vehicle.setCapacity(30);
        vehicle.setStatus(VEHICLESTATUS.READY);
        vehicleList.add(vehicle);
    }
    @Override
    public void addVehicle(Vehicle vehicle) {
        vehicleList.add(vehicle);
    }

    @Override
    public List<Vehicle> getAvailableVehicles() {
        return vehicleList;
    }
}
