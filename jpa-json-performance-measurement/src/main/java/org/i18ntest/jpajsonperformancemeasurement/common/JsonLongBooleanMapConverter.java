package org.i18ntest.jpajsonperformancemeasurement.common;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Converter
@Component
public class JsonLongBooleanMapConverter implements AttributeConverter<HashMap<Long, Boolean>, String> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(HashMap<Long, Boolean> attribute) {
        if (attribute == null) {
            return null;
        }
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (Exception e) {
            throw new RuntimeException("JSON writing error", e);
        }
    }

    @Override
    public HashMap<Long, Boolean> convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return new HashMap<>();
        }
        try {
            return objectMapper.readValue(dbData, new TypeReference<HashMap<Long, Boolean>>() {
            });
        } catch (Exception e) {
            throw new RuntimeException("JSON reading error", e);
        }
    }
}
