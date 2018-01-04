package com.laushkina.anastasia.orm.services;

import com.laushkina.anastasia.orm.models.simple.Kanji;
import com.laushkina.anastasia.orm.repositories.IRepository;
import com.laushkina.anastasia.orm.repositories.ISimpleRepository;

import java.util.List;

public class SimpleService implements IService<Kanji>{
    private ISimpleRepository repository;

    public SimpleService(ISimpleRepository repository) {
        this.repository = repository;
    }

    public void deleteAll(){
        repository.deleteAll();
    }

    public void add(List<Kanji> kanjis) {
        repository.add(kanjis);
    }

    public List<Kanji> shallowLoad(){
        return repository.shallowLoad();
    }

    public List<Kanji> deepLoad(){
        return repository.getAll();
    }
}
