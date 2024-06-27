package com.edian.edian_backend.common;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class BooleanToYesNoConverter implements AttributeConverter<Boolean, String> {

    @Override
    public String convertToDatabaseColumn(Boolean attribute) {
        return (attribute != null && attribute) ? "yes" : "no";
    }

    @Override
    public Boolean convertToEntityAttribute(String dbData) {
        return "yes".equalsIgnoreCase(dbData);
    }
}
