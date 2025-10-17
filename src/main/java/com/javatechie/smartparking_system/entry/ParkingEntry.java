package com.javatechie.smartparking_system.entry;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "parking_entry")
// Data Untuk Entry Parking
public class ParkingEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "vehicle_number") // <-- Peta kan secara eksplisit
    private String vehicleNumber;

    @Column(name = "entry_time") // <-- Peta kan secara eksplisit
    private LocalDateTime entryTime;

    @Column(name = "exit_time") // <-- Peta kan secara eksplisit
    private LocalDateTime exitTime;

    @Column(name = "active") // <-- Peta kan secara eksplisit
    private boolean active;
}