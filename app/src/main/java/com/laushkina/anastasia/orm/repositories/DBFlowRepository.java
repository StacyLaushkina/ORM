package com.laushkina.anastasia.orm.repositories;

import com.laushkina.anastasia.orm.models.dbFlow.KanjiDbFlow;
import com.laushkina.anastasia.orm.repositories.db.DbFlowDatabase;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.Delete;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.ModelAdapter;

import java.util.List;

public class DBFlowRepository implements IRepository<KanjiDbFlow> {

    @Override
    public void add(List<KanjiDbFlow> kanjis) {
        FlowManager.getDatabase(DbFlowDatabase.class)
                .executeTransaction(databaseWrapper -> {
                    for (KanjiDbFlow model : kanjis) {
                        model.save();
                    }
                });
    }

    @Override
    public List<KanjiDbFlow> getAll() {
        return SQLite.select()
                .from(KanjiDbFlow.class)
                .queryList();
    }

    @Override
    public void deleteAll() {
        Delete.table(KanjiDbFlow.class);
    }
}
