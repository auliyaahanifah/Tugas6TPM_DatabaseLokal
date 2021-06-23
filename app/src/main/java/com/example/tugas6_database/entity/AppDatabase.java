package com.example.tugas6_database.entity;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {DataUntung.class} , version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DataUntungDAO dao();
    private static AppDatabase appDatabase;

    public static AppDatabase iniDb(Context context){
        if(appDatabase == null)
            appDatabase = Room.databaseBuilder(context, AppDatabase.class,
                    "dbUntung").allowMainThreadQueries().build();

        return appDatabase;
    }
}
