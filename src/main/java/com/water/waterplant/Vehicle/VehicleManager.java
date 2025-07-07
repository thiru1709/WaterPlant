package com.water.waterplant.Vehicle;

import com.water.waterplant.vo.Vehicle;

import java.util.List;

public interface VehicleManager {
    public void addVehicle(Vehicle vehicle);
    public List<Vehicle> getAvailableVehicles();
    public Vehicle getVehicleDetailsById(int vehicleId);
}
