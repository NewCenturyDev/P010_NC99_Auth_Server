package com.newcentury99.p010_nc99_auth_server.commons.base.crud.entity;

import com.newcentury99.p010_nc99_auth_server.commons.base.crud.dto.request.CreateGeneralReqDTO;
import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GeneralEntityTest {

    @Setter
    @Getter
    static class SampleEntityCreateReqDTO implements CreateGeneralReqDTO {
        private String sample;
    }

    @Getter
    @Setter
    static class SampleEntity implements GeneralEntity<SampleEntity, SampleEntityCreateReqDTO> {
        private String sample;

        @Override
        public SampleEntity fromCreateDTO(SampleEntityCreateReqDTO createReqDTO) {
            this.sample = createReqDTO.getSample();
            return this;
        }
    }

    private final SampleEntity sampleGeneralEntity = new SampleEntity();

    @Test
    @DisplayName("필드 초기화 성공 테스트 케이스")
    void resetFieldTest() throws NoSuchFieldException, IllegalAccessException {
        // Given
        sampleGeneralEntity.setSample("Test");

        // When
        sampleGeneralEntity.resetField(sampleGeneralEntity, "sample");

        // Then
        Assertions.assertNull(sampleGeneralEntity.getSample());
    }

    @Test
    @DisplayName("필드 초기화 실패 테스트 케이스 - 존재하지 않는 필드")
    void resetFieldFailureCase1Test() {
        // Given
        sampleGeneralEntity.setSample("Test");

        // When/Then
        Assertions.assertThrows(NoSuchFieldException.class,
                () ->  sampleGeneralEntity.resetField(sampleGeneralEntity, "notExists")
        );
    }

    @Test
    @DisplayName("필드 업데이트 성공 테스트 케이스")
    void updateFieldTest() throws NoSuchFieldException, IllegalAccessException {
        // Given
        sampleGeneralEntity.setSample("Test");

        // When
        sampleGeneralEntity.updateField(sampleGeneralEntity, "sample", "Test2");

        // Then
        Assertions.assertEquals("Test2", sampleGeneralEntity.getSample());
    }


    @Test
    @DisplayName("필드 업데이트 실패 테스트 케이스 - 존재하지 않는 필드")
    void updateFieldFailureCase1Test() {
        // Given
        sampleGeneralEntity.setSample("Test");

        // When/Then
        Assertions.assertThrows(NoSuchFieldException.class,
                () ->  sampleGeneralEntity.updateField(sampleGeneralEntity, "notExists", "Test2")
        );
    }
}
