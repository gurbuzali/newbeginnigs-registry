package com.newbeginnings.registry.generator;

import com.newbeginnings.registry.model.Participant;

public interface ReferenceNumberGenerator {

    String generate(Participant participant);

}
