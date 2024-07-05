package com.newcentury99.p010_nc99_auth_server.commons.base.crud.dto.response;

import java.time.LocalDateTime;
import java.time.ZoneId;

public interface CreateGeneralResDTO<T> {
    T getCreated();
    void setCreated(T entity);
    default LocalDateTime getCreatedAt() {
        return LocalDateTime.now(ZoneId.of("Asia/Seoul"));
    }
}
