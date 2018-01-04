package com.laushkina.anastasia.orm;

import android.app.Application;

import com.laushkina.anastasia.orm.repositories.db.DbHelper;
import com.laushkina.anastasia.orm.repositories.db.DbManager;
import com.raizlabs.android.dbflow.config.FlowManager;

public class ORMApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Initialize common db access
        DbHelper helper = new DbHelper(this);
        DbManager.getInstance().init(helper);

        FlowManager.init(this);
    }
}
