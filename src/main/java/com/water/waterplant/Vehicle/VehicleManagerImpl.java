package com.water.waterplant.Vehicle;

import com.water.waterplant.common.CommonHelper;
import com.water.waterplant.enums.VEHICLESTATUS;
import com.water.waterplant.vo.Vehicle;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleManagerImpl implements VehicleManager{

    private List<Vehicle> vehicleList = new ArrayList<>();

    @PostConstruct
    public void init(){
        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleId(CommonHelper.generateNewId());
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

    @Override
    public Vehicle getVehicleDetailsById(int vehicleId) {
        Optional<Vehicle> optionalVehicle = vehicleList.stream().filter(vehicle -> vehicle.getVehicleId() == vehicleId).findAny();
        return optionalVehicle.orElse(null);
    }
}
