package com.laushkina.anastasia.orm.models.greenDao;

import com.laushkina.anastasia.orm.models.enums.Popularity;

import org.greenrobot.greendao.converter.PropertyConverter;

public class PopularityConverter implements PropertyConverter<Popularity, Integer> {
    @Override
    public Popularity convertToEntityProperty(Integer databaseValue) {
        return databaseValue == null ? null : Popularity.parseValue(databaseValue);
    }

    @Override
    public Integer convertToDatabaseValue(Popularity entityProperty) {
        return entityProperty == null ? null : entityProperty.getValue();
    }
}
