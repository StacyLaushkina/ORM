package com.laushkina.anastasia.orm.presenters;

import android.os.Bundle;
import android.util.Log;

import com.laushkina.anastasia.orm.data.KanjiDbFlowFactory;
import com.laushkina.anastasia.orm.data.KanjiGetter;
import com.laushkina.anastasia.orm.data.KanjiGreenDaoFactory;
import com.laushkina.anastasia.orm.data.KanjiSimpleFactory;
import com.laushkina.anastasia.orm.models.IKanji;
import com.laushkina.anastasia.orm.models.TestResult;
import com.laushkina.anastasia.orm.models.dbFlow.KanjiDbFlow;
import com.laushkina.anastasia.orm.models.dbFlow.MeaningDbFlow;
import com.laushkina.anastasia.orm.models.dbFlow.ReadingDbFlow;
import com.laushkina.anastasia.orm.models.greenDao.KanjiGreenDAO;
import com.laushkina.anastasia.orm.models.greenDao.MeaningGreenDAO;
import com.laushkina.anastasia.orm.models.greenDao.ReadingGreenDAO;
import com.laushkina.anastasia.orm.models.simple.Kanji;
import com.laushkina.anastasia.orm.models.simple.Meaning;
import com.laushkina.anastasia.orm.models.simple.Reading;
import com.laushkina.anastasia.orm.repositories.DBFlowRepository;
import com.laushkina.anastasia.orm.repositories.GreenDaoRepository;
import com.laushkina.anastasia.orm.repositories.SimpleRepository;
import com.laushkina.anastasia.orm.services.DbFlowService;
import com.laushkina.anastasia.orm.services.GreenDaoService;
import com.laushkina.anastasia.orm.services.IService;
import com.laushkina.anastasia.orm.services.SimpleService;

import java.util.Calendar;
import java.util.List;

public class MainActivityPresenter {
    private static final String TAG = MainActivityPresenter.class.getName();

    public interface TestProgressObserver {

        /**
         * @param progress is percent from 0 to 100
         */
        void onProgressChanged(int progress);
    }

    private GreenDaoService greenDaoService;
    private DbFlowService dbFlowService;
    private SimpleService simpleService;
    private KanjiGetter<MeaningDbFlow, ReadingDbFlow, KanjiDbFlow> dbFlowKanjiGetter;
    private KanjiGetter<MeaningGreenDAO, ReadingGreenDAO, KanjiGreenDAO> greenDaoKanjiGetter;
    private KanjiGetter<Meaning, Reading, Kanji> kanjiGetter;

    public MainActivityPresenter() {
        greenDaoService = new GreenDaoService(new GreenDaoRepository());
        dbFlowService = new DbFlowService(new DBFlowRepository());
        simpleService = new SimpleService(new SimpleRepository());

        dbFlowKanjiGetter = new KanjiGetter<>(new KanjiDbFlowFactory(), KanjiDbFlow.class);
        greenDaoKanjiGetter = new KanjiGetter<>(new KanjiGreenDaoFactory(), KanjiGreenDAO.class);
        kanjiGetter = new KanjiGetter<>(new KanjiSimpleFactory(), Kanji.class);
    }

    public Bundle startTests(int amount, TestProgressObserver observer){
        try {
            List<KanjiGreenDAO> greenDAOList = greenDaoKanjiGetter.getKanjis(amount);
            TestResult greenDaoResult = startTest(greenDaoService, greenDAOList, observer, 5);

            List<KanjiDbFlow> dbFlowList = dbFlowKanjiGetter.getKanjis(amount);
            TestResult dbFlowResult = startTest(dbFlowService, dbFlowList, observer, 40);

            List<Kanji> kanjiList = kanjiGetter.getKanjis(amount);
            TestResult simpleResult = startTest(simpleService, kanjiList, observer, 65);

            Bundle bundle = new Bundle(3);
            bundle.putSerializable(TestResultPresenter.DB_FLOW_RESULT_EXTRA, dbFlowResult);
            bundle.putSerializable(TestResultPresenter.GREEN_DAO_RESULT_EXTRA, greenDaoResult);
            bundle.putSerializable(TestResultPresenter.SIMPLE_RESULT_EXTRA, simpleResult);
            return bundle;

        } catch (Exception e) {
            Log.e("Test failed", e.getMessage());
            return null;
            // todo show toast
        }
    }

    private <T extends IKanji> TestResult startTest(IService<T> service, List<T> kanjis,
                                                    TestProgressObserver observer, int currentProgress) throws Exception{
        Log.i(TAG, "Started test for: " + service.getClass().getSimpleName());
        service.deleteAll();

        long startTime = Calendar.getInstance().getTimeInMillis();
        service.add(kanjis);
        observer.onProgressChanged(currentProgress + 10);
        Log.i(TAG, "Finished add for: " + service.getClass().getSimpleName());

        long insertTime = Calendar.getInstance().getTimeInMillis();
        List<T> kanji = service.shallowLoad();
        observer.onProgressChanged(currentProgress + 20);
        Log.i(TAG, "Finished shallow load for: " + service.getClass().getSimpleName());

        long shallowLoadTime = Calendar.getInstance().getTimeInMillis();
        service.deepLoad();
        observer.onProgressChanged(currentProgress + 20);
        Log.i(TAG, "Finished deep load for: " + service.getClass().getSimpleName());

        long deepLoadTime = Calendar.getInstance().getTimeInMillis();
        return new TestResult(kanji.size(), insertTime - startTime, shallowLoadTime - insertTime,
                 deepLoadTime - shallowLoadTime);
    }
}
