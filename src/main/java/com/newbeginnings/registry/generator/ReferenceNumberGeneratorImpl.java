package com.newbeginnings.registry.generator;

import com.newbeginnings.registry.model.Participant;

import java.util.UUID;

/**
 * And implementation if {@link ReferenceNumberGenerator} which uses
 * {@link UUID#randomUUID()} to generate unique reference numbers.
 */
public class ReferenceNumberGeneratorImpl implements ReferenceNumberGenerator {

    @Override
    public String generate(Participant participant) {
        return UUID.randomUUID().toString();
    }
}
