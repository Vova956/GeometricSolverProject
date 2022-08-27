package com.geom.geometricsolver3.DB;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {FormulaData.class},version = 1,exportSchema = false)
public abstract class FormulaDB extends RoomDatabase {
    private static FormulaDB database;
    private static String DATABASE_NAME = "FormulaDB";

    public abstract FormulaDAO formulaDAO();

    public synchronized static FormulaDB getInstance(Context contex){
        if(database == null){
            database = Room.databaseBuilder
                    (contex.getApplicationContext(), FormulaDB.class,DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return database;
    }
}
