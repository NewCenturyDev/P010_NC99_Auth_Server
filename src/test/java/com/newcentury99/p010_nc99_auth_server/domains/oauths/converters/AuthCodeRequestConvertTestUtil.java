package com.newcentury99.p010_nc99_auth_server.domains.oauths.converters;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationResponseType;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;

import static org.mockito.Mockito.when;

public class AuthCodeRequestConvertTestUtil {
    static void setHttpRequestServletMockToSuccessCase(HttpServletRequest request) {
        when(request.getParameter(OAuth2ParameterNames.RESPONSE_TYPE)).thenReturn(OAuth2AuthorizationResponseType.CODE.getValue());
        when(request.getParameter(OAuth2ParameterNames.CLIENT_ID)).thenReturn("TEST_CLIENT_ID");
        when(request.getParameter(OAuth2ParameterNames.REDIRECT_URI)).thenReturn("https://test.com/");
        when(request.getParameter("request_id")).thenReturn("1");
    }

    static void setHttpRequestServletMockToFailureCase1(HttpServletRequest request) {
        when(request.getParameter(OAuth2ParameterNames.RESPONSE_TYPE)).thenReturn(null);
        when(request.getParameter(OAuth2ParameterNames.CLIENT_ID)).thenReturn("TEST_CLIENT_ID");
        when(request.getParameter(OAuth2ParameterNames.REDIRECT_URI)).thenReturn("https://test.com/");
        when(request.getParameter("request_id")).thenReturn("1");
    }

    static void setHttpRequestServletMockToFailureCase2(HttpServletRequest request) {
        when(request.getParameter(OAuth2ParameterNames.RESPONSE_TYPE)).thenReturn("");
        when(request.getParameter(OAuth2ParameterNames.CLIENT_ID)).thenReturn("TEST_CLIENT_ID");
        when(request.getParameter(OAuth2ParameterNames.REDIRECT_URI)).thenReturn("https://test.com/");
        when(request.getParameter("request_id")).thenReturn("1");
    }

    static void setHttpRequestServletMockToFailureCase3(HttpServletRequest request) {
        when(request.getParameter(OAuth2ParameterNames.RESPONSE_TYPE)).thenReturn("WRONG_TYPE");
        when(request.getParameter(OAuth2ParameterNames.CLIENT_ID)).thenReturn("TEST_CLIENT_ID");
        when(request.getParameter(OAuth2ParameterNames.REDIRECT_URI)).thenReturn("https://test.com/");
        when(request.getParameter("request_id")).thenReturn("1");
    }

    static void setHttpRequestServletMockToFailureCase4(HttpServletRequest request) {
        when(request.getParameter(OAuth2ParameterNames.RESPONSE_TYPE)).thenReturn(OAuth2AuthorizationResponseType.CODE.getValue());
        when(request.getParameter(OAuth2ParameterNames.CLIENT_ID)).thenReturn(null);
        when(request.getParameter(OAuth2ParameterNames.REDIRECT_URI)).thenReturn("https://test.com/");
        when(request.getParameter("request_id")).thenReturn("1");
    }

    static void setHttpRequestServletMockToFailureCase5(HttpServletRequest request) {
        when(request.getParameter(OAuth2ParameterNames.RESPONSE_TYPE)).thenReturn(OAuth2AuthorizationResponseType.CODE.getValue());
        when(request.getParameter(OAuth2ParameterNames.CLIENT_ID)).thenReturn("");
        when(request.getParameter(OAuth2ParameterNames.REDIRECT_URI)).thenReturn("https://test.com/");
        when(request.getParameter("request_id")).thenReturn("1");
    }

    static void setHttpRequestServletMockToFailureCase6(HttpServletRequest request) {
        when(request.getParameter(OAuth2ParameterNames.RESPONSE_TYPE)).thenReturn(OAuth2AuthorizationResponseType.CODE.getValue());
        when(request.getParameter(OAuth2ParameterNames.CLIENT_ID)).thenReturn("TEST_CLIENT_ID");
        when(request.getParameter(OAuth2ParameterNames.REDIRECT_URI)).thenReturn(null);
        when(request.getParameter("request_id")).thenReturn("1");
    }

    static void setHttpRequestServletMockToFailureCase7(HttpServletRequest request) {
        when(request.getParameter(OAuth2ParameterNames.RESPONSE_TYPE)).thenReturn(OAuth2AuthorizationResponseType.CODE.getValue());
        when(request.getParameter(OAuth2ParameterNames.CLIENT_ID)).thenReturn("TEST_CLIENT_ID");
        when(request.getParameter(OAuth2ParameterNames.REDIRECT_URI)).thenReturn("");
        when(request.getParameter("request_id")).thenReturn("1");
    }
}
