package com.newbeginnings.registry;

import com.newbeginnings.registry.generator.ReferenceNumberGenerator;
import com.newbeginnings.registry.generator.ReferenceNumberGeneratorImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RegistryApplication {

    public static void main(String[] args) {
        SpringApplication.run(RegistryApplication.class, args);
    }

    @Bean
    ReferenceNumberGenerator generator() {
        return new ReferenceNumberGeneratorImpl();
    }

}
