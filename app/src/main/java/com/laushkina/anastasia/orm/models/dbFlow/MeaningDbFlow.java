package com.laushkina.anastasia.orm.models.dbFlow;

import com.laushkina.anastasia.orm.models.IMeaning;
import com.laushkina.anastasia.orm.repositories.db.DbFlowDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(database = DbFlowDatabase.class)
public class MeaningDbFlow extends BaseModel implements IMeaning{

    @PrimaryKey
    private String id;

    @Column
    private String description;

    @Column
    private String kanjiId;

    public MeaningDbFlow() {}

    public MeaningDbFlow(String id, String description, String kanjiId){
        setId(id);
        setDescription(description);
        setKanjiId(kanjiId);
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
