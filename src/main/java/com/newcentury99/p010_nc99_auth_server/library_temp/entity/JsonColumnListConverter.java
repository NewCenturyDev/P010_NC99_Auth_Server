package com.newcentury99.p010_nc99_auth_server.library_temp.entity;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.newcentury99.p010_nc99_auth_server.library_temp.LibraryAPI;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.List;

@LibraryAPI
@Converter
public class JsonColumnListConverter<T> implements AttributeConverter<List<T>, String> {
    protected final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<T> objectList) {
        if (objectList != null && !objectList.isEmpty()) {
            try {
                return objectMapper.writeValueAsString(objectList);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            return "[]";
        }
    }

    @Override
    public List<T> convertToEntityAttribute(String jsonColumnStr) {
        try {
            return objectMapper.readValue(jsonColumnStr, new TypeReference<>() {});
        } catch (Exception e) {
            return null;
        }
    }
}
