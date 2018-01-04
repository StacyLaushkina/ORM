package com.laushkina.anastasia.orm.presenters;

import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.laushkina.anastasia.orm.models.TestResult;

import java.util.ArrayList;

public class TestResultPresenter {
    public static final String RESULT_EXTRA = "TestResults";
    static final String DB_FLOW_RESULT_EXTRA = "DBFlowResult";
    static final String GREEN_DAO_RESULT_EXTRA = "GreenDaoResult";
    static final String SIMPLE_RESULT_EXTRA = "SimpleResult";


    private TestResult greenDaoResult;
    private TestResult dbFlowResult;
    private TestResult simpleResult;

    public TestResultPresenter(Bundle extras){
        this.greenDaoResult = (TestResult)extras.getSerializable(GREEN_DAO_RESULT_EXTRA);
        this.dbFlowResult = (TestResult)extras.getSerializable(DB_FLOW_RESULT_EXTRA);
        this.simpleResult = (TestResult)extras.getSerializable(SIMPLE_RESULT_EXTRA);
    }

    public BarData getBarData(){
        BarDataSet set1, set2, set3;

        ArrayList<BarEntry> greenDaoValues = new ArrayList<>();
        ArrayList<BarEntry> DBFlowValues = new ArrayList<>();
        ArrayList<BarEntry> manualValues = new ArrayList<>();

        greenDaoValues.add(new BarEntry(0, greenDaoResult.getInsertTime()));
        greenDaoValues.add(new BarEntry(1, greenDaoResult.getShallowLoadTime()));
        greenDaoValues.add(new BarEntry(2, greenDaoResult.getDeepLoadTime()));

        DBFlowValues.add(new BarEntry(0, dbFlowResult.getInsertTime()));
        DBFlowValues.add(new BarEntry(1, dbFlowResult.getShallowLoadTime()));
        DBFlowValues.add(new BarEntry(2, dbFlowResult.getDeepLoadTime()));

        manualValues.add(new BarEntry(0, simpleResult.getInsertTime()));
        manualValues.add(new BarEntry(1, simpleResult.getShallowLoadTime()));
        manualValues.add(new BarEntry(2, simpleResult.getDeepLoadTime()));

        set1 = new BarDataSet(greenDaoValues, "GreenDao");
        set1.setColor(Color.rgb(255, 0, 0));

        set2 = new BarDataSet(DBFlowValues, "DBFlow");
        set2.setColor(Color.rgb(0, 255, 0));

        set3 = new BarDataSet(manualValues, "Manual");
        set3.setColor(Color.rgb(0, 0, 255));

        return new BarData(set1, set2, set3) ;
    }

}
