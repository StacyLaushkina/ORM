package com.laushkina.anastasia.orm.services;

import com.laushkina.anastasia.orm.models.greenDao.KanjiGreenDAO;
import com.laushkina.anastasia.orm.repositories.IRepository;

import java.util.List;

public class GreenDaoService implements IService<KanjiGreenDAO>{
    private IRepository<KanjiGreenDAO> repository;

    public GreenDaoService(IRepository<KanjiGreenDAO> repository) {
        this.repository = repository;
    }

    public void deleteAll(){
        repository.deleteAll();
    }

    public void add(List<KanjiGreenDAO> kanjis) {
        repository.add(kanjis);
    }

    public List<KanjiGreenDAO> shallowLoad(){
        return repository.getAll();
    }

    public List<KanjiGreenDAO> deepLoad(){
        List<KanjiGreenDAO> kanjis = repository.getAll();
        for (KanjiGreenDAO kanji : kanjis) {
            kanji.getMeanings();
            kanji.getReadings();
        }
        return kanjis;
    }
}
