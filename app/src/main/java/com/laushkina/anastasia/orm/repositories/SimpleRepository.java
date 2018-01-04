package com.laushkina.anastasia.orm.repositories;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.laushkina.anastasia.orm.models.enums.Popularity;
import com.laushkina.anastasia.orm.models.enums.ReadingType;
import com.laushkina.anastasia.orm.models.simple.Kanji;
import com.laushkina.anastasia.orm.models.simple.Meaning;
import com.laushkina.anastasia.orm.models.simple.Reading;
import com.laushkina.anastasia.orm.repositories.db.DbManager;

import java.util.ArrayList;
import java.util.List;

public class SimpleRepository implements ISimpleRepository {

    private static final String KanjiTableName = "Kanjis";

    private static final class KanjiColumns {
        static final String id = "id";
        static final String description = "description";
        static final String popularity = "popularity";
    }

    private static final String ReadingTableName = "Readings";
    private static final class ReadingColumns {
        static final String id = "id";
        static final String hiraganaReading = "hiraganaReading";
        static final String romanjiReading = "romanjiReading";
        static final String readingType = "readingType";
        static final String kanjiId = "kanjiId";
    }

    private static final String MeaningTableName = "Meanings";
    private static final class MeaningColumns {
        static final String id = "id";
        static final String description = "description";
        static final String kanjiId = "kanjiId";
    }

    @Override
    public void add(List<Kanji> kanjis) {
        SQLiteDatabase db = DbManager.getInstance().getDatabase();

        String sqlKanji = "INSERT INTO " + KanjiTableName + " (" + KanjiColumns.id + ", " +
                                                              KanjiColumns.description + ", " +
                                                              KanjiColumns.popularity + ") VALUES (?, ?, ?)";

        String sqlReading = "INSERT INTO " + ReadingTableName + " (" + ReadingColumns.id + ", " +
                                                                ReadingColumns.hiraganaReading + ", " +
                                                                ReadingColumns.romanjiReading + ", " +
                                                                ReadingColumns.readingType + ", " +
                                                                ReadingColumns.kanjiId + ") VALUES (?, ?, ?, ?, ?)";
        String sqlMeaning = "INSERT INTO " + MeaningTableName + " (" + MeaningColumns.id + ", " +
                MeaningColumns.description + ", " +
                MeaningColumns.kanjiId + ") VALUES (?, ?, ?)";
        SQLiteStatement kanjiStatement = db.compileStatement(sqlKanji);
        SQLiteStatement readingStatement = db.compileStatement(sqlReading);
        SQLiteStatement meaningStatement = db.compileStatement(sqlMeaning);
        db.beginTransaction();

        try {
            for (Kanji kanji : kanjis) {
                bindKanjiValues(kanjiStatement, kanji);
                kanjiStatement.execute();

                for (Meaning meaning : kanji.getMeanings()) {
                    bindMeaningValues(meaningStatement, meaning);
                    meaningStatement.execute();
                }
                for (Reading reading : kanji.getReadings()) {
                    bindReadingValues(readingStatement, reading);
                    readingStatement.execute();
                }
            }
            kanjiStatement.close();
            meaningStatement.close();
            readingStatement.close();
            db.setTransactionSuccessful();
        } catch (Exception ex) {
            Log.e("Simple DB add: ", ex.getLocalizedMessage());
        } finally {
            db.endTransaction();
        }
    }

    @Override
    public List<Kanji> getAll() {
        List<Kanji> result = new ArrayList<>();
        try (SQLiteDatabase db = DbManager.getInstance().getDatabase()) {
            Cursor c = db.query(KanjiTableName, getKanjiColumns(), null, null,
                                null, null, null);
            if (c.moveToFirst()) {
                do {
                    Kanji kanji = getKanjiFromCursor(c);
                    kanji.setMeanings(getMeaningsByKanjiId(db, kanji.getId()));
                    kanji.setReadings(getReadingsByKanjiId(db, kanji.getId()));
                    result.add(kanji);
                } while (c.moveToNext());
            }
            c.close();
        } catch (Exception ex) {
            Log.e("Error getAll", ex.getLocalizedMessage());
        }
        return result;
    }

    @Override
    public List<Kanji> shallowLoad() {
        List<Kanji> result = new ArrayList<>();
        try (SQLiteDatabase db = DbManager.getInstance().getDatabase()) {
            Cursor c = db.query(KanjiTableName, getKanjiColumns(), null, null,
                    null, null, null);
            if (c.moveToFirst()) {
                do {
                    result.add(getKanjiFromCursor(c));
                } while (c.moveToNext());
            }
            c.close();
        } catch (Exception ex) {
            Log.e("Error getAll", ex.getLocalizedMessage());
        }
        return result;
    }

