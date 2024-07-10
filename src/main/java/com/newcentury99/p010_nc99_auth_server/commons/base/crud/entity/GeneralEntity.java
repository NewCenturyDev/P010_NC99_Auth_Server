package com.newcentury99.p010_nc99_auth_server.commons.base.crud.entity;

import com.newcentury99.p010_nc99_auth_server.commons.base.crud.dto.request.CreateGeneralReqDTO;

import java.lang.reflect.Field;

public interface GeneralEntity<T, DTO extends CreateGeneralReqDTO> {
    T fromCreateDTO(DTO createReqDTO);
    default <F> void updateField(T target, String fieldName, F fieldValue) throws NoSuchFieldException, IllegalAccessException {
        Field targetField = this.getClass().getDeclaredField(fieldName);
        targetField.setAccessible(true);
        targetField.set(target, fieldValue);
        targetField.setAccessible(false);
    }
    default void resetField(T target, String fieldName) throws NoSuchFieldException, IllegalAccessException {
        Field targetField = this.getClass().getDeclaredField(fieldName);
        targetField.setAccessible(true);
        targetField.set(target, null);
        targetField.setAccessible(false);
    }
}
