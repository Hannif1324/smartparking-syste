package com.javatechie.smartparking_system.allocation;

import com.javatechie.smartparking_system.event.VehicleEnteredEvent;
import com.javatechie.smartparking_system.event.VehicleExitedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class SlotAllocationService {

    private final SlotRepository slotRepo;
    public SlotAllocationService(SlotRepository slotRepo){
        this.slotRepo= slotRepo;
    }

    // Mangambil Event dan Menerima Entry
    @EventListener
    public void handleVehicleEntry(VehicleEnteredEvent vehicleEntryEvent){

        // Periksa Yang Available Di Slot
        Slot slot = slotRepo.findFirstByAvailableTrue()
                .orElseThrow(() -> new RuntimeException("No Available slot!")); // Konfirmasi Availabe
        slot.setAvailable(false);

        // Isi Dengan Nomor Mobil dari Event
        slot.setVehicleNumber(vehicleEntryEvent.vehicleNumber());
        slotRepo.save(slot);
        System.out.println("Allocated Slot" + slot.getSlotCode() + "to vehicle" + vehicleEntryEvent.vehicleNumber());

    }

    @EventListener
    public void handleVehicleExit(VehicleExitedEvent vehicleExitEvent){

        slotRepo.findByVehicleNumber(vehicleExitEvent.vehicleNumber())
                .ifPresentOrElse(slot -> {
                    slot.setAvailable(true);
                    slot.setVehicleNumber(null);
                    slotRepo.save(slot);
                    System.out.println("Parking free Slot");

                }, () -> {
                    throw new RuntimeException("No Slot found for vehicle");
                });


    }
}
