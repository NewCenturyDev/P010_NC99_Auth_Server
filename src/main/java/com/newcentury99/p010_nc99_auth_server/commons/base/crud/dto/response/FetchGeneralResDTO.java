package com.newcentury99.p010_nc99_auth_server.commons.base.crud.dto.response;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;

public interface FetchGeneralResDTO<T> {
    List<T> getResults();
    void setResults(List<T> entities);
    Long getResultCounts();
    void setResultCounts(Long counts);
    default LocalDateTime getFetchedAt() {
        return LocalDateTime.now(ZoneId.of("Asia/Seoul"));
    }
}
