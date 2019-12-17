package com.arcsoft.arcfacedemo.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arcsoft.arcfacedemo.R;
import com.arcsoft.arcfacedemo.model.Staff;
import com.arcsoft.arcfacedemo.model.StaffLab;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListFragment extends Fragment {
    private RecyclerView recyclerView;
    private  StaffAdapter staffAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private void updateUI(){
        StaffLab staffLab = StaffLab.get(getActivity());
        List<Staff> staff = staffLab.getStaff();
        staffAdapter = new StaffAdapter(staff);
        recyclerView.setAdapter(staffAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_list, container, false);
        recyclerView = (RecyclerView)v.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return v;
    }


    private class StaffHolder extends RecyclerView.ViewHolder{
        private Date minedate;
        private TextView state;
        private TextView name;
        private TextView gonghao;
        private TextView date;
        private TextView time;
        private ImageView imageView;
        public StaffHolder(View itemView){
            super(itemView);
            state =(TextView)itemView.findViewById(R.id.state);
            name = (TextView)itemView.findViewById(R.id.name);
            gonghao = (TextView)itemView.findViewById(R.id.gongHao);
            date = (TextView)itemView.findViewById(R.id.date);
            time = (TextView)itemView.findViewById(R.id.time);
            imageView = (ImageView)itemView.findViewById(R.id.imageView);
        }
        public void bindStaff(Staff staff){
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
            //Log.e("报表",minedate+"     "+staff.getTime());

            ///
            /////这里时间的还有图片的加入，然后改改八阿哥就好了

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
    }





    private class StaffAdapter extends RecyclerView.Adapter<StaffHolder>{
        private List<Staff> staffs;

        public StaffAdapter(List<Staff> staffs1){
            staffs = staffs1;
        }


        @Override
        public StaffHolder onCreateViewHolder(ViewGroup parent, int viewType){
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.item,parent,false);
            return new StaffHolder((view));
        }

        @Override
        public void onBindViewHolder(StaffHolder holder, int position){
            Staff staff = staffs.get(position);
            //holder.textView.setText(staff.getName());
            holder.bindStaff(staff);
        }

        @Override
        public int getItemCount(){
            return staffs.size();
        }
    }

}
