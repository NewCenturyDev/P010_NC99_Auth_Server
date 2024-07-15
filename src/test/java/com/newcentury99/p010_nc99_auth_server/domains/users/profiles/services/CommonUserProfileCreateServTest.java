package com.newcentury99.p010_nc99_auth_server.domains.users.profiles.services;

import com.newcentury99.p010_nc99_auth_server.domains.users.profiles.dto.request.CreateCommonUserProfileReqDTO;
import com.newcentury99.p010_nc99_auth_server.domains.users.profiles.entity.UserGender;
import com.newcentury99.p010_nc99_auth_server.domains.users.profiles.repository.MockCommonUserProfileRepo;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CommonUserProfileCreateServTest {
    private final CreateCommonUserProfileReqDTO sampleReqDTO;
    private final CommonUserProfileCreateServ createServ;

    public CommonUserProfileCreateServTest() {
        this.createServ = new CommonUserProfileCreateServ(new MockCommonUserProfileRepo());
        this.sampleReqDTO = new CreateCommonUserProfileReqDTO();
        sampleReqDTO.setEmail("test@example.com");
        sampleReqDTO.setUsername("testUser");
        sampleReqDTO.setName("test");
        sampleReqDTO.setCountry("ko");
        sampleReqDTO.setBirth(LocalDate.of(1999, 5, 27));
        sampleReqDTO.setGender(UserGender.MALE);
        sampleReqDTO.setDefaultPhone("010-1234-5678");
        sampleReqDTO.setMobilePhone("010-1111-2222");
    }

    @Test
    void createTest() {
        // Given

        // When

        // Then
        assertDoesNotThrow(() -> createServ.create(sampleReqDTO));
        assertEquals("test@example.com", sampleReqDTO.getEmail());
    }

    @Test
    void createsTest() {
    }

    @Test
    void createEntityStorageTest() {
    }
}