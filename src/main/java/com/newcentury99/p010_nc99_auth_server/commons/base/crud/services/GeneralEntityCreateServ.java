package com.newcentury99.p010_nc99_auth_server.commons.base.crud.services;

import com.newcentury99.p010_nc99_auth_server.commons.base.crud.dto.request.CreateGeneralReqDTO;

import java.util.List;

public interface GeneralEntityCreateServ<T, DTO extends CreateGeneralReqDTO> {
    T create(DTO reqDTO);
    List<T> creates(List<DTO> reqDTOs);
    void createEntityStorage();
}
