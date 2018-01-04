package com.laushkina.anastasia.orm.models.simple;

import com.laushkina.anastasia.orm.models.IKanji;
import com.laushkina.anastasia.orm.models.enums.Popularity;

import java.util.List;

public class Kanji implements IKanji<Meaning, Reading> {
    private String id;
    private List<Meaning> meanings;
    private List<Reading> readings;
    private String description;
    private Popularity popularity;

    public Kanji(String id, String description, Popularity popularity, List<Meaning> meanings,
                 List<Reading> readings){
        setId(id);
        setDescription(description);
        setPopularity(popularity);
        setReadings(readings);
        setMeanings(meanings);
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

    public Popularity getPopularity() {
        return popularity;
    }

    public void setPopularity(Popularity popularity) {
        this.popularity = popularity;
    }

    public void setMeanings(List<Meaning> meanings) {
        this.meanings = meanings;
    }

    public void setReadings(List<Reading> readings) {
        this.readings = readings;
    }

    public List<Meaning> getMeanings() {
        return meanings;
    }

    public List<Reading> getReadings() {
        return readings;
    }
}
