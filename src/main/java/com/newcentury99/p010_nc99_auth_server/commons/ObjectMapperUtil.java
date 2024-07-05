package com.newcentury99.p010_nc99_auth_server.commons;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class ObjectMapperUtil {
    private static ObjectMapper objectMapper;

    public static ObjectMapper getInstance() {
        if (objectMapper == null) {
            objectMapper = JsonMapper.builder()
                    .addModule(new JavaTimeModule())
                    .configure(DeserializationFeature.USE_LONG_FOR_INTS, true)
                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                    .build();
        }
        return objectMapper;
    }

    public static String encodeToJSONString(Object object) throws JsonProcessingException {
        ObjectMapper mapper = ObjectMapperUtil.getInstance();
        return mapper.writeValueAsString(object);
    }

    public static  <T> T parseFromJSONString(String jsonStr, Class<T> cls) throws JsonProcessingException {
        ObjectMapper mapper = ObjectMapperUtil.getInstance();
        return mapper.readValue(jsonStr, cls);
    }
}
