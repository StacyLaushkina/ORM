package com.laushkina.anastasia.orm.data;

import com.laushkina.anastasia.orm.models.enums.Popularity;
import com.laushkina.anastasia.orm.models.enums.ReadingType;
import com.laushkina.anastasia.orm.models.simple.Kanji;
import com.laushkina.anastasia.orm.models.simple.Meaning;
import com.laushkina.anastasia.orm.models.simple.Reading;

import java.util.List;

public class KanjiSimpleFactory extends KanjiFactory<Meaning, Reading, Kanji> {

    Meaning getNewMeaning(String id, String description, String kanjiId) {
        return new Meaning(id, description, kanjiId);
    }

    Reading getNewReading(String id, String hiraganaReading, String romanjiReading, ReadingType readingType,
                             String kanjiId) {
        return new Reading(id, hiraganaReading, romanjiReading,readingType, kanjiId);
    }

    Kanji getKanji(String id, String description, Popularity popularity, List<Meaning> meanings,
                        List<Reading> readings) {
        return new Kanji(id, description, popularity, meanings, readings);
    }
}
