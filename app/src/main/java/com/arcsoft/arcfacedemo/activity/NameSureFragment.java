package com.arcsoft.arcfacedemo.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.arcsoft.arcfacedemo.R;
import com.arcsoft.arcfacedemo.model.Staff;
import com.arcsoft.arcfacedemo.model.StaffLab;

import java.io.File;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class NameSureFragment extends DialogFragment {
    private Staff staff;
    public static final String ARG_STAFF = "staff";
    private TextView state;
    private TextView name;
    private TextView gonghao;
    private TextView date;
    private TextView time;
    private ImageView imageView;
    private Date minedate;
    @Override
    public Dialog onCreateDialog(Bundle bundle){
        View itemView = LayoutInflater.from(getActivity()).inflate(R.layout.item,null);
        state =(TextView)itemView.findViewById(R.id.state);
        name = (TextView)itemView.findViewById(R.id.name);
        gonghao = (TextView)itemView.findViewById(R.id.gongHao);
        date = (TextView)itemView.findViewById(R.id.date);
        time = (TextView)itemView.findViewById(R.id.time);
        imageView = (ImageView)itemView.findViewById(R.id.imageView);
        state.setText(staff.getState().equals("shangban") ?  "上班打卡":"下班打卡");
        name.setText(staff.getName());
        gonghao.setText(staff.getNumber());

        minedate = parseServerTime(staff.getTime(),null);
        DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        String s = format1.format(minedate);
        date.setText(s);
        DateFormat format2 = new SimpleDateFormat("hh:mm:ss");
        String s1 = format2.format(minedate);
        time.setText(s1);
        imageView.setImageURI(Uri.fromFile(new File("/data/user/0/com.arcsoft.arcfacedemo/files/register/imgs/"+staff.getId()+".jpg")));
        return new AlertDialog.Builder(getActivity())
                .setView(itemView)
                .setTitle("打卡")
                .setPositiveButton("确认打卡", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        StaffLab.get(getActivity()).addStaff(staff);
                        Toast.makeText(getActivity(),"打卡成功",Toast.LENGTH_LONG).show();
                        //这里直接退出到ChoosFunction
                        getActivity().finish();
                    }
                })
                .setNegativeButton("取消打卡", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //这里重新识别
                        getActivity().finish();
                    }
                })
                .create();
    }

    public void getStaff(Staff staff){
        this.staff = staff;
    }

    private Date parseServerTime(String serverTime, String format) {
        if (format == null || format.isEmpty()) {
            format = "E MMM dd HH:mm:ss z yyyy";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.US);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        Date date = null;
        try {
            date = sdf.parse(serverTime);
        } catch (Exception e) {
            Log.e("报表error",e.getMessage());
        }
        return date;
    }

    public static NameSureFragment newInstance(Staff staff){
        Bundle bundle = new Bundle();
        bundle.putSerializable(ARG_STAFF, (Serializable) staff);
        NameSureFragment nameSureFragment = new NameSureFragment();
        nameSureFragment.setArguments(bundle);
        return nameSureFragment;
    }

}
