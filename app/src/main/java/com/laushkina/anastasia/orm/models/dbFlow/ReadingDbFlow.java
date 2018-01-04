package com.laushkina.anastasia.orm.models.dbFlow;

import com.laushkina.anastasia.orm.models.IReading;
import com.laushkina.anastasia.orm.models.enums.ReadingType;
import com.laushkina.anastasia.orm.repositories.db.DbFlowDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(database = DbFlowDatabase.class)
public class ReadingDbFlow extends BaseModel implements IReading{

    @PrimaryKey
    private String id;

    @Column
    private String hiraganaReading;

    @Column
    private String romanjiReading;

    @Column
    private ReadingType readingType;

    @Column
    private String kanjiId;

    public ReadingDbFlow(){}

    public ReadingDbFlow(String id, String hiraganaReading, String romanjiReading, ReadingType readingType, String kanjiId){
        setId(id);
        setHiraganaReading(hiraganaReading);
        setRomanjiReading(romanjiReading);
        setReadingType(readingType);
        setKanjiId(kanjiId);
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
