package com.newcentury99.p010_nc99_auth_server.domains.users.profiles.entity.converters;

import com.fasterxml.jackson.core.type.TypeReference;
import com.newcentury99.p010_nc99_auth_server.library_temp.entity.JsonColumnListConverter;
import com.newcentury99.p010_nc99_auth_server.library_temp.security.PermissionScope;

import java.util.List;
import java.util.stream.Collectors;

public class PermissionScopeConverter extends JsonColumnListConverter<PermissionScope> {
    @Override
    public String convertToDatabaseColumn(List<PermissionScope> objectList) {
        if (objectList != null && !objectList.isEmpty()) {
            try {
                return objectMapper.writeValueAsString(objectList.stream().map(PermissionScope::getAuthority).collect(Collectors.toList()));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            return "[]";
        }
    }

    @Override
    public List<PermissionScope> convertToEntityAttribute(String jsonColumnStr) {
        try {
            List<String> scopeStrings = objectMapper.readValue(jsonColumnStr, new TypeReference<>() {
            });
            return scopeStrings.stream().map(PermissionScope::new).collect(Collectors.toList());
        } catch (Exception e) {
            return null;
        }
    }
}
