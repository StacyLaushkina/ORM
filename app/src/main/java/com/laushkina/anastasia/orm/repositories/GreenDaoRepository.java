package com.laushkina.anastasia.orm.repositories;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.laushkina.anastasia.orm.models.greenDao.DaoMaster;
import com.laushkina.anastasia.orm.models.greenDao.KanjiGreenDAO;
import com.laushkina.anastasia.orm.models.greenDao.KanjiGreenDAODao;
import com.laushkina.anastasia.orm.models.greenDao.MeaningGreenDAODao;
import com.laushkina.anastasia.orm.models.greenDao.ReadingGreenDAODao;
import com.laushkina.anastasia.orm.repositories.db.DbManager;

import org.greenrobot.greendao.database.Database;

import java.util.List;

public class GreenDaoRepository implements IRepository<KanjiGreenDAO> {

    @Override
    public void add(List<KanjiGreenDAO> kanjis) {
        KanjiGreenDAODao kanjiDao = getDaoMaster().newSession().getKanjiGreenDAODao();
        MeaningGreenDAODao meaningDao = getDaoMaster().newSession().getMeaningGreenDAODao();
        ReadingGreenDAODao readingDao = getDaoMaster().newSession().getReadingGreenDAODao();

        Database db = kanjiDao.getDatabase();
        db.beginTransaction();

        try {
            for(KanjiGreenDAO kanji : kanjis) {
                kanjiDao.insert(kanji);
                meaningDao.insertInTx(kanji.getMeanings());
                readingDao.insertInTx(kanji.getReadings());
            }

            db.setTransactionSuccessful();
        } catch (Exception ex) {
            Log.e("GreenDao add: ", ex.getLocalizedMessage());
        } finally {
            db.endTransaction();
        }
    }

    @Override
    public List<KanjiGreenDAO> getAll() {
        KanjiGreenDAODao dao = getDaoMaster().newSession().getKanjiGreenDAODao();
        return dao.loadAll();
    }

    @Override
    public void deleteAll() {
        KanjiGreenDAODao kanjiDao = getDaoMaster().newSession().getKanjiGreenDAODao();
        kanjiDao.deleteAll();
    }

    private DaoMaster getDaoMaster(){
        SQLiteDatabase db = DbManager.getInstance().getDatabase();
        return new DaoMaster(db);
    }
}
