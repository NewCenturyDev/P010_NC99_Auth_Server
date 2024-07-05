package com.newcentury99.p010_nc99_auth_server.domains.users.profiles.dto.response;

import com.newcentury99.p010_nc99_auth_server.commons.base.crud.dto.response.CreateGeneralResDTO;
import com.newcentury99.p010_nc99_auth_server.commons.http.GeneralResDTO;
import com.newcentury99.p010_nc99_auth_server.domains.users.profiles.entity.CommonUserProfile;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CreateCommonUserProfileResDTO extends GeneralResDTO implements CreateGeneralResDTO<CommonUserProfile> {
    private CommonUserProfile created;
}
