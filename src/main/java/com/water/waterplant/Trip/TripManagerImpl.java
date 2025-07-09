package com.water.waterplant.Trip;

import com.water.waterplant.Vehicle.VehicleManager;
import com.water.waterplant.enums.TRIPSTATUS;
import com.water.waterplant.vo.Trip;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TripManagerImpl implements TripManager{

    @Autowired
    VehicleManager vehicleManager;

    private final List<Trip> tripList = new ArrayList<>();

    @PostConstruct
    public void init(){
        Trip trip = new Trip();
        trip.setStartTime(LocalDateTime.now());
        trip.setVehicleId(vehicleManager.getAvailableVehicles().get(0).getVehicleId());
        trip.setStatus(TRIPSTATUS.INPROGRESS);
        trip.setDriverId(12345);
        tripList.add(trip);
    }

    @Override
    public String startTrip(Trip trip) {
        trip.setStatus(TRIPSTATUS.INPROGRESS);
        tripList.add(trip);
        return "Trip started";
    }

    @Override
    public List<Trip> getTrips(TRIPSTATUS tripstatus) {
        return tripList.stream().filter(trip -> trip.getStatus()!= null && trip.getStatus().equals(tripstatus)).toList();
    }

    @Override
    public String endTrip(Trip trip) {
        trip.setStatus(TRIPSTATUS.COMPLETED);
        return "Trip ended";
    }

    @Override
    public Trip getTripById(int id) {
        Optional<Trip> tripOptional = tripList.stream().filter(trip -> trip.getTripId() == id).findAny();
        return tripOptional.orElse(null);
    }
}