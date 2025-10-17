package com.javatechie.smartparking_system;

import com.javatechie.smartparking_system.allocation.Slot;

import com.javatechie.smartparking_system.allocation.SlotRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.modulith.Modulith;
import org.springframework.modulith.Modulithic;

@SpringBootApplication
@Modulith
@Modulithic
public class SmartparkingSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartparkingSystemApplication.class, args);
    }

    @Bean
    CommandLineRunner initSlots(SlotRepository repo) {
        return args -> {
            if (repo.count() == 0) {
                System.out.println("Membuat slot parkir awal..."); // Tambahkan log untuk debugging
                repo.save(new Slot(null, "A1", true, null));
                repo.save(new Slot(null, "A2", true, null));
                repo.save(new Slot(null, "B1", true, null));
                System.out.println("Slot parkir berhasil dibuat.");
            } else {
                System.out.println("Slot parkir sudah ada, tidak perlu inisialisasi.");
            }
        };
    }
}