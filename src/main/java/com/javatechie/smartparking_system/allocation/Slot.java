package com.javatechie.smartparking_system.allocation;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "slot")
// Data Slot Parking yang tersedia
public class Slot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String slotCode; // like A1, B2, C3
    private boolean available;
    private String vehicleNumber; // Optional For Checking
}
