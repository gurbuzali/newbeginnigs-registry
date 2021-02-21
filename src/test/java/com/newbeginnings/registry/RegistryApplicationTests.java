package com.newbeginnings.registry;

import com.newbeginnings.registry.model.Participant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class RegistryApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String baseUri;

    @BeforeEach
    public void setup() {
        baseUri = "http://localhost:" + port + "/api/v1";
    }

    @Test
    public void whenAdd_thenReturnReferenceNumber() {
        // given
        Participant participant = createParticipantWithRandomValues();

        // when
        String referenceNumber = addParticipant(participant);

        // then
        assertThat(referenceNumber).isNotEmpty();
    }

    @Test
    public void whenGet_andNoParticipant_thenReturnNotFound() {
        // given
        String refNo = "foo";

        // when
        ResponseEntity<Participant> responseEntity = restTemplate
                .getForEntity(baseUri + "/get/{refNo}", Participant.class, refNo);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void whenGet_thenReturnAddedParticipant() {
        // given
        Participant participant = createParticipantWithRandomValues();
        String refNo = addParticipant(participant);

        // when
        ResponseEntity<Participant> responseEntity = getParticipant(refNo);

        // then
        participant.setReferenceNumber(refNo);
        assertThat(responseEntity.getBody()).isEqualTo(participant);
    }

    @Test
    public void whenUpdatePhoneNumber_andNoParticipant_thenReturnNotFound() {
        // given
        String refNo = "foo";
        String newPhoneNo = "bar";

        ResponseEntity<String> responseEntity = restTemplate
                .postForEntity(baseUri + "/updatePhoneNumber/{refNo}", newPhoneNo, String.class, refNo);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void whenUpdatePhoneNumber_thenUpdateParticipantAndReturnOldNumber() {
        // given
        Participant participant = createParticipantWithRandomValues();
        String refNo = addParticipant(participant);
        String newPhoneNo = randomString();

        // when
        ResponseEntity<String> responseEntity = restTemplate
                .postForEntity(baseUri + "/updatePhoneNumber/{refNo}", newPhoneNo, String.class, refNo);

        assertThat(responseEntity.getBody()).isEqualTo(participant.getPhoneNumber());
        assertThat(getParticipant(refNo).getBody().getPhoneNumber()).isEqualTo(newPhoneNo);
    }

    @Test
    public void whenUpdateAddress_andNoParticipant_thenReturnNotFound() {
        // given
        String refNo = "foo";
        String newAddress = "bar";

        ResponseEntity<String> responseEntity = restTemplate
                .postForEntity(baseUri + "/updateAddress/{refNo}", newAddress, String.class, refNo);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void whenUpdateAddress_thenUpdateParticipantAndReturnOldAddress() {
        // given
        Participant participant = createParticipantWithRandomValues();
        String refNo = addParticipant(participant);
        String newAddress = randomString();

        // when
        ResponseEntity<String> responseEntity = restTemplate
                .postForEntity(baseUri + "/updateAddress/{refNo}", newAddress, String.class, refNo);

        assertThat(responseEntity.getBody()).isEqualTo(participant.getAddress());
        assertThat(getParticipant(refNo).getBody().getAddress()).isEqualTo(newAddress);
    }

    @Test
    public void whenDelete_andNoParticipant_thenReturnNotFound() {
        // given
        String refNo = "foo";

        ResponseEntity<Participant> responseEntity = restTemplate
                .postForEntity(baseUri + "/delete/{refNo}", null, Participant.class, refNo);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void whenDelete_thenDeleteAndReturnTheParticipant() {
        // given
        Participant participant = createParticipantWithRandomValues();
        String refNo = addParticipant(participant);

        // when
        ResponseEntity<Participant> responseEntity = restTemplate
                .postForEntity(baseUri + "/delete/{refNo}", null, Participant.class, refNo);

        participant.setReferenceNumber(refNo);
        assertThat(responseEntity.getBody()).isEqualTo(participant);
        assertThat(getParticipant(refNo).getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    private Participant createParticipantWithRandomValues() {
        return new Participant(randomString(), new Date(), randomString(), randomString());
    }

    private String addParticipant(Participant participant) {
        HttpEntity<Participant> entity = new HttpEntity<>(participant);
        return restTemplate.postForObject(baseUri + "/add", entity, String.class);
    }

    private ResponseEntity<Participant> getParticipant(String refNo) {
        return restTemplate.getForEntity(baseUri + "/get/{refNo}", Participant.class, refNo);
    }

    private static String randomString() {
        return UUID.randomUUID().toString();
    }
}
