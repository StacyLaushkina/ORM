package com.laushkina.anastasia.orm.data;

import com.laushkina.anastasia.orm.models.GUID;
import com.laushkina.anastasia.orm.models.IKanji;
import com.laushkina.anastasia.orm.models.IMeaning;
import com.laushkina.anastasia.orm.models.IReading;
import com.laushkina.anastasia.orm.models.enums.Popularity;
import com.laushkina.anastasia.orm.models.enums.ReadingType;

import java.util.ArrayList;
import java.util.List;

abstract class KanjiFactory<M extends IMeaning, R extends IReading, K extends IKanji<M, R>> {

    private static final int DICTIONARY_SIZE = 3;
    private int i;

    KanjiFactory(){
        i = -1;
    }

    K getNext(){
        i += 1;
        i = i % DICTIONARY_SIZE;
        switch (i) {
            case 0: return getTreeKanji();
            case 1: return getTalkKanji();
            case 2: return getMoonKanji();
            default:
                return getTreeKanji();
        }
    }

    private K getTreeKanji(){
        String kanjiId = GUID.getNew();
        List<M> meanings = new ArrayList<>();

        meanings.add(getNewMeaning(GUID.getNew(), "tree", kanjiId));
        meanings.add(getNewMeaning(GUID.getNew(), "wood", kanjiId));
        meanings.add(getNewMeaning(GUID.getNew(), "bush", kanjiId));
        meanings.add(getNewMeaning(GUID.getNew(), "shrub", kanjiId));

        List<R> readings = new ArrayList<>();
        readings.add(getNewReading(GUID.getNew(), "き", "ki", ReadingType.Japanese, kanjiId));
        readings.add(getNewReading(GUID.getNew(), "モク", "mon", ReadingType.Chinese, kanjiId));

        return getKanji(kanjiId, "", Popularity.Unpopular, meanings, readings);
    }

    private K getMoonKanji(){
        String kanjiId = GUID.getNew();

        List<M> meanings = new ArrayList<>();
        meanings.add(getNewMeaning(GUID.getNew(), "moon", kanjiId));
        meanings.add(getNewMeaning(GUID.getNew(), "month", kanjiId));

        List<R> readings = new ArrayList<>();
        readings.add(getNewReading(GUID.getNew(), "つき", "tsuki", ReadingType.Japanese, kanjiId));
        readings.add(getNewReading(GUID.getNew(), "ガツ", "gatsu", ReadingType.Chinese, kanjiId));
        readings.add(getNewReading(GUID.getNew(), "ゲツ", "getsu", ReadingType.Chinese,  kanjiId));

        return getKanji(kanjiId, "", Popularity.VeryPopular, meanings, readings);
    }

    private K getTalkKanji(){
        String kanjiId = GUID.getNew();

        List<M> meanings = new ArrayList<>();
        meanings.add(getNewMeaning(GUID.getNew(), "talk", kanjiId));
        meanings.add(getNewMeaning(GUID.getNew(), "speak", kanjiId));
        meanings.add(getNewMeaning(GUID.getNew(), "discuss", kanjiId));
        meanings.add(getNewMeaning(GUID.getNew(), "explain", kanjiId));

        List<R> readings = new ArrayList<>();
        readings.add(getNewReading(GUID.getNew(), "はなし", "hanashi", ReadingType.Japanese, kanjiId));
        readings.add(getNewReading(GUID.getNew(), "はなす", "hanasu", ReadingType.Japanese, kanjiId));
        readings.add(getNewReading(GUID.getNew(), "ワ", "wa", ReadingType.Chinese, kanjiId));

        return getKanji(kanjiId, "", Popularity.Popular, meanings, readings);
    }

    abstract M getNewMeaning(String id, String description, String kanjiId);
    abstract R getNewReading(String id, String hiraganaReading, String romanjiReading, ReadingType readingType,
                             String kanjiId);
    abstract K getKanji(String id, String description, Popularity popularity, List<M> meanings,
                        List<R> readings);
}
