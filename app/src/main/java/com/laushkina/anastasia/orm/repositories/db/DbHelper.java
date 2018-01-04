package com.laushkina.anastasia.orm.repositories.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.laushkina.anastasia.orm.R;

import java.io.IOException;
import java.io.InputStream;

public class DbHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "KanjiDB.db";
    private Context context;

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    public void onCreate(SQLiteDatabase db) {
        try {
            InputStream migrationFile = context.getResources().openRawResource(R.raw.db_migration_1);
            byte[] migrationBytes = new byte[migrationFile.available()];
            migrationFile.read(migrationBytes);
            db.execSQL("CREATE TABLE Kanjis( id TEXT PRIMARY KEY,\n" +
                    "                     description TEXT,\n" +
                    "                     popularity INT NOT NULL);");

            db.execSQL("CREATE TABLE Meanings( id TEXT PRIMARY KEY,\n" +
                    "                       description TEXT NOT NULL,\n" +
                    "                       kanjiId INT NOT NULL,\n" +
                    "                       FOREIGN KEY (kanjiId) REFERENCES Kanjis(id) ON DELETE CASCADE);");

            db.execSQL("CREATE TABLE Readings( id TEXT PRIMARY KEY,\n" +
                    "                      hiraganaReading TEXT NOT NULL,\n" +
                    "                      romanjiReading INT NOT NULL,\n" +
                    "                      readingType INT NOT NULL,\n" +
                    "                      kanjiId INT NOT NULL,\n" +
                    "                      FOREIGN KEY (kanjiId) REFERENCES Kanjis(id) ON DELETE CASCADE);");
        } catch (IOException ignored) {
            Log.e("DbHelper", ignored.getMessage());
        }
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
