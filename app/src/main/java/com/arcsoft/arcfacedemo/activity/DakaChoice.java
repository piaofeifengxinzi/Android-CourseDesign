package com.arcsoft.arcfacedemo.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.arcsoft.arcfacedemo.R;

public class DakaChoice extends AppCompatActivity {

    private Button button;
    private Button button1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daka_choice);
        initView();
    }

    private void initView(){
        button = (Button)findViewById(R.id.id5);
        button1 = (Button)findViewById(R.id.id6);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DakaChoice.this,RegisterAndRecognizeActivity.class);
                i.putExtra("moshi","daka");
                i.putExtra("state","shangban");
                startActivity(i);
                finish();

            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DakaChoice.this,RegisterAndRecognizeActivity.class);
                i.putExtra("moshi","daka");
                i.putExtra("state","xiaban");
                startActivity(i);
                finish();
            }
        });
    }
}
