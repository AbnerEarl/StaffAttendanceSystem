package com.example.frank.antitheft;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.tamic.novate.Throwable;
import com.tamic.novate.callback.RxStringCallback;

import java.util.HashMap;
import java.util.Map;

public class PersonActivity extends AppCompatActivity {

    private TextView name,phone,dept,posi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);


        name=(TextView)this.findViewById(R.id.textView8w) ;
        phone=(TextView)this.findViewById(R.id.textView9w) ;
        dept=(TextView)this.findViewById(R.id.textView10w) ;
        posi=(TextView)this.findViewById(R.id.textView11w) ;



        Map<String, Object> paremetes = new HashMap<>();
        paremetes.put("data", PersonAbout.account+"");
        ApiService.GetString(PersonActivity.this, "getPerInfo", paremetes, new RxStringCallback() {
            @Override
            public void onError(Object tag, Throwable e) {

                Toast.makeText(PersonActivity.this, "查询失败！" + e, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNext(Object tag, String response) {



                if(response.toString().equals("查询失败！")){
                    Toast.makeText(PersonActivity.this, "查询失败，请检查服务器！", Toast.LENGTH_SHORT).show();
                }
                else {
                   String pp[]=response.split("##");

                   name.setText(pp[0]);
                   phone.setText(pp[1]);
                   dept.setText(pp[2]);
                   posi.setText(pp[3]);
                }

            }

                   /* @Override
                    public void onError(Object tag, Throwable e) {
                        progress.setVisibility(View.INVISIBLE);
                        Toast.makeText(Login.this, "登录失败！" + e, Toast.LENGTH_SHORT).show();

                    }*/

            @Override
            public void onCancel(Object tag, Throwable e) {

                Toast.makeText(PersonActivity.this, "查询失败！" + e, Toast.LENGTH_SHORT).show();

            }
        });



    }
}
