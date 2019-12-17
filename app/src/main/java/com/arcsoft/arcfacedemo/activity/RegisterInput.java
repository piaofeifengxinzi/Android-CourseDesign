package com.arcsoft.arcfacedemo.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.arcsoft.arcfacedemo.R;

public class RegisterInput extends AppCompatActivity {

    private EditText editText3;
    private EditText editText4;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_input);
        editText3 = (EditText)findViewById(R.id.id3);
        editText4 = (EditText)findViewById(R.id.id4);
        button = (Button)findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                init();
            }
        });
    }
    //这里要将虹软生成的id与工号名字对应起来，就不直接存在数据库了，方便一点，实现起来简单一点
    //这里要获取输入信息，同时，将信息携带跳转到人脸注册界面后，在那里存储再sharedperenced中，以便日后使用
    public void init(){
        String name = editText3.getText().toString();
        String gonghao = editText4.getText().toString();
        Intent i = new Intent(this, RegisterAndRecognizeActivity.class);
        i.putExtra("moshi","zhuce");
        i.putExtra("name",name);
        i.putExtra("gonghao",gonghao);
        startActivity(i);
        finish();
    }
}
