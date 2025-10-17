package com.javatechie.smartparking_system.billing;

import com.javatechie.smartparking_system.event.VehicleExitedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class BillingService {

    private final BillingRecordRepository billingRepository;
    public BillingService(BillingRecordRepository billingRepository){
        this.billingRepository= billingRepository;
    }

    @EventListener
    public void handleVehicleExit(VehicleExitedEvent exitedEvent){

        Duration duration = Duration.between(exitedEvent.entryTime(), exitedEvent.exitTime());
        double amount = Math.max(20, (duration.toMinutes()/ 60.0) * 50);

        BillingRecord record = new BillingRecord(null, exitedEvent.vehicleNumber(), amount, exitedEvent.exitTime());
        billingRepository.save(record);

        System.out.println("Billed $" + amount + "for vehicle" + exitedEvent.vehicleNumber() + "form" + exitedEvent.entryTime() +  "to" + exitedEvent.exitTime());
    }
}
