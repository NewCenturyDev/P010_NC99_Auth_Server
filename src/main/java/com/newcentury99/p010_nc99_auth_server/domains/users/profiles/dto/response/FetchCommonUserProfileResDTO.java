package com.newcentury99.p010_nc99_auth_server.domains.users.profiles.dto.response;

import com.newcentury99.p010_nc99_auth_server.commons.base.crud.dto.response.FetchGeneralResDTO;
import com.newcentury99.p010_nc99_auth_server.commons.http.GeneralPageableResDTO;
import com.newcentury99.p010_nc99_auth_server.domains.users.profiles.entity.CommonUserProfile;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class FetchCommonUserProfileResDTO extends GeneralPageableResDTO implements FetchGeneralResDTO<CommonUserProfile> {
    private List<CommonUserProfile> results;
    private Long resultCounts;
}
