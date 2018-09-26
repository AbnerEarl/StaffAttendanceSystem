package com.example.frank.antitheft;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.tamic.novate.Throwable;
import com.tamic.novate.callback.RxStringCallback;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    private Button btn_register,btn_intorduce,btn_login;
    private ProgressBar progress;
    private EditText et_account,et_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        btn_register=(Button)this.findViewById(R.id.button4);
        btn_intorduce=(Button)this.findViewById(R.id.button38);
        btn_login=(Button)this.findViewById(R.id.button42);
        progress=(ProgressBar)this.findViewById(R.id.progressBar2_login);
        et_account=(EditText)this.findViewById(R.id.editText2);
        et_password=(EditText)this.findViewById(R.id.editText3);



        btn_intorduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new  AlertDialog.Builder(Login.this)
                        .setTitle("系统提示")
                        .setMessage("暂时没有使用介绍！")
                        .setPositiveButton("确定",
                                new  DialogInterface.OnClickListener()
                                {
                                    @Override
                                    public  void  onClick(DialogInterface dialog, int  which)
                                    {


                                    }
                                }).show();
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(Login.this,Register.class);
                startActivity(intent);
            }
        });


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progress.setVisibility(View.VISIBLE);
                final String account=et_account.getText().toString().trim();
                String password=et_password.getText().toString().trim();

                Map<String, Object> paremetes = new HashMap<>();
                paremetes.put("data", account+"#"+password);
                ApiService.GetString(Login.this, "getUserInfo", paremetes, new RxStringCallback() {
                    @Override
                    public void onError(Object tag, Throwable e) {
                        progress.setVisibility(View.INVISIBLE);
                        Toast.makeText(Login.this, "登录失败！" + e, Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onNext(Object tag, String response) {

                        progress.setVisibility(View.INVISIBLE);
                        if (response.toString().equals("true")) {
                            Toast.makeText(Login.this, "登录成功！", Toast.LENGTH_SHORT).show();

                            PersonAbout.account=account;
                            Intent intent=new Intent(Login.this,HomeActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        else if(response.toString().equals("查询失败！")){
                            Toast.makeText(Login.this, "登录失败，请检查服务器！", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(Login.this, "登录失败，请检查账号、密码是否正确，网络是否连接！", Toast.LENGTH_SHORT).show();


                        }

                    }

                   /* @Override
                    public void onError(Object tag, Throwable e) {
                        progress.setVisibility(View.INVISIBLE);
                        Toast.makeText(Login.this, "登录失败！" + e, Toast.LENGTH_SHORT).show();

                    }*/

                    @Override
                    public void onCancel(Object tag, Throwable e) {
                        progress.setVisibility(View.INVISIBLE);
                        Toast.makeText(Login.this, "登录失败！" + e, Toast.LENGTH_SHORT).show();

                    }
                });


            }
        });

    }
}
