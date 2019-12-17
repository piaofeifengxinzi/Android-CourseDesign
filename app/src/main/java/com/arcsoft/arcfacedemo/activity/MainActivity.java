package com.arcsoft.arcfacedemo.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.arcsoft.arcfacedemo.R;

public class MainActivity extends AppCompatActivity {

    private EditText editTextn;
    private EditText editTextp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextn = (EditText)findViewById(R.id.ed1);
        editTextp = (EditText)findViewById(R.id.ed2);
    }

public void start(View view){
        if((editTextn.getText().toString().equals("zjl")&&editTextp.getText().toString().equals("123456")) ){
            Intent i = new Intent(this, ChooseFunctionActivity.class);
            startActivity(i);
        }else {
            Toast.makeText(this,"管理员账号或密码错误",Toast.LENGTH_LONG).show();
        }
}
}
