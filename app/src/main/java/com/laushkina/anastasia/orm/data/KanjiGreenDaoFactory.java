package com.laushkina.anastasia.orm.data;

import com.laushkina.anastasia.orm.models.enums.Popularity;
import com.laushkina.anastasia.orm.models.enums.ReadingType;
import com.laushkina.anastasia.orm.models.greenDao.KanjiGreenDAO;
import com.laushkina.anastasia.orm.models.greenDao.MeaningGreenDAO;
import com.laushkina.anastasia.orm.models.greenDao.ReadingGreenDAO;

import java.util.List;

public class KanjiGreenDaoFactory extends KanjiFactory<MeaningGreenDAO, ReadingGreenDAO, KanjiGreenDAO> {

    MeaningGreenDAO getNewMeaning(String id, String description, String kanjiId) {
        return new MeaningGreenDAO(id, description, kanjiId);
    }

    ReadingGreenDAO getNewReading(String id, String hiraganaReading, String romanjiReading, ReadingType readingType,
                             String kanjiId) {
        return new ReadingGreenDAO(id, hiraganaReading, romanjiReading,readingType, kanjiId);
    }

    KanjiGreenDAO getKanji(String id, String description, Popularity popularity, List<MeaningGreenDAO> meanings,
                        List<ReadingGreenDAO> readings) {
        return new KanjiGreenDAO(id, description, popularity, meanings, readings);
    }
}
