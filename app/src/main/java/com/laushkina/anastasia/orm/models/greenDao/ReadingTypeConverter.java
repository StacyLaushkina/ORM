package com.laushkina.anastasia.orm.models.greenDao;

import com.laushkina.anastasia.orm.models.enums.ReadingType;

import org.greenrobot.greendao.converter.PropertyConverter;

public class ReadingTypeConverter implements PropertyConverter<ReadingType, Integer> {
    @Override
    public ReadingType convertToEntityProperty(Integer databaseValue) {
        return databaseValue == null ? null : ReadingType.parseValue(databaseValue);
    }

    @Override
    public Integer convertToDatabaseValue(ReadingType entityProperty) {
        return entityProperty == null ? null : entityProperty.getValue();
    }
}
