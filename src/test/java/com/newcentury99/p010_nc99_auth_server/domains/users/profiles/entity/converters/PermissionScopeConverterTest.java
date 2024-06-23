package com.newcentury99.p010_nc99_auth_server.domains.users.profiles.entity.converters;

import com.newcentury99.p010_nc99_auth_server.library_temp.security.PermissionScope;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class PermissionScopeConverterTest {
    PermissionScopeConverter converter = new PermissionScopeConverter();
    List<PermissionScope> sampleScopes;
    String sampleScopeJsonStr;

    @Test
    @DisplayName("퍼미션 정보 객체 -> DB테이블 JSON 스트링 단일객체 변환 테스트")
    void convertToDatabaseColumnTest() {
        // Given
        sampleScopes = List.of(new PermissionScope("GLOBAL", "ROOT", "MASTER", "ADMIN"));

        // When
        sampleScopeJsonStr = converter.convertToDatabaseColumn(sampleScopes);

        // Then
        Assertions.assertEquals("[\"GLOBAL_ROOT_MASTER_ADMIN\"]", sampleScopeJsonStr);
    }

    @Test
    @DisplayName("퍼미션 정보 객체 -> DB테이블 JSON 스트링 복수객체 변환 테스트")
    void convertToDatabaseColumnMultipleTest() {
        // Given
        sampleScopes = List.of(
                new PermissionScope("GLOBAL", "ROOT", "MASTER", "ADMIN"),
                new PermissionScope("GLOBAL", "CORE", "AUTH", "ADMIN")
        );

        // When
        sampleScopeJsonStr = converter.convertToDatabaseColumn(sampleScopes);

        // Then
        Assertions.assertEquals("[\"GLOBAL_ROOT_MASTER_ADMIN\",\"GLOBAL_CORE_AUTH_ADMIN\"]", sampleScopeJsonStr);

    }

    @Test
    @DisplayName("퍼미션 정보 객체 -> DB테이블 JSON 스트링 빈 객체 변환 테스트")
    void convertToDatabaseColumnEmptyTest() {
        // Given
        sampleScopes = List.of();

        // When
        sampleScopeJsonStr = converter.convertToDatabaseColumn(sampleScopes);

        // Then
        Assertions.assertEquals("[]", sampleScopeJsonStr);
    }

    @Test
    @DisplayName("DB테이블 JSON 스트링 -> 퍼미션 정보 단일객체 변환 테스트")
    void convertToEntityAttributeTest() {
        // Given
        sampleScopeJsonStr = "[\"GLOBAL_ROOT_MASTER_ADMIN\"]";

        // When
        sampleScopes = converter.convertToEntityAttribute(sampleScopeJsonStr);

        // Then
        Assertions.assertEquals(1, sampleScopes.size());
        Assertions.assertEquals("GLOBAL_ROOT_MASTER_ADMIN", sampleScopes.getFirst().getAuthority());
        Assertions.assertEquals("GLOBAL", sampleScopes.getFirst().getServiceArea());
        Assertions.assertEquals("ROOT", sampleScopes.getFirst().getServiceGroup());
        Assertions.assertEquals("MASTER", sampleScopes.getFirst().getServiceName());
        Assertions.assertEquals("ADMIN", sampleScopes.getFirst().getPermission());
    }

    @Test
    @DisplayName("DB테이블 JSON 스트링 -> 퍼미션 정보 복수객체 변환 테스트")
    void convertToEntityAttributeMultipleTest() {
        // Given
        sampleScopeJsonStr = "[\"GLOBAL_ROOT_MASTER_ADMIN\",\"GLOBAL_CORE_AUTH_ADMIN\"]";

        // When
        sampleScopes = converter.convertToEntityAttribute(sampleScopeJsonStr);

        // Then
        Assertions.assertEquals(2, sampleScopes.size());
        Assertions.assertEquals("GLOBAL_ROOT_MASTER_ADMIN", sampleScopes.getFirst().getAuthority());
        Assertions.assertEquals("GLOBAL_CORE_AUTH_ADMIN", sampleScopes.get(1).getAuthority());
    }

    @Test
    @DisplayName("DB테이블 JSON 스트링 -> 퍼미션 정보 빈 객체 변환 테스트")
    void convertToEntityAttributeEmptyTest() {
        // Given
        sampleScopeJsonStr = "[]";

        // When
        sampleScopes = converter.convertToEntityAttribute(sampleScopeJsonStr);

        // Then
        Assertions.assertEquals(0, sampleScopes.size());
    }
}
