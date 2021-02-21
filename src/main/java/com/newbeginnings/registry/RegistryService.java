package com.newbeginnings.registry;

import com.newbeginnings.registry.generator.ReferenceNumberGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistryService {

    @Autowired
    ReferenceNumberGenerator generator;
}
