package com.newcentury99.p010_nc99_auth_server.library_temp.entity;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.newcentury99.p010_nc99_auth_server.library_temp.LibraryAPI;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@LibraryAPI
@Converter
public class JsonColumnConverter<T> implements AttributeConverter<T, String> {
    protected final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(T object) {
        if (object != null) {
            try {
                return objectMapper.writeValueAsString(object);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            return null;
        }
    }

    @Override
    public T convertToEntityAttribute(String jsonColumnStr) {
        try {
            return objectMapper.readValue(jsonColumnStr, new TypeReference<>() {});
        } catch (Exception e) {
            return null;
        }
    }
}
