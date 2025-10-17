package com.javatechie.smartparking_system.entry;

import com.javatechie.smartparking_system.allocation.SlotAllocationService;
import com.javatechie.smartparking_system.event.VehicleEnteredEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EntryService {

    // save vehicle to basis db
    // allocation a parking slot
    // send notification to user

    private final ParkingEntryRepository repo;
    private final ApplicationEventPublisher publisher;
    public EntryService(ParkingEntryRepository repo, ApplicationEventPublisher publisher){
        this.repo = repo;
        this.publisher = publisher;
    }

    public void vehicleEntry(String vehicleNumber){

        ParkingEntry parkingEntry = new ParkingEntry(null, vehicleNumber, LocalDateTime.now(), null, true);
        repo.save(parkingEntry);

        // publish an event
        publisher.publishEvent(new VehicleEnteredEvent(vehicleNumber, parkingEntry.getEntryTime()));
    }
}