    @Override
    public void deleteAll() {
        SQLiteDatabase db = DbManager.getInstance().getDatabase();
        db.delete(KanjiTableName, null, null);
    }

    private List<Meaning> getMeaningsByKanjiId(SQLiteDatabase db, String kanjiId) throws Exception{
        List<Meaning> result = new ArrayList<>();

        String whereClause = MeaningColumns.kanjiId + "= ?";
        String[] whereArgs = new String[] { kanjiId };

        Cursor c = db.query(MeaningTableName, getMeaningColumns(), whereClause, whereArgs,
                null, null, null);
        if (c.moveToFirst()) {
            do {
                result.add(getMeaningFromCursor(c));
            } while (c.moveToNext());
        }
        c.close();
        return result;
    }

    private List<Reading> getReadingsByKanjiId(SQLiteDatabase db, String kanjiId) throws Exception{
        List<Reading> result = new ArrayList<>();

        String whereClause = ReadingColumns.kanjiId + "= ?";
        String[] whereArgs = new String[] { kanjiId };

        Cursor c = db.query(ReadingTableName, getReadingColumns(), whereClause, whereArgs,
                null, null, null);
        if (c.moveToFirst()) {
            do {
                result.add(getReadingFromCursor(c));
            } while (c.moveToNext());
        }
        c.close();
        return result;
    }

    private void bindKanjiValues(SQLiteStatement statement, Kanji kanji){
        statement.bindString(1, kanji.getId());
        statement.bindString(2, kanji.getDescription());
        if (kanji.getPopularity() == null) return;

        statement.bindLong(3,  kanji.getPopularity().getValue());
    }

    private void bindReadingValues(SQLiteStatement statement, Reading reading){
        statement.bindString(1, reading.getId());
        statement.bindString(2, reading.getHiraganaReading());
        statement.bindString(3, reading.getRomanjiReading());
        statement.bindString(4, reading.getKanjiId());
        if (reading.getReadingType() == null) return;

        statement.bindLong(5, reading.getReadingType().getValue());
    }

    private void bindMeaningValues(SQLiteStatement statement, Meaning meaning){
        statement.bindString(1, meaning.getId());
        statement.bindString(2, meaning.getDescription());
        statement.bindString(3, meaning.getKanjiId());
    }

    private Kanji getKanjiFromCursor(Cursor cursor){
        String id = cursor.getString(cursor.getColumnIndexOrThrow(KanjiColumns.id));
        String description = cursor.getString(cursor.getColumnIndexOrThrow(KanjiColumns.description));
        Integer popularity = cursor.getInt(cursor.getColumnIndexOrThrow(KanjiColumns.popularity));
        return new Kanji(id, description, Popularity.parseValue(popularity), null, null);
    }

    private String[] getKanjiColumns(){
        return new String[]{KanjiColumns.id, KanjiColumns.description, KanjiColumns.popularity};
    }

    private Reading getReadingFromCursor(Cursor cursor){
        String id = cursor.getString(cursor.getColumnIndexOrThrow(ReadingColumns.id));
        String hiraganaReading = cursor.getString(cursor.getColumnIndexOrThrow(ReadingColumns.hiraganaReading));
        String romanjiReading = cursor.getString(cursor.getColumnIndexOrThrow(ReadingColumns.romanjiReading));
        Integer readingType = cursor.getInt(cursor.getColumnIndexOrThrow(ReadingColumns.readingType));
        String kanjiId = cursor.getString(cursor.getColumnIndexOrThrow(ReadingColumns.kanjiId));
        return new Reading(id, hiraganaReading, romanjiReading, ReadingType.parseValue(readingType), kanjiId);
    }

    private String[] getReadingColumns(){
        return new String[]{ReadingColumns.id, ReadingColumns.hiraganaReading, ReadingColumns.romanjiReading,
                ReadingColumns.readingType, ReadingColumns.kanjiId};
    }

    private Meaning getMeaningFromCursor(Cursor cursor){
        String id = cursor.getString(cursor.getColumnIndexOrThrow(MeaningColumns.id));
        String description = cursor.getString(cursor.getColumnIndexOrThrow(MeaningColumns.description));
        String kanjiId = cursor.getString(cursor.getColumnIndexOrThrow(MeaningColumns.kanjiId));
        return new Meaning(id, description, kanjiId);
    }

    private String[] getMeaningColumns(){
        return new String[]{MeaningColumns.id, MeaningColumns.description, MeaningColumns.kanjiId};
    }
}
