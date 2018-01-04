package com.laushkina.anastasia.orm.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import com.laushkina.anastasia.orm.R;
import com.laushkina.anastasia.orm.presenters.MainActivityPresenter;
import com.laushkina.anastasia.orm.presenters.TestResultPresenter;
import com.laushkina.anastasia.orm.view.helpers.ExecuteTestTask;

import org.joda.time.Duration;

public class TestConfigurationActivity extends Activity {

    private static final int defaultAmountOfEntries = 500;
    private static final int maxAmountOfEntries = 5000;

    private MainActivityPresenter presenter;
    private boolean areTestsRunning = false;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_configuration_activity);

        presenter = new MainActivityPresenter();
    }

    @Override
    public void onResume(){
        super.onResume();
        initializeConfiguration();
    }

    public void startTest(View view) {
        areTestsRunning = true;
        crossfadeConfigAndProgressBar();
        SeekBar amountSeekBar = getAmountSeekBar();
        final int amount = amountSeekBar.getProgress();
        ExecuteTestTask testTask = new ExecuteTestTask(() -> presenter.startTests(amount, new ProgressObserver()), this);
        testTask.execute();
    }

    private void crossfadeConfigAndProgressBar() {
        long duration = Duration.standardSeconds(1).getMillis() / 2;
        View performView = getPerformingContainerView();
        View configView = getConfigurationContainerView();

        // Animate fade in
        performView.animate()
                .alpha(1f)
                .setDuration(duration)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        performView.setVisibility(View.VISIBLE);
                    }
                });

        // Animate fade out
        configView.animate()
                .alpha(0f)
                .setDuration(duration)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        configView.setVisibility(View.INVISIBLE);
                    }
                });
    }

    public void showResults(Bundle bundle) {
        areTestsRunning = false;
        Intent intent = new Intent(this, TestsResultActivity.class);
        intent.putExtra(TestResultPresenter.RESULT_EXTRA, bundle);
        startActivity(intent);
    }

    private void initializeConfiguration(){
        if (areTestsRunning) return;

        SeekBar amountSeekBar = getAmountSeekBar();
        TextView amountTextView = getAmountTextView();

        getPerformingContainerView().setVisibility(View.INVISIBLE);
        getConfigurationContainerView().setVisibility(View.VISIBLE);
        amountSeekBar.setMax(maxAmountOfEntries);
        amountSeekBar.setProgress(defaultAmountOfEntries);
        amountTextView.setText(String.valueOf(defaultAmountOfEntries));
        amountSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                amountTextView.setText(String.valueOf(progress));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }

    private SeekBar getAmountSeekBar(){
        return findViewById(R.id.amountSeekBar);
    }

    private TextView getAmountTextView(){
        return findViewById(R.id.amountTextView);
    }

    private ProgressBar getProgressBar(){
        return findViewById(R.id.progressBar);
    }

    private View getConfigurationContainerView(){
        return findViewById(R.id.configuration_container);
    }

    private View getPerformingContainerView(){
        return findViewById(R.id.perform_test_container);
    }


    private class ProgressObserver implements MainActivityPresenter.TestProgressObserver {
        @Override
        public void onProgressChanged(int progress) {
            getProgressBar().setProgress(progress);
        }
    }



}
