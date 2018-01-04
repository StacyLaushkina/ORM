package com.laushkina.anastasia.orm.services;

import com.laushkina.anastasia.orm.models.dbFlow.KanjiDbFlow;
import com.laushkina.anastasia.orm.repositories.IRepository;

import java.util.ArrayList;
import java.util.List;

public class DbFlowService implements IService<KanjiDbFlow>{
    private IRepository<KanjiDbFlow> repository;

    public DbFlowService(IRepository<KanjiDbFlow> repository) {
        this.repository = repository;
    }

    public void deleteAll(){
        repository.deleteAll();
    }

    public void add(List<KanjiDbFlow> kanjis) {
        repository.add(kanjis);
    }

    public List<KanjiDbFlow> shallowLoad(){
        // DB flow always load child entities when load from db
        return new ArrayList<>();
    }

    public List<KanjiDbFlow> deepLoad(){
        return repository.getAll();
    }
}
