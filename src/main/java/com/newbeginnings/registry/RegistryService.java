package com.newbeginnings.registry;

import com.newbeginnings.registry.exception.DuplicateReferenceNumberException;
import com.newbeginnings.registry.exception.ParticipantNotFoundException;
import com.newbeginnings.registry.generator.ReferenceNumberGenerator;
import com.newbeginnings.registry.model.Participant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * The Registry Service which is responsible for managing the registry
 * of the participants.
 */
@Service
public class RegistryService {

    private static final String NO_PARTICIPANT = "No participant found with the reference number: ";
    private static final String DUPLICATE_REFERENCE_NUMBER = "Duplicate reference number: ";

    @Autowired
    private ReferenceNumberGenerator generator;

    /**
     * Stores the participants.
     * key: reference number
     * value: the participant
     */
    private final ConcurrentMap<String, Participant> store = new ConcurrentHashMap<>();

    /**
     * @param referenceNumber the reference number.
     * @return the participant associated with the given {@code referenceNumber}.
     * @throws ParticipantNotFoundException if no participant found with the
     *                                      given {@code referenceNumber}.
     */
    @NonNull
    Participant get(@NonNull String referenceNumber) {
        return getParticipant(referenceNumber);
    }

    /**
     * Adds the given {@code participant} to store, generates a reference
     * number and associates it to the given {@code participant}.
     *
     * @param participant the {@link Participant} to add.
     * @return the generated reference number.
     * @throws DuplicateReferenceNumberException if the generated reference
     *                                           number is already associated with a participant.
     */
    @NonNull
    String add(@NonNull Participant participant) {
        String referenceNumber = generator.generate(participant);
        participant.setReferenceNumber(referenceNumber);
        Participant previousParticipant = store.putIfAbsent(referenceNumber, participant);
        if (previousParticipant != null) {
            throw new DuplicateReferenceNumberException(DUPLICATE_REFERENCE_NUMBER + referenceNumber);
        }
        return referenceNumber;
    }

    /**
     * Updates the phone number of the participant associated with the given
     * {@code referenceNumber}.
     *
     * @param referenceNumber the reference number of the participant.
     * @param phoneNumber     the new phone number.
     * @return the old phone number.
     * @throws ParticipantNotFoundException if no participant found with the
     *                                      given {@code referenceNumber}.
     */
    @NonNull
    String updatePhoneNumber(@NonNull String referenceNumber, @NonNull String phoneNumber) {
        Participant participant = getParticipant(referenceNumber);
        String oldPhoneNumber = participant.getPhoneNumber();
        participant.setPhoneNumber(phoneNumber);
        return oldPhoneNumber;
    }

    /**
     * Updates the address of the participant associated with the given
     * {@code referenceNumber}.
     *
     * @param referenceNumber the reference number of the participant.
     * @param address         the new address.
     * @return the old address.
     * @throws ParticipantNotFoundException if no participant found with the
     *                                      given {@code referenceNumber}.
     */
    @NonNull
    String updateAddress(@NonNull String referenceNumber, @NonNull String address) {
        Participant participant = getParticipant(referenceNumber);
        String oldAddress = participant.getAddress();
        participant.setAddress(address);
        return oldAddress;
    }

    /**
     * Deletes the participant with the given {@code referenceNumber}.
     *
     * @param referenceNumber the reference number of the participant.
     * @return the participant associated with the given {@code referenceNumber}.
     * @throws ParticipantNotFoundException if no participant found with the
     *                                      given {@code referenceNumber}.
     */
    @NonNull
    Participant delete(@NonNull String referenceNumber) {
        Participant participant = store.remove(referenceNumber);
        if (participant == null) {
            throw new ParticipantNotFoundException(NO_PARTICIPANT + referenceNumber);
        }
        return participant;
    }

    private Participant getParticipant(String referenceNumber) {
        Participant participant = store.get(referenceNumber);
        if (participant == null) {
            throw new ParticipantNotFoundException(NO_PARTICIPANT + referenceNumber);
        }
        return participant;
    }
}
