package com.newcentury99.p010_nc99_auth_server.domains.oauths.converters;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2ErrorCodes;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationResponseType;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AuthorizationCodeRequestAuthenticationException;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AuthorizationCodeRequestAuthenticationToken;
import org.springframework.security.web.authentication.AuthenticationConverter;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class AuthCodeRequestConverter implements AuthenticationConverter {
    private static final String errorURI = "/oauth2/error";

    @Override
    public Authentication convert(HttpServletRequest request) {
        String responseType = request.getParameter(OAuth2ParameterNames.RESPONSE_TYPE);
        String clientId = request.getParameter(OAuth2ParameterNames.CLIENT_ID);
        String redirectURI = request.getParameter(OAuth2ParameterNames.REDIRECT_URI);
        Map<String, Object> additionalParam = new HashMap<>();
        additionalParam.put("request_id", request.getParameter("request_id"));

        // 파라미터 null 체크
        if (responseType == null || responseType.isBlank()) {
            throwError(OAuth2ErrorCodes.INVALID_REQUEST, OAuth2ParameterNames.RESPONSE_TYPE);
        }
        if (!Objects.requireNonNull(responseType).equals(OAuth2AuthorizationResponseType.CODE.getValue())) {
            throwError(OAuth2ErrorCodes.UNSUPPORTED_RESPONSE_TYPE, OAuth2ParameterNames.RESPONSE_TYPE);
        } else if (clientId == null || clientId.isBlank()) {
            throwError(OAuth2ErrorCodes.INVALID_REQUEST, OAuth2ParameterNames.CLIENT_ID);
        }
        if (redirectURI == null || redirectURI.isBlank()) {
            throwError(OAuth2ErrorCodes.INVALID_REQUEST, OAuth2ParameterNames.REDIRECT_URI);
        }
        AnonymousAuthenticationToken preAuthToken = new AnonymousAuthenticationToken("anonymous", "anonymousUser", AuthorityUtils.createAuthorityList("ROLE_ANONYMOUS"));
        return new OAuth2AuthorizationCodeRequestAuthenticationToken("/oauth2/login", clientId, preAuthToken, redirectURI, null, null, additionalParam);
    }

    private static void throwError(String errorCode, String parameterName) {
        OAuth2Error error = new OAuth2Error(errorCode, "OAuth 2.0 Parameter Error: " + parameterName, errorURI);
        throw new OAuth2AuthorizationCodeRequestAuthenticationException(error, null);
    }
}
