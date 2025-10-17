package com.javatechie.smartparking_system.entry;

import com.javatechie.smartparking_system.event.VehicleExitedEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ExitService {

    // save vehicle to basis db
    // allocation a parking slot
    // send notification to user

    private final ParkingEntryRepository repo;
    private final ApplicationEventPublisher publisher;

    public ExitService(ParkingEntryRepository repo, ApplicationEventPublisher publisher){
        this.repo = repo;
        this.publisher = publisher;
    }

    public void vehicleExit(String vehicleNumber){
        // get vehicle entry details form DB
        // update Exit time
        // sava to DB
        // publish vehicle exited event

        ParkingEntry exitEntry = repo.findByVehicleNumberAndActiveTrue(vehicleNumber)
                .orElseThrow(() -> new RuntimeException("No Active Entry found for vehicle" + vehicleNumber));
        exitEntry.setExitTime(LocalDateTime.now());

        exitEntry.setActive(false);
        repo.save(exitEntry);

        // Publish Event ExitedVehicle
        publisher.publishEvent(new VehicleExitedEvent(vehicleNumber, exitEntry.getEntryTime(), exitEntry.getExitTime()));
    }
}
