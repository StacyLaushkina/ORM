package com.laushkina.anastasia.orm.view;

import android.app.Activity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.formatter.LargeValueFormatter;
import com.laushkina.anastasia.orm.R;
import com.laushkina.anastasia.orm.presenters.TestResultPresenter;

import org.joda.time.Duration;

public class TestsResultActivity extends Activity{

    private TestResultPresenter presenter;

    // Group - related constants
    private static final float groupSpace = 3.0f;
    private static final float barSpace = 0.1f;
    private static final float barWidth = 8.0f;
    private static final int amountOfGroups = 3;
    private static final String[] groupLabels = new String[] {"insertTime", "shallowLoadTime", "deepLoadTime"};

    // Legend - related constants
    private static final float legendXOffset = 10;
    private static final float legendYOffset = 0;
    private static final float legendYEntrySpace = 0;
    private static final float legendTextSize = 12;

    private static final float leftLegendSpaceTop = 35;
    private static final long animationTime = Duration.standardSeconds(1).getMillis();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_results);

        presenter = new TestResultPresenter(getIntent().getBundleExtra(TestResultPresenter.RESULT_EXTRA));
        initializeChart();
    }

    private void initializeChart(){
        BarChart chart = getBarChart();

        chart.getDescription().setEnabled(false);
        chart.setDrawBorders(true);
        // Scaling can now only be done on x- and y-axis separately
        chart.setPinchZoom(false);
        chart.setDrawBarShadow(false);
        chart.setDrawGridBackground(false);
        chart.getAxisRight().setEnabled(false);

        initializeLegend(chart.getLegend());
        initializeLeftAxis(chart.getAxisLeft());
        initializeChartData(chart);

        // barData.getGroupWith(...) is a helper that calculates the width each group needs based on the provided parameters
        float groupWidth = chart.getBarData().getGroupWidth(groupSpace, barSpace);
        initializeXAxis(chart.getXAxis(), groupWidth);
        chart.animateY((int)animationTime);

        chart.invalidate();
    }

    private void initializeLegend(Legend legend){
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        legend.setOrientation(Legend.LegendOrientation.VERTICAL);
        legend.setDrawInside(true);
        legend.setYOffset(legendYOffset);
        legend.setXOffset(legendXOffset);
        legend.setYEntrySpace(legendYEntrySpace);
        legend.setTextSize(legendTextSize);
    }

    private void initializeLeftAxis(YAxis leftAxis){
        leftAxis.setValueFormatter(new LargeValueFormatter());
        leftAxis.setDrawGridLines(false);
        leftAxis.setSpaceTop(leftLegendSpaceTop);
        leftAxis.setAxisMinimum(0); // this replaces setStartAtZero(true)
    }

    public void initializeChartData(BarChart chart) {
        BarData data = presenter.getBarData();
        data.setValueFormatter(new LargeValueFormatter());
        chart.setData(data);

        // specify the width each bar should have
        chart.getBarData().setBarWidth(barWidth);
        chart.groupBars(barSpace, groupSpace, barSpace);
    }

    private void initializeXAxis(XAxis xAxis, float groupWidth){
        // restrict the x-axis range
        xAxis.setAxisMinimum(0);
        xAxis.setAxisMaximum(groupWidth * amountOfGroups);

        xAxis.setLabelCount(amountOfGroups + 1, true);
        xAxis.setCenterAxisLabels(true);
        xAxis.setValueFormatter(((value, axis) -> value < groupWidth ? groupLabels[0]
                                                                     : value < 2 * groupWidth ? groupLabels[1]
                                                                                              : groupLabels[2]));
    }

    private BarChart getBarChart(){
        return findViewById(R.id.chart);
    }
}
