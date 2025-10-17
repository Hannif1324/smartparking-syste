package com.javatechie.smartparking_system.notification;

import com.javatechie.smartparking_system.event.VehicleEnteredEvent;
import com.javatechie.smartparking_system.event.VehicleExitedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @EventListener
    public void notifyOnVehicleEntry(VehicleEnteredEvent event){
        // Logic send Notification to user
        System.out.println("Notification: Vehicle" + event.vehicleNumber() + "entered at" + event.entryTime() + ". Welcome!");
    }

    @EventListener
    public void notifyOnVehicleExit(VehicleExitedEvent event){
        // logic send notification to user
        System.out.println("Notification: Vehicle" + event.vehicleNumber() + "has exited. Tanks you");
    }
}
