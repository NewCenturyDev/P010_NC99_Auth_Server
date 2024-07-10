package com.newcentury99.p010_nc99_auth_server.domains.users.profiles.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

class CommonUserProfileUtilsTest {
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final CommonUserProfileUtils sampleCommonUserProfileUtils = new CommonUserProfileUtils(passwordEncoder);

    @Test
    void encodePassword() {
        // Given
        String samplePassword = "test";
        String encodedSamplePassword = sampleCommonUserProfileUtils.encodePassword(samplePassword);

        // When
        boolean isPasswordHashMatch = passwordEncoder.matches(samplePassword, encodedSamplePassword);

        // Then
        Assertions.assertTrue(isPasswordHashMatch);
    }
}
