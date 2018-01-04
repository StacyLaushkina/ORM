package com.laushkina.anastasia.orm.models;

import java.util.UUID;

public class GUID {

    public static String getNew(){
        return UUID.randomUUID().toString();
    }
}
