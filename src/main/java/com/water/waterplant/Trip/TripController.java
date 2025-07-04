package com.water.waterplant.Trip;

import com.water.waterplant.Driver.DriverManager;
import com.water.waterplant.Vehicle.VehicleManager;
import com.water.waterplant.enums.DRIVERSTATUS;
import com.water.waterplant.enums.TRIPSTATUS;
import com.water.waterplant.enums.VEHICLESTATUS;
import com.water.waterplant.vo.Driver;
import com.water.waterplant.vo.Trip;
import com.water.waterplant.vo.Vehicle;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
public class TripController {

    @Autowired
    VehicleManager vehicleManager;

    @Autowired
    DriverManager driverManager;

    @Autowired
    TripManager tripManager;

    @PostMapping("/startTrip")
    public ResponseEntity<List<Trip>> startTrip(@RequestBody Trip trip){
        //choose list of available drivers
        //choose available vehicle
        if(!assignVehicleToTrip(trip) || !assignDriverToTrip(trip)){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        trip.setStartTime(LocalDateTime.now());
        tripManager.startTrip(trip);

        return new ResponseEntity<>(tripManager.getTrips(TRIPSTATUS.INPROGRESS), HttpStatus.OK);

    }

    private boolean assignVehicleToTrip(Trip trip) {
        List<Vehicle> listOfVehicles = vehicleManager.getAvailableVehicles();
        Optional<Vehicle> availableVehicle = listOfVehicles.stream().filter(vehicle -> vehicle.getStatus().equals(VEHICLESTATUS.READY)).findFirst();
        if(availableVehicle.isEmpty()){
            return false;
        }
        System.out.println(availableVehicle.get());
        trip.setVehicleId(availableVehicle.get().getVehicleId());
        return true;
    }

    private boolean assignDriverToTrip(Trip trip) {
        List<Driver> listOfDrivers = driverManager.getAvailableDrivers();
        Optional<Driver> availableDriver = listOfDrivers.stream().filter(driver -> driver.getStatus().equals(DRIVERSTATUS.READY)).findFirst();
        if(availableDriver.isEmpty()){
            return false;
        }
        System.out.println(availableDriver.get());
        trip.setVehicleId(availableDriver.get().getDriverId());
        return true;
    }


}
