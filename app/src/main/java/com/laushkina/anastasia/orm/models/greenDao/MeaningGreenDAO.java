package com.laushkina.anastasia.orm.models.greenDao;

import com.laushkina.anastasia.orm.models.IMeaning;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity(nameInDb = "Meanings")
public class MeaningGreenDAO implements IMeaning {
    @Id
    @Property(nameInDb = "id")
    private String id;

    @NotNull
    @Property(nameInDb = "description")
    private String description;

    @NotNull
    @Property(nameInDb = "kanjiId")
    private String kanjiId;


    @Keep
    public MeaningGreenDAO(String id, String description, String kanjiId){
        setId(id);
        setDescription(description);
        setKanjiId(kanjiId);
    }

    @Generated(hash = 468348031)
    public MeaningGreenDAO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getKanjiId() {
        return kanjiId;
    }

    public void setKanjiId(String kanjiId) {
        this.kanjiId = kanjiId;
    }
}
