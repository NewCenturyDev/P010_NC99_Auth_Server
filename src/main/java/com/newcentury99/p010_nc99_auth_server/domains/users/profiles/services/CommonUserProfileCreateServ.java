package com.newcentury99.p010_nc99_auth_server.domains.users.profiles.services;

import com.newcentury99.p010_nc99_auth_server.commons.base.crud.services.GeneralEntityCreateServ;
import com.newcentury99.p010_nc99_auth_server.domains.users.profiles.dto.request.CreateCommonUserProfileReqDTO;
import com.newcentury99.p010_nc99_auth_server.domains.users.profiles.entity.CommonUserProfile;
import com.newcentury99.p010_nc99_auth_server.domains.users.profiles.repository.CommonUserProfileRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CommonUserProfileCreateServ implements GeneralEntityCreateServ<CommonUserProfile, CreateCommonUserProfileReqDTO> {
    private final CommonUserProfileRepo repo;

    @Override
    public CommonUserProfile create(CreateCommonUserProfileReqDTO reqDTO) {
        CommonUserProfile newProfile = new CommonUserProfile();
        newProfile = newProfile.fromCreateDTO(reqDTO);
        return repo.save(newProfile);
    }

    @Override
    public List<CommonUserProfile> creates(List<CreateCommonUserProfileReqDTO> reqDTOs) {
        List<CommonUserProfile> newProfiles = new ArrayList<>();
        for (CreateCommonUserProfileReqDTO reqDTO : reqDTOs) {
            newProfiles.add(this.create(reqDTO));
        }
        return repo.saveAll(newProfiles);
    }

    @Override
    public void createEntityStorage() {

    }
}
