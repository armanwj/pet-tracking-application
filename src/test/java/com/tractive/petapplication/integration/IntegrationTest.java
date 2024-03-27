package com.tractive.petapplication.integration;

import com.tractive.petapplication.dto.PetDto;
import com.tractive.petapplication.entity.PetType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testInsertPet() {
        PetDto dto = new PetDto();
        dto.setPetType(PetType.CAT);
        ResponseEntity<PetDto> response =
                restTemplate.postForEntity("http://localhost:" + port + "/api/pets", dto, PetDto.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(PetType.CAT, response.getBody().getPetType());
    }
}
