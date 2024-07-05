package com.newcentury99.p010_nc99_auth_server.domains.users.profiles.dto.request;

import com.newcentury99.p010_nc99_auth_server.commons.http.GeneralPageableResDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class FetchCommonUserProfileReqDTO extends GeneralPageableResDTO {
    private List<UUID> ids;
    private String email;
}
