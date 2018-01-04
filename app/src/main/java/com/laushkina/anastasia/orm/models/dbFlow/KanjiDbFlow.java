package com.laushkina.anastasia.orm.models.dbFlow;

import com.laushkina.anastasia.orm.models.IKanji;
import com.laushkina.anastasia.orm.models.enums.Popularity;
import com.laushkina.anastasia.orm.repositories.db.DbFlowDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.OneToMany;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.List;

@Table(database = DbFlowDatabase.class, name = "Kanjis")
public class KanjiDbFlow extends BaseModel implements IKanji<MeaningDbFlow, ReadingDbFlow> {

    @PrimaryKey
    private String id;

    private List<MeaningDbFlow> meanings;

    private List<ReadingDbFlow> readings;

    @Column
    private String description;

    @Column
    private Popularity popularity;

    public KanjiDbFlow(){}

    public KanjiDbFlow(String id, String description, Popularity popularity,
                       List<MeaningDbFlow> meanings, List<ReadingDbFlow> readings){
        setId(id);
        setDescription(description);
        setPopularity(popularity);
        setReadings(readings);
        setMeanings(meanings);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Popularity getPopularity() {
        return popularity;
    }

    public void setPopularity(Popularity popularity) {
        this.popularity = popularity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @OneToMany(methods = {OneToMany.Method.ALL}, variableName = "meanings")
    public List<MeaningDbFlow> getMeanings() {
        if (meanings == null || meanings.isEmpty()) {
            meanings = SQLite.select()
                    .from(MeaningDbFlow.class)
                    .where(MeaningDbFlow_Table.kanjiId.eq(id))
                    .queryList();
        }
        return meanings;
    }

    public void setMeanings(List<MeaningDbFlow> meanings){
        this.meanings = meanings;
    }

    @OneToMany(methods = {OneToMany.Method.ALL}, variableName = "readings")
    public List<ReadingDbFlow> getReadings() {
        if (readings == null || readings.isEmpty()) {
            readings = SQLite.select()
                    .from(ReadingDbFlow.class)
                    .where(ReadingDbFlow_Table.kanjiId.eq(id))
                    .queryList();
        }
        return readings;
    }

    public void setReadings(List<ReadingDbFlow> readings){
        this.readings = readings;
    }
}
