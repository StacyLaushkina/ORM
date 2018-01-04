package com.laushkina.anastasia.orm.models;

import java.io.Serializable;

public class TestResult implements Serializable {
    private int amountOfEntries;
    private long insertTime;
    private long shallowLoadTime;
    private long deepLoadTime;

    public TestResult(int amountOfEntries, long insertTime, long shallowLoadTime, long deepLoadTime){
        setAmountOfEntries(amountOfEntries);
        setInsertTime(insertTime);
        setShallowLoadTime(shallowLoadTime);
        setDeepLoadTime(deepLoadTime);
    }

    public int getAmountOfEntries() {
        return amountOfEntries;
    }

    public void setAmountOfEntries(int amountOfEntries) {
        this.amountOfEntries = amountOfEntries;
    }

    public long getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(long insertTime) {
        this.insertTime = insertTime;
    }

    public long getShallowLoadTime() {
        return shallowLoadTime;
    }

    public void setShallowLoadTime(long shallowLoadTime) {
        this.shallowLoadTime = shallowLoadTime;
    }

    public long getDeepLoadTime() {
        return deepLoadTime;
    }

    public void setDeepLoadTime(long deepLoadTime) {
        this.deepLoadTime = deepLoadTime;
    }
}
