package com.arcsoft.arcfacedemo.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class StaffLab {
    private static StaffLab staffLab;

    //private List<Staff> staffs;
    private Context mcontext;
    private SQLiteDatabase mDatabase;
    public static StaffLab get(Context context){
        if(staffLab == null){
            staffLab = new StaffLab(context);
        }
        return staffLab;
    }


    private StaffLab(Context context){
        mcontext = context.getApplicationContext();
        mDatabase = new StaffBaseHelper(mcontext).getWritableDatabase();
        //staffs = new ArrayList<>();
    }

    public List<Staff> getStaff() {
        //return staffs;
        List<Staff> staffs = new ArrayList<>();
        StaffCursorWrapper cursor = queryStaffs(null,null);
        try{
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                staffs.add(cursor.getStaff());
                cursor.moveToNext();
            }
        }finally {
            cursor.close();
        }
        return staffs;
    }


    //添加员工，就是这里，用于打卡
    public void addStaff(Staff staff){
        ContentValues values = getContentValues(staff);
        mDatabase.insert(Staff.StaffTable.NAME,null,values);
    }

    private static ContentValues getContentValues(Staff staff){
        ContentValues contentValues = new ContentValues();
        contentValues.put(Staff.StaffTable.Cols.ID,staff.getId());
        contentValues.put(Staff.StaffTable.Cols.NUMBER,staff.getNumber());
        contentValues.put(Staff.StaffTable.Cols.NAME,staff.getName());
        contentValues.put(Staff.StaffTable.Cols.STATE,staff.getState());
        contentValues.put(Staff.StaffTable.Cols.TIME,staff.getTime());
        return contentValues;
    }

    //从数据库通过id获取员工的号
    public Staff getStaff(String id){
//        for (Staff st : staffs){
//            if(st.getId().equals(id)){
//                return st;
//            }
//        }
        StaffCursorWrapper cursor = queryStaffs(Staff.StaffTable.Cols.ID+"= ?", new String []{id});
        try{
            if(cursor.getCount() == 0){
                return null;
            }
            cursor.moveToFirst();
            return cursor.getStaff();
        }finally{
            cursor.close();
        }
    }

    //更新员工的信息，应该用不到
    public void updateStaff(Staff staff){
        String id = staff.getId();
        ContentValues values = getContentValues(staff);
        mDatabase.update(Staff.StaffTable.NAME,values,Staff.StaffTable.Cols.ID+"= ?",new String[]{id});
    }
//
//    private Cursor queryStaff(String whereClause, String[] whereArgs){
//        Cursor cursor = mDatabase.query(
//                Staff.StaffTable.NAME,
//                null,
//                whereClause,
//                whereArgs,
//                null,
//                null,
//                null
//        );
//        return cursor;
//    }

    private StaffCursorWrapper queryStaffs(String whereClause, String[] whereArgs){
                Cursor cursor = mDatabase.query(
                Staff.StaffTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );
                return new StaffCursorWrapper(cursor);
    }
}
