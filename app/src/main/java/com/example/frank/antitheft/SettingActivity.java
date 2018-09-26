package com.example.frank.antitheft;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SettingActivity extends AppCompatActivity {


    private Button shezhi,xiugai;
    private EditText mailee,passs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        shezhi=(Button)this.findViewById(R.id.button7);
        xiugai=(Button)this.findViewById(R.id.button8);
        mailee=(EditText)this.findViewById(R.id.editText2);
        passs=(EditText)this.findViewById(R.id.editText3);


        shezhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean flag=PersonInfo.saveInfoMail(SettingActivity.this,"mail", mailee.getText().toString().trim());
                if (flag){
                    mailee.setText("");
                    Toast.makeText(SettingActivity.this,"设置成功！",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(SettingActivity.this,"设置失败！",Toast.LENGTH_SHORT).show();
                }
            }
        });



        xiugai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean flag=PersonInfo.saveInfoPass(SettingActivity.this,"admin", passs.getText().toString().trim());
                if (flag){
                    passs.setText("");
                    Toast.makeText(SettingActivity.this,"修改成功！",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(SettingActivity.this,"修改失败！",Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
}
