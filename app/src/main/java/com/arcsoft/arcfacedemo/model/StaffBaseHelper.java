package com.arcsoft.arcfacedemo.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class StaffBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "staffBase.db";

    public StaffBaseHelper(Context context){
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("create table "+ Staff.StaffTable.NAME + "(" +
                "_id integer primary key autoincrement, "+
                Staff.StaffTable.Cols.ID+", "+
                        Staff.StaffTable.Cols.NUMBER+", "+
                        Staff.StaffTable.Cols.NAME+", "+
                        Staff.StaffTable.Cols.STATE+", "+
                        Staff.StaffTable.Cols.TIME+")"
                );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int ov, int nv){

    }
}
