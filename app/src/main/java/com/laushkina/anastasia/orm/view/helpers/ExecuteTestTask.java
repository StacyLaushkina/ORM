package com.laushkina.anastasia.orm.view.helpers;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.laushkina.anastasia.orm.view.TestConfigurationActivity;

import java.lang.ref.WeakReference;
import java.util.concurrent.Callable;

public class ExecuteTestTask extends AsyncTask<Void, Integer, Void> {
    private Callable<Bundle> backgroundTask;
    private WeakReference<TestConfigurationActivity> parentActivity;

    public ExecuteTestTask(Callable<Bundle> backgroundTask, TestConfigurationActivity parentActivity){
        this.backgroundTask = backgroundTask;
        this.parentActivity = new WeakReference<>(parentActivity);
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            Bundle result = backgroundTask.call();

            TestConfigurationActivity activity = parentActivity.get();
            if (activity != null) {
                activity.showResults(result);
            }
        } catch (Exception ex) {
            Log.e(ExecuteTestTask.class.getName(), ex.getMessage());
        }
        return null;
    }
}