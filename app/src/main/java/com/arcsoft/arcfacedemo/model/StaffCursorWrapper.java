package com.arcsoft.arcfacedemo.model;

import android.database.Cursor;
import android.database.CursorWrapper;

public class StaffCursorWrapper extends CursorWrapper {
    public StaffCursorWrapper(Cursor cursor){
        super(cursor);
    }

    public Staff getStaff(){
        String id = getString(getColumnIndex(Staff.StaffTable.Cols.ID));
        String number = getString(getColumnIndex(Staff.StaffTable.Cols.NUMBER));
        String name = getString(getColumnIndex(Staff.StaffTable.Cols.NAME));
        String state = getString(getColumnIndex(Staff.StaffTable.Cols.STATE));
        String time = getString(getColumnIndex(Staff.StaffTable.Cols.TIME));
        Staff staff = new Staff(id);
        staff.setName(name);
        staff.setNumber(number);
        staff.setState(state);
        staff.setTime(time);
        return staff;
    }
}
