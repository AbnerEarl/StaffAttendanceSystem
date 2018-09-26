package com.example.frank.antitheft;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tamic.novate.Throwable;
import com.tamic.novate.callback.RxStringCallback;

import java.util.HashMap;
import java.util.Map;

public class AppActivity extends AppCompatActivity {

    private String style="";
    private EditText reason,dtime,dsum;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);
        Intent intent=getIntent();
        style=intent.getStringExtra("Style");

        reason=(EditText)this.findViewById(R.id.editText7);
        dtime=(EditText)this.findViewById(R.id.editText8);
        dsum=(EditText)this.findViewById(R.id.editText9);
        submit=(Button)this.findViewById(R.id.button12);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Map<String, Object> paremetes = new HashMap<>();
                paremetes.put("data", PersonAbout.account+"#"+style+"#"+reason.getText().toString().trim()+"#"+dtime.getText().toString().trim()+"#"+dsum.getText().toString().trim());
                ApiService.GetString(AppActivity.this, "addApplicant", paremetes, new RxStringCallback() {
                    @Override
                    public void onError(Object tag, Throwable e) {
                        Toast.makeText(AppActivity.this, "申请失败！" + e, Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onNext(Object tag, String response) {

                        if (response.toString().equals("提交失败！")) {
                            Toast.makeText(AppActivity.this, "申请失败，请检查网络是否连接，服务器是否开启！", Toast.LENGTH_SHORT).show();
                        }

                        else if (response.toString().equals("提交成功！")) {
                            Toast.makeText(AppActivity.this, "申请成功！", Toast.LENGTH_SHORT).show();
                            finish();

                        }

                    }

                       /* @Override
                        public void onError(Object tag, Throwable e) {
                            Toast.makeText(Register.this, "添加失败！" + e, Toast.LENGTH_SHORT).show();

                        }*/

                    @Override
                    public void onCancel(Object tag, Throwable e) {
                        Toast.makeText(AppActivity.this, "申请失败！" + e, Toast.LENGTH_SHORT).show();

                    }
                });





            }
        });


    }
}
