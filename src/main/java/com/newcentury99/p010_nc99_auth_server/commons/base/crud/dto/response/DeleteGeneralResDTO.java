package com.newcentury99.p010_nc99_auth_server.commons.base.crud.dto.response;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;

public interface DeleteGeneralResDTO {
    List<UUID> getDeletedIDs();
    void setDeletedIDs (List<UUID> ids);
    Long getDeletedCounts();
    void setDeletedCounts(Long deletedCounts);
    default LocalDateTime getDeletedAt() {
        return LocalDateTime.now(ZoneId.of("Asia/Seoul"));
    }
}
