package com.example.hello.myapplication.db;


import android.content.Context;

import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.database.DatabaseOpenHelper;

public abstract class AbstractDatabaseManager<M,K> implements IDatabase<M,K> {

    private static final String DB_NAME ="luow.db";

    public static void initOpenHelper(@NotNull Context context){
    }
}
