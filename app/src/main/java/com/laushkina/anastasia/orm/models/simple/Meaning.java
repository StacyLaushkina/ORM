package com.laushkina.anastasia.orm.models.simple;

import com.laushkina.anastasia.orm.models.IMeaning;

public class Meaning implements IMeaning {
    private String id;
    private String description;
    private String kanjiId;

    public Meaning(String id, String description, String kanjiId){
        setId(id);
        setDescription(description);
        setKanjiId(kanjiId);
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
