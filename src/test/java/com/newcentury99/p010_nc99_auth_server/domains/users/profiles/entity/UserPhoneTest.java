package com.newcentury99.p010_nc99_auth_server.domains.users.profiles.entity;

import com.newcentury99.p010_nc99_auth_server.library_temp.messages.SDKError;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class UserPhoneTest {
    private String samplePhoneNumber;

    @Test
    @DisplayName("휴대전화번호 양식 검증 성공 테스트 케이스")
    void checkMobilePhoneTest() {
        // Given
        samplePhoneNumber = "010-1234-5678";

        // When
        UserPhone.checkMobilePhone(samplePhoneNumber);

        // Then
        Assertions.assertDoesNotThrow(() -> new IllegalArgumentException());
    }

    @Test
    @DisplayName("휴대전화번호 양식 검증 실패 테스트 케이스 - 대쉬 삽입이 되지 않음")
    void checkMobilePhoneFailureCase1Test() {
        // Given
        samplePhoneNumber = "01012345678";

        // When / Then
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> UserPhone.checkMobilePhone(samplePhoneNumber), SDKError.ERROR_AUTH_MS_ILLEGAL_MOBILE_PHONE_FORMAT.getMessage()
        );
    }

    @Test
    @DisplayName("휴대전화번호 양식 검증 실패 테스트 케이스2 - 자릿수 에러")
    void checkMobilePhoneFailureCase2Test() {
        // Given
        samplePhoneNumber = "010-1234-56789";

        // When / Then
        Assertions.assertThrows(IllegalArgumentException.class, () -> UserPhone.checkMobilePhone(samplePhoneNumber));
    }

    @Test
    @DisplayName("휴대전화번호 양식 검증 실패 테스트 케이스3 - 자릿수 에러(주로 국가코드처리시 생략된 0 처리가 미흡한 Case)")
    void checkMobilePhoneFailureCase3Test() {
        // Given
        samplePhoneNumber = "10-1234-5678";

        // When / Then
        Assertions.assertThrows(IllegalArgumentException.class, () -> UserPhone.checkMobilePhone(samplePhoneNumber));
    }
    
    @Test
    @DisplayName("유선전화번호 양식 검증 성공 테스트 케이스 - 서울 유선전화번호")
    void checkLandlinePhoneTest() {
        // Given
        samplePhoneNumber = "02-1234-5678";

        // When
        UserPhone.checkLandlinePhone(samplePhoneNumber);

        // Then
        Assertions.assertDoesNotThrow(() -> new IllegalArgumentException());
    }

    @Test
    @DisplayName("유선전화번호 양식 검증 성공 테스트 케이스2 - 비서울 4자리국번 유선전화번호 (경기/인천 일부 Case)")
    void checkLandlinePhoneTest2() {
        // Given
        samplePhoneNumber = "031-1234-5678";

        // When
        UserPhone.checkLandlinePhone(samplePhoneNumber);

        // Then
        Assertions.assertDoesNotThrow(() -> new IllegalArgumentException());
    }


    @Test
    @DisplayName("유선전화번호 양식 검증 성공 테스트 케이스3 - 비서울 3자리국번 유선전화번호")
    void checkLandlinePhoneTest3() {
        // Given
        samplePhoneNumber = "031-123-5678";

        // When
        UserPhone.checkLandlinePhone(samplePhoneNumber);

        // Then
        Assertions.assertDoesNotThrow(() -> new IllegalArgumentException());
    }

    @Test
    @DisplayName("유선전화번호 양식 검증 성공 테스트 케이스4 - 대표전화번호")
    void checkLandlinePhoneTest4() {
        // Given
        samplePhoneNumber = "1588-1234";

        // When
        UserPhone.checkLandlinePhone(samplePhoneNumber);

        // Then
        Assertions.assertDoesNotThrow(() -> new IllegalArgumentException());
    }

    @Test
    @DisplayName("유선전화번호 양식 검증 성공 테스트 케이스5 - 인터넷전화번호")
    void checkLandlinePhoneTest5() {
        // Given
        samplePhoneNumber = "070-1234-5678";

        // When
        UserPhone.checkLandlinePhone(samplePhoneNumber);

        // Then
        Assertions.assertDoesNotThrow(() -> new IllegalArgumentException());
    }

    @Test
    @DisplayName("유선전화번호 양식 검증 실패 테스트 케이스 - 대쉬 미삽입 에러")
    void checkLandlinePhoneFailureCase1Test() {
        // Given
        samplePhoneNumber = "0212345678";

        // When / Then
        Assertions.assertThrows(IllegalArgumentException.class, () -> UserPhone.checkLandlinePhone(samplePhoneNumber));
    }

    @Test
    @DisplayName("유선전화번호 양식 검증 실패 테스트 케이스2 - 자릿수 에러")
    void checkLandlinePhoneFailureCase2Test() {
        // Given
        samplePhoneNumber = "031-1234-56789";

        // When / Then
        Assertions.assertThrows(IllegalArgumentException.class, () -> UserPhone.checkLandlinePhone(samplePhoneNumber));
    }

    @Test
    @DisplayName("유선전화번호 양식 검증 실패 테스트 케이스3 - 자릿수 에러(서울시 제외 지역코드 자릿수 에러)")
    void checkLandlinePhoneFailureCase3Test() {
        // Given
        samplePhoneNumber = "10-1234-5678";

        // When / Then
        Assertions.assertThrows(IllegalArgumentException.class, () -> UserPhone.checkLandlinePhone(samplePhoneNumber));
    }


    @Test
    @DisplayName("유선전화번호 양식 검증 실패 테스트 케이스4 - 자릿수 에러(대표전화번호 국번삽입 에러)")
    void checkLandlinePhoneFailureCase4Test() {
        // Given
        samplePhoneNumber = "1588-1234-5678";

        // When / Then
        Assertions.assertThrows(IllegalArgumentException.class, () -> UserPhone.checkLandlinePhone(samplePhoneNumber));
    }
}
