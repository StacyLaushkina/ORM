package com.laushkina.anastasia.orm.repositories;

import java.util.List;

public interface IRepository<T> {
    void add(List<T> kanjis);
    List<T> getAll();
    void deleteAll();
}
