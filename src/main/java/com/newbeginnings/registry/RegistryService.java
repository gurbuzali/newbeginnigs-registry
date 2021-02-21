package com.newbeginnings.registry;

import com.newbeginnings.registry.exception.DuplicateReferenceNumberException;
import com.newbeginnings.registry.exception.ParticipantNotFoundException;
import com.newbeginnings.registry.generator.ReferenceNumberGenerator;
import com.newbeginnings.registry.model.Participant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Service
public class RegistryService {

    private static final String NO_PARTICIPANT = "No participant found with the reference number: ";
    private static final String DUPLICATE_REFERENCE_NUMBER = "Duplicate reference number: ";

    @Autowired
    private ReferenceNumberGenerator generator;

    private final ConcurrentMap<String, Participant> map = new ConcurrentHashMap<>();

    Participant get(String referenceNumber) {
        return getParticipant(referenceNumber);
    }

    String add(Participant participant) {
        String referenceNumber = generator.generate(participant);
        participant.setReferenceNumber(referenceNumber);
        Participant previousParticipant = map.putIfAbsent(referenceNumber, participant);
        if (previousParticipant != null) {
            throw new DuplicateReferenceNumberException(DUPLICATE_REFERENCE_NUMBER + referenceNumber);
        }
        return referenceNumber;
    }

    void updatePhoneNumber(String referenceNumber, String phoneNumber) {
        Participant participant = getParticipant(referenceNumber);
        participant.setPhoneNumber(phoneNumber);
    }

    void updateAddress(String referenceNumber, String address) {
        Participant participant = getParticipant(referenceNumber);
        participant.setAddress(address);
    }

    Participant getParticipant(String referenceNumber) {
        Participant participant = map.get(referenceNumber);
        if (participant == null) {
            throw new ParticipantNotFoundException(NO_PARTICIPANT + referenceNumber);
        }
        return participant;
    }
}
