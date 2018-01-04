package com.laushkina.anastasia.orm.data;

import com.laushkina.anastasia.orm.models.dbFlow.KanjiDbFlow;
import com.laushkina.anastasia.orm.models.dbFlow.MeaningDbFlow;
import com.laushkina.anastasia.orm.models.dbFlow.ReadingDbFlow;
import com.laushkina.anastasia.orm.models.enums.Popularity;
import com.laushkina.anastasia.orm.models.enums.ReadingType;

import java.util.List;

public class KanjiDbFlowFactory extends KanjiFactory<MeaningDbFlow, ReadingDbFlow, KanjiDbFlow> {

    @Override
    MeaningDbFlow getNewMeaning(String id, String description, String kanjiId) {
        return new MeaningDbFlow(id, description, kanjiId);
    }

    ReadingDbFlow getNewReading(String id, String hiraganaReading, String romanjiReading, ReadingType readingType,
                             String kanjiId) {
        return new ReadingDbFlow(id, hiraganaReading, romanjiReading,readingType, kanjiId);
    }

    KanjiDbFlow getKanji(String id, String description, Popularity popularity, List<MeaningDbFlow> meanings,
                         List<ReadingDbFlow> readings) {
        return new KanjiDbFlow(id, description, popularity, meanings, readings);
    }
}
