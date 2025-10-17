package com.javatechie.smartparking_system;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.modulith.Modulith;
import org.springframework.modulith.Modulithic;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;

@Modulith
@Modulithic
class SmartparkingSystemApplicationTests {

	@Test
	void contextLoads() {
        ApplicationModules modules = ApplicationModules.of(SmartparkingSystemApplicationTests.class).verify();
        new Documenter(modules).writeDocumentation();
    }

}
