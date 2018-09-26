package com.example.frank.antitheft;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tamic.novate.Throwable;
import com.tamic.novate.callback.RxStringCallback;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    private Button register;
    private EditText et_no,et_pass,et_comfirm,et_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        register=(Button)this.findViewById(R.id.button5);
        et_no=(EditText)this.findViewById(R.id.editText);
        et_pass=(EditText)this.findViewById(R.id.editText4);
        et_comfirm=(EditText)this.findViewById(R.id.editText5);
        et_name=(EditText)this.findViewById(R.id.editText6);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String no=et_no.getText().toString().trim();
                String pass=et_pass.getText().toString().trim();
                String comfir=et_comfirm.getText().toString().trim();
                String name=et_name.getText().toString().trim();
                if (no.length()<2||pass.length()<2||comfir.length()<2||name.length()<2){
                    Toast.makeText(Register.this, "联系方式、姓名、注册密码、确认密码其中输入不合法，请重新输入大于3位数的电话号码或者密码！", Toast.LENGTH_SHORT).show();
                }
                else if (!pass.equals(comfir)){
                    Toast.makeText(Register.this, "两次密码输入不一致，请重新输入确认密码！", Toast.LENGTH_SHORT).show();
                    et_comfirm.setText("");
                }else {
                    Map<String, Object> paremetes = new HashMap<>();
                    paremetes.put("data", no+"#"+pass+"#"+name);
                    ApiService.GetString(Register.this, "addUserInfo", paremetes, new RxStringCallback() {
                        @Override
                        public void onError(Object tag, Throwable e) {
                            Toast.makeText(Register.this, "添加失败！" + e, Toast.LENGTH_SHORT).show();

                        }

                        @Override
                        public void onNext(Object tag, String response) {

                            if (response.toString().equals("提交失败！")) {
                                Toast.makeText(Register.this, "提交失败，请检查网络是否连接，服务器是否开启！", Toast.LENGTH_SHORT).show();
                            }
                            else if(response.toString().equals("重复提交")){
                                et_no.setText("");
                                Toast.makeText(Register.this, "该账号已经注册，请重新输入注册账号！", Toast.LENGTH_SHORT).show();
                            }
                            else if (response.toString().equals("提交成功！")) {
                                Toast.makeText(Register.this, "注册成功！", Toast.LENGTH_SHORT).show();
                                finish();

                            }

                        }

                       /* @Override
                        public void onError(Object tag, Throwable e) {
                            Toast.makeText(Register.this, "添加失败！" + e, Toast.LENGTH_SHORT).show();

                        }*/

                        @Override
                        public void onCancel(Object tag, Throwable e) {
                            Toast.makeText(Register.this, "添加失败！" + e, Toast.LENGTH_SHORT).show();

                        }
                    });

                }
            }
        });
    }
}
