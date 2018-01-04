package com.laushkina.anastasia.orm.repositories.db;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbManager {
   private SQLiteOpenHelper helper;

   private static DbManager instance;

   public void init(SQLiteOpenHelper helper){
       this.helper = helper;
   }

   public static synchronized DbManager getInstance(){
       if (instance == null) {
           instance = new DbManager();
       }
       return instance;
   }

   public SQLiteDatabase getDatabase(){
       return helper.getReadableDatabase();
   }
}
