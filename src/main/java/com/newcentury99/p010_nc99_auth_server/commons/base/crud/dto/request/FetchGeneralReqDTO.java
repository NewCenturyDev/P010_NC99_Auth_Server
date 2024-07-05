package com.newcentury99.p010_nc99_auth_server.commons.base.crud.dto.request;

import java.util.List;
import java.util.UUID;

public interface FetchGeneralReqDTO {
    UUID getId();
    void setId(UUID id);
    List<UUID> getIds();
    void setIds(List<UUID> ids);
}
