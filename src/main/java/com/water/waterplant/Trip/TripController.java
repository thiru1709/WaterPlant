package com.water.waterplant.Trip;

import com.water.waterplant.Driver.DriverManager;
import com.water.waterplant.Vehicle.VehicleManager;
import com.water.waterplant.common.CommonHelper;
import com.water.waterplant.enums.DRIVERSTATUS;
import com.water.waterplant.enums.ORDERSTATUS;
import com.water.waterplant.enums.TRIPSTATUS;
import com.water.waterplant.enums.VEHICLESTATUS;
import com.water.waterplant.order.OrderManager;
import com.water.waterplant.vo.Driver;
import com.water.waterplant.vo.Order;
import com.water.waterplant.vo.Trip;
import com.water.waterplant.vo.Vehicle;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    @Autowired
    OrderManager orderManager;

    @PostMapping("/startTrip")
    public ResponseEntity<List<Trip>> startTrip(@RequestBody Trip trip){
        //choose list of available drivers
        //choose available vehicle
        if(!assignVehicleToTrip(trip) || !assignDriverToTrip(trip)){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        trip.setTripId(CommonHelper.generateNewId());
        trip.setStatus(TRIPSTATUS.START);
        Vehicle vehicle = vehicleManager.getVehicleDetailsById(trip.getVehicleId());
        List<Order> pendingOrders = orderManager.ordersByStatus(ORDERSTATUS.ACCEPTED);
        List<Order> ordersThatCanBeFulfilled = pendingOrders;
//        int count = vehicle.getCapacity();
//        for(Order order : pendingOrders){
//            while (count > 0){
//                if(count - (order.getBubbleCanQuantity() + order.getCoolCanQuantity()) > 0){
//                    ordersThatCanBeFulfilled.add(order);
//                    count = count - (order.getBubbleCanQuantity() + order.getCoolCanQuantity());
//                }
//                break;
//            }
//        }
        trip.setOrderList(ordersThatCanBeFulfilled);
        trip.setStartTime(LocalDateTime.now());
        tripManager.startTrip(trip);

        return new ResponseEntity<>(tripManager.getTrips(TRIPSTATUS.INPROGRESS), HttpStatus.OK);
    }

    @GetMapping("/endTrip")
    public ResponseEntity<String> endTrip(@RequestParam int id){
        Trip tripById = tripManager.getTripById(id);
        if(tripById == null){
            return new ResponseEntity<>("Trip end failed",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        tripManager.endTrip(tripById);
        return new ResponseEntity<>("Trip successfully ended",HttpStatus.OK);

    }

    @GetMapping("/trips")
    public ResponseEntity<List<Trip>> getTripsByStatus(@RequestParam String status){
        List<Trip> trips = tripManager.getTrips(TRIPSTATUS.valueOf(status));
        return new ResponseEntity<>(trips,HttpStatus.OK);


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
        trip.setDriverId(availableDriver.get().getDriverId());
        return true;
    }


}
