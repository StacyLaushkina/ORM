package com.laushkina.anastasia.orm.data;

import com.google.gson.Gson;
import com.laushkina.anastasia.orm.models.IKanji;
import com.laushkina.anastasia.orm.models.IMeaning;
import com.laushkina.anastasia.orm.models.IReading;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class KanjiGetter<M extends IMeaning, R extends IReading, K extends IKanji<M, R>> {

    private KanjiFactory<M, R, K> kanjiFactory;
    private Type k;

    public KanjiGetter(KanjiFactory<M, R, K> kanjiFactory, Type k){
        this.kanjiFactory = kanjiFactory;
        this.k = k;
    }

    public List<K> getKanjis(Integer amount){
        if (amount == null || amount == 0) return new ArrayList<>();

        List<K> result = new ArrayList<>();
        Gson gson = new Gson();
        for (int i = 0; i < amount; i++) {
            result.add(getCopy(kanjiFactory.getNext(), gson));
        }
        return result;
    }

    private K getCopy(K kanji, Gson gson){
        String json = gson.toJson(kanji);
        return gson.fromJson(json, k);
    }
}
