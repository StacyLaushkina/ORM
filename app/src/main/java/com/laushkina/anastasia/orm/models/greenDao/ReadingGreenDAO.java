package com.laushkina.anastasia.orm.models.greenDao;

import com.laushkina.anastasia.orm.models.IReading;
import com.laushkina.anastasia.orm.models.enums.ReadingType;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity(nameInDb = "Readings")
public class ReadingGreenDAO implements IReading {
    @Id
    @Property(nameInDb = "id")
    private String id;

    @NotNull
    @Property(nameInDb = "hiraganaReading")
    private String hiraganaReading;

    @NotNull
    @Property(nameInDb = "romanjiReading")
    private String romanjiReading;

    @NotNull
    @Property(nameInDb = "readingType")
    @Convert(converter = ReadingTypeConverter.class, columnType = Integer.class)
    private ReadingType readingType;

    @NotNull
    @Property(nameInDb = "kanjiId")
    private String kanjiId;

    @Keep
    public ReadingGreenDAO(String id, String hiraganaReading, String romanjiReading, ReadingType readingType, String kanjiId){
        setId(id);
        setHiraganaReading(hiraganaReading);
        setRomanjiReading(romanjiReading);
        setReadingType(readingType);
        setKanjiId(kanjiId);
    }

    @Generated(hash = 518357405)
    public ReadingGreenDAO() {
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
