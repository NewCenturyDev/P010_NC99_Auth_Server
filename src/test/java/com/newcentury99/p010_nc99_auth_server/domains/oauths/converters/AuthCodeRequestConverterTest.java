package com.newcentury99.p010_nc99_auth_server.domains.oauths.converters;

import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AuthorizationCodeRequestAuthenticationException;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AuthorizationCodeRequestAuthenticationToken;

@WebMvcTest
public class AuthCodeRequestConverterTest {
    @Mock
    private HttpServletRequest request;
    private final AuthCodeRequestConverter authCodeRequestConverter = new AuthCodeRequestConverter();

    @Test
    @DisplayName("Authorization Code 생성 요청 변환 동작 성공 테스트")
    void convertTest() {
        // Given: Mockup Setting
        AuthCodeRequestConvertTestUtil.setHttpRequestServletMockToSuccessCase(this.request);

        // When
        OAuth2AuthorizationCodeRequestAuthenticationToken preAuthToken = (OAuth2AuthorizationCodeRequestAuthenticationToken) authCodeRequestConverter.convert(request);

        // Then
        Assertions.assertEquals("anonymousUser", preAuthToken.getName());
        Assertions.assertEquals(0, preAuthToken.getAuthorities().stream().toList().size());
        Assertions.assertEquals("/oauth2/login", preAuthToken.getAuthorizationUri());
        Assertions.assertEquals("TEST_CLIENT_ID", preAuthToken.getClientId());
        Assertions.assertEquals("https://test.com/", preAuthToken.getRedirectUri());
        Assertions.assertNull(preAuthToken.getState());
        Assertions.assertEquals(0, preAuthToken.getScopes().size());
        Assertions.assertEquals("1", preAuthToken.getAdditionalParameters().get("request_id"));
    }

    @Test
    @DisplayName("Authorization Code 생성 요청 변환 동작 실패 테스트 - responseType null 에러")
    void convertFailureCase1Test() {
        // Given: Mockup Setting
        AuthCodeRequestConvertTestUtil.setHttpRequestServletMockToFailureCase1(this.request);

        // When + Then
        OAuth2AuthorizationCodeRequestAuthenticationException e = Assertions.assertThrows(OAuth2AuthorizationCodeRequestAuthenticationException.class, () -> authCodeRequestConverter.convert(request));
        Assertions.assertEquals("OAuth 2.0 Parameter Error: " + OAuth2ParameterNames.RESPONSE_TYPE, e.getLocalizedMessage());
    }

    @Test
    @DisplayName("Authorization Code 생성 요청 변환 동작 실패 테스트 - responseType blank 에러")
    void convertFailureCase2Test() {
        // Given: Mockup Setting
        AuthCodeRequestConvertTestUtil.setHttpRequestServletMockToFailureCase2(this.request);

        // When + Then
        OAuth2AuthorizationCodeRequestAuthenticationException e = Assertions.assertThrows(OAuth2AuthorizationCodeRequestAuthenticationException.class, () -> authCodeRequestConverter.convert(request));
        Assertions.assertEquals("OAuth 2.0 Parameter Error: " + OAuth2ParameterNames.RESPONSE_TYPE, e.getLocalizedMessage());
    }

    @Test
    @DisplayName("Authorization Code 생성 요청 변환 동작 실패 테스트 - responseType 불일치 에러")
    void convertFailureCase3Test() {
        // Given: Mockup Setting
        AuthCodeRequestConvertTestUtil.setHttpRequestServletMockToFailureCase3(this.request);

        // When + Then
        OAuth2AuthorizationCodeRequestAuthenticationException e = Assertions.assertThrows(OAuth2AuthorizationCodeRequestAuthenticationException.class, () -> authCodeRequestConverter.convert(request));
        Assertions.assertEquals("OAuth 2.0 Parameter Error: " + OAuth2ParameterNames.RESPONSE_TYPE, e.getLocalizedMessage());
    }

    @Test
    @DisplayName("Authorization Code 생성 요청 변환 동작 실패 테스트 - clientId null 에러")
    void convertFailureCase4Test() {
        // Given: Mockup Setting
        AuthCodeRequestConvertTestUtil.setHttpRequestServletMockToFailureCase4(this.request);

        // When + Then
        OAuth2AuthorizationCodeRequestAuthenticationException e = Assertions.assertThrows(OAuth2AuthorizationCodeRequestAuthenticationException.class, () -> authCodeRequestConverter.convert(request));
        Assertions.assertEquals("OAuth 2.0 Parameter Error: " + OAuth2ParameterNames.CLIENT_ID, e.getLocalizedMessage());
    }

    @Test
    @DisplayName("Authorization Code 생성 요청 변환 동작 실패 테스트 - clientId blank 에러")
    void convertFailureCase5Test() {
        // Given: Mockup Setting
        AuthCodeRequestConvertTestUtil.setHttpRequestServletMockToFailureCase5(this.request);

        // When + Then
        OAuth2AuthorizationCodeRequestAuthenticationException e = Assertions.assertThrows(OAuth2AuthorizationCodeRequestAuthenticationException.class, () -> authCodeRequestConverter.convert(request));
        Assertions.assertEquals("OAuth 2.0 Parameter Error: " + OAuth2ParameterNames.CLIENT_ID, e.getLocalizedMessage());
    }

    @Test
    @DisplayName("Authorization Code 생성 요청 변환 동작 실패 테스트 - redirectURI null 에러")
    void convertFailureCase6Test() {
        // Given: Mockup Setting
        AuthCodeRequestConvertTestUtil.setHttpRequestServletMockToFailureCase6(this.request);

        // When + Then
        OAuth2AuthorizationCodeRequestAuthenticationException e = Assertions.assertThrows(OAuth2AuthorizationCodeRequestAuthenticationException.class, () -> authCodeRequestConverter.convert(request));
        Assertions.assertEquals("OAuth 2.0 Parameter Error: " + OAuth2ParameterNames.REDIRECT_URI, e.getLocalizedMessage());
    }

    @Test
    @DisplayName("Authoization Code 생성 요청 변환 동작 실패 테스트 - redirectURI blank 에러")
    void convertFailureCase7Test() {
        // Given: Mockup Setting
        AuthCodeRequestConvertTestUtil.setHttpRequestServletMockToFailureCase7(this.request);

        // When + Then
        OAuth2AuthorizationCodeRequestAuthenticationException e = Assertions.assertThrows(OAuth2AuthorizationCodeRequestAuthenticationException.class, () -> authCodeRequestConverter.convert(request));
        Assertions.assertEquals("OAuth 2.0 Parameter Error: " + OAuth2ParameterNames.REDIRECT_URI, e.getLocalizedMessage());
    }
}
