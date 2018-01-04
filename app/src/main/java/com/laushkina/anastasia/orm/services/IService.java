package com.laushkina.anastasia.orm.services;

import com.laushkina.anastasia.orm.models.IKanji;

import java.util.List;

public interface IService<T extends IKanji> {
    void add(List<T> entries);
    List<T> shallowLoad();
    List<T> deepLoad();
    void deleteAll();
}
