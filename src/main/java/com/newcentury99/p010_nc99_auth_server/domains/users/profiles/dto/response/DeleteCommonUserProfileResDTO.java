package com.newcentury99.p010_nc99_auth_server.domains.users.profiles.dto.response;

import com.newcentury99.p010_nc99_auth_server.commons.base.crud.dto.response.DeleteGeneralResDTO;
import com.newcentury99.p010_nc99_auth_server.commons.http.GeneralResDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class DeleteCommonUserProfileResDTO extends GeneralResDTO implements DeleteGeneralResDTO {
    private List<UUID> deletedIDs;
    private Long deletedCounts;
}
