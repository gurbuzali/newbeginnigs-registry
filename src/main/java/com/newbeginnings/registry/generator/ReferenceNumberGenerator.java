package com.newbeginnings.registry.generator;

import com.newbeginnings.registry.model.Participant;

/**
 * Emulates the API which generates the unique reference number for
 * participants.
 */
public interface ReferenceNumberGenerator {

    /**
     * Generates unique reference number.
     * @param participant the participant which the generated reference number
     *                    will be associated with.
     * @return the generated reference number.
     */
    String generate(Participant participant);

}
