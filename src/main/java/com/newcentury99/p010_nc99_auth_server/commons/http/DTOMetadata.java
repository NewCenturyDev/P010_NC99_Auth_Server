package com.newcentury99.p010_nc99_auth_server.commons.http;

import lombok.Data;

@Data
public class DTOMetadata {
    private Boolean status;
    private String message;
    private String code;
    private String exception;

    public DTOMetadata(String message) {
        this.status = true;
        this.message = message;
        this.code = null;
        this.exception = null;
    }
    public DTOMetadata(String message, String exception) {
        this.status = false;
        this.message = message;
        this.exception = exception;
    }
}
