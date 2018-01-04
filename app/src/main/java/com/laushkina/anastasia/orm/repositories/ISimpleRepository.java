package com.laushkina.anastasia.orm.repositories;

import com.laushkina.anastasia.orm.models.simple.Kanji;

import java.util.List;

public interface ISimpleRepository extends IRepository<Kanji> {
    List<Kanji> shallowLoad();
}
