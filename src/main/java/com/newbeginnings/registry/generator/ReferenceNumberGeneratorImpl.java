package com.newbeginnings.registry.generator;

import com.newbeginnings.registry.model.Participant;

import java.util.UUID;

public class ReferenceNumberGeneratorImpl implements ReferenceNumberGenerator {

    @Override
    public String generate(Participant participant) {
        return UUID.randomUUID().toString();
    }
}
