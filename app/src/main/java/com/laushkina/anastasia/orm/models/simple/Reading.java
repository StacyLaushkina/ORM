package com.laushkina.anastasia.orm.models.simple;

import com.laushkina.anastasia.orm.models.IReading;
import com.laushkina.anastasia.orm.models.enums.ReadingType;

public class Reading implements IReading {
    private String id;
    private String hiraganaReading;
    private String romanjiReading;
    private ReadingType readingType;
    private String kanjiId;

    public Reading(String id, String hiraganaReading, String romanjiReading, ReadingType readingType, String kanjiId){
        setId(id);
        setHiraganaReading(hiraganaReading);
        setRomanjiReading(romanjiReading);
        setReadingType(readingType);
        setKanjiId(kanjiId);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHiraganaReading() {
        return hiraganaReading;
    }

    public void setHiraganaReading(String hiraganaReading) {
        this.hiraganaReading = hiraganaReading;
    }

    public String getRomanjiReading() {
        return romanjiReading;
    }

    public void setRomanjiReading(String romanjiReading) {
        this.romanjiReading = romanjiReading;
    }

    public ReadingType getReadingType() {
        return readingType;
    }

    public void setReadingType(ReadingType readingType) {
        this.readingType = readingType;
    }

    public String getKanjiId() {
        return kanjiId;
    }

    public void setKanjiId(String kanjiId) {
        this.kanjiId = kanjiId;
    }
}
