package com.laushkina.anastasia.orm.models.enums;

public enum ReadingType {
    Chinese(0),
    Japanese(1);

    private final int value;

    ReadingType(int value) {
        this.value = value;
    }

    public int getValue(){
        return value;
    }

    public static ReadingType parseValue(int value){
        switch (value) {
            case 0: return Chinese;
            case 1: return Japanese;
            default: return null;
        }
    }
}
