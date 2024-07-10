package com.newcentury99.p010_nc99_auth_server.domains.users.profiles.entity;

import com.newcentury99.p010_nc99_auth_server.commons.base.SupportedLanguage;
import com.newcentury99.p010_nc99_auth_server.domains.users.profiles.dto.request.CreateCommonUserProfileReqDTO;
import com.newcentury99.p010_nc99_auth_server.library_temp.security.PermissionScope;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

public class CommonUserProfileTest {
    @Test
    @DisplayName("생성 DTO 불러오기 성공 테스트 케이스")
    void fromCreateDTO() {
        // Given
        CommonUserProfile sampleUserProfile = new CommonUserProfile();
        CreateCommonUserProfileReqDTO sampleReqDTO = getSampleCreateCommonUserProfileReqDTO();

        // When
        sampleUserProfile.fromCreateDTO(sampleReqDTO);
        CommonUserProfile result = sampleUserProfile.fromCreateDTO(sampleReqDTO);

        // Then
        Assertions.assertEquals(sampleReqDTO.getEmail(), result.getEmail());
        Assertions.assertEquals(sampleReqDTO.getPassword(), result.getPassword());
        Assertions.assertEquals(sampleReqDTO.getLanguage().toLocale(), result.getLanguage());
        Assertions.assertEquals(sampleReqDTO.getName(), result.getName());
        Assertions.assertEquals(sampleReqDTO.getBirth(), result.getBirth());
        Assertions.assertEquals(sampleReqDTO.getGender(), result.getGender());
        Assertions.assertEquals(sampleReqDTO.getDefaultPhone(), result.getPhone().getPhone());
        Assertions.assertIterableEquals(sampleReqDTO.getScopes(), result.getScopes());
    }

    private static CreateCommonUserProfileReqDTO getSampleCreateCommonUserProfileReqDTO() {
        CreateCommonUserProfileReqDTO sampleReqDTO = new CreateCommonUserProfileReqDTO();
        sampleReqDTO.setEmail("example@test.com");
        sampleReqDTO.setPassword("password");
        sampleReqDTO.setLanguage(SupportedLanguage.ko);
        sampleReqDTO.setName("TEST");
        sampleReqDTO.setBirth(LocalDate.of(1999, 5, 27));
        sampleReqDTO.setGender(UserGender.MALE);
        sampleReqDTO.setDefaultPhone("010-1234-5678");
        sampleReqDTO.setScopes(List.of(new PermissionScope("TEST", "GROUP", "SERVICE", "ADMIN")));
        return sampleReqDTO;
    }
}
