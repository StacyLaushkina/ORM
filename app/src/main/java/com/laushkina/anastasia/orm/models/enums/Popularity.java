package com.laushkina.anastasia.orm.models.enums;

public enum Popularity {
    Rare(0),
    Unpopular(1),
    Popular(2),
    VeryPopular(3);

    private final int value;

     Popularity(int value) {
        this.value = value;
    }

    public int getValue(){
        return value;
    }

    public static Popularity parseValue(int value){
        switch (value) {
            case 0: return Rare;
            case 1: return Unpopular;
            case 2: return Popular;
            case 3: return VeryPopular;
            default: return null;
        }
    }
}
