package com.newcentury99.p010_nc99_auth_server.commons.base.crud.dto.response;

import java.time.LocalDateTime;
import java.time.ZoneId;

public interface UpdateGeneralResDTO<T> {
    T getUpdated();
    void setUpdated(T entity);
    default LocalDateTime getUpdatedAt() {
        return LocalDateTime.now(ZoneId.of("Asia/Seoul"));
    }
}
