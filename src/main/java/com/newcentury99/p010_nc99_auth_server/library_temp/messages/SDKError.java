package com.newcentury99.p010_nc99_auth_server.library_temp.messages;

import com.newcentury99.p010_nc99_auth_server.library_temp.LibraryAPI;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.NoSuchElementException;

@Getter
@AllArgsConstructor
public enum SDKError {
    /* 공통 에러 코드 */
    ERROR_GENERAL_UNKNOWN_FATAL("000", "ERROR_GENERAL_UNKNOWN_FATAL"),
    ERROR_GENERAL_UNKNOWN_MSA_API("001", "ERROR_GENERAL_UNKNOWN_MSA_API"),
    ERROR_GENERAL_UNKNOWN_MESSAGE_QUEUE("002", "ERROR_GENERAL_UNKNOWN_MESSAGE_QUEUE"),
    ERROR_GENERAL_UNKNOWN_EXTERNAL_API("003", "ERROR_GENERAL_UNKNOWN_EXTERNAL_API"),
    ERROR_GENERAL_UNKNOWN_NO_RESPOND("004", "ERROR_GENERAL_UNKNOWN_NO_RESPOND"),
    ERROR_REQUEST_UNKNOWN_REASON("100", "ERROR_REQUEST_UNKNOWN_REASON"),
    ERROR_REQUEST_OTHER_MS_JSON_NOT_PARSEABLE("101", "ERROR_REQUEST_OTHER_MS_JSON_NOT_PARSEABLE"),
    ERROR_REQUEST_CLIENT_JSON_NOT_PARSEABLE("102", "ERROR_REQUEST_CLIENT_JSON_NOT_PARSEABLE"),
    ERROR_REQUEST_PAGE_SIZE_LIMIT_EXCEED("103", "ERROR_REQUEST_PAGE_SIZE_LIMIT_EXCEED"),
    ERROR_REQUEST_PAGE_INDEX_OUT_OF_RANGE("104", "ERROR_REQUEST_PAGE_INDEX_OUT_OF_RANGE"),
    ERROR_REQUEST_FILE_WRITE_EMPTY("110", "ERROR_REQUEST_FILE_WRITE_EMPTY"),
    ERROR_REQUEST_FILE_WRITE_NOT_SUPPORTED_EXT("111", "ERROR_REQUEST_FILE_WRITE_NOT_SUPPORTED_EXT"),
    ERROR_REQUEST_FILE_WRITE_EXCEED_SIZE_LIMIT("112", "ERROR_REQUEST_FILE_WRITE_EXCEED_SIZE_LIMIT"),
    ERROR_REQUEST_FILE_WRITE_DIRECTORY_CREATE_FAILURE("113", "ERROR_REQUEST_FILE_WRITE_DIRECTORY_CREATE_FAILURE"),
    ERROR_SECURITY_UNKNOWN_REASON("200", "ERROR_SECURITY_UNKNOWN_REASON"),
    ERROR_SECURITY_OTHER_MS_REFUSED("201", "ERROR_SECURITY_OTHER_MS_REFUSED"),
    ERROR_SECURITY_NO_PERMISSION_FOR_TARGET_MS("202", "ERROR_SECURITY_NO_PERMISSION_FOR_TARGET_MS"),
    ERROR_USER_UNKNOWN_REASON("300", "ERROR_USER_UNKNOWN_REASON"),
    ERROR_AI_UNKNOWN_REASON("500", "ERROR_AI_UNKNOWN_REASON"),
    ERROR_DB_UNKNOWN_REASON("600", "ERROR_DB_UNKNOWN_REASON"),
    ERROR_DB_ENTITY_NOT_EXIST("610", "ERROR_DB_ENTITY_NOT_EXIST"),
    ERROR_DB_CHILD_ENTITY_NOT_EXIST("611", "ERROR_DB_CHILD_ENTITY_NOT_EXIST"),
    ERROR_DB_COLUMN_NOT_EXISTS("615", "ERROR_DB_COLUMN_NOT_EXISTS"),
    ERROR_API_UNKNOWN_REASON("700", "ERROR_API_UNKNOWN_REASON"),
    ERROR_API_OTHER_MS_INTERNAL_SERVER("701", "ERROR_API_OTHER_MS_INTERNAL_SERVER"),
    ERROR_FILE_NOT_EXISTS("710", "ERROR_FILE_NOT_EXISTS"),
    ERROR_FILE_TYPE_NOT_SUPPORTED("711", "ERROR_FILE_TYPE_NOT_SUPPORTED"),
    ERROR_DEPENDENCY_UNKNOWN_REASON("800", "ERROR_DEPENDENCY_UNKNOWN_REASON"),
    ERROR_DEPENDENCY_NAVER_MAP_API("801", "ERROR_DEPENDENCY_NAVER_MAP_API"),
    
    /* Auth 마이크로서비스 전용 에러 코드 */
    ERROR_AUTH_MS_ILLEGAL_MOBILE_PHONE_FORMAT("150", "ERROR_AUTH_MS_ILLEGAL_MOBILE_PHONE_FORMAT"),
    ERROR_AUTH_MS_ILLEGAL_LANDLINE_PHONE_FORMAT("151", "ERROR_AUTH_MS_ILLEGAL_LANDLINE_PHONE_FORMAT");


    private final String code;
    private final String message;

    @LibraryAPI
    public String getCodeForMsg(String message) {
        return Arrays.stream(SDKError.values()).filter(e -> e.getMessage().equals(message)).findFirst()
                .orElseThrow(() -> new NoSuchElementException("Message Not Exists")).getCode();
    }

    @LibraryAPI
    public String getMessage() {
        return this.message;
    }

    @LibraryAPI
    public String getMessage(Object[] arguments) {
        return MessageFormat.format(this.message, arguments);
    }
}
