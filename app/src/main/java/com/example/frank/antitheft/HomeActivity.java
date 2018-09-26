package com.example.frank.antitheft;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.Poi;
import com.example.frank.antitheft.Application.LocationApplication;
import com.example.frank.antitheft.Application.LocationService;
import com.example.frank.antitheft.Service.GrayService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

public class HomeActivity extends AppCompatActivity {

    Button erji,chongdian,shuangchong,yanzheng;
    private LocationService locationService;
    TextView myText;


    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        erji=(Button)this.findViewById(R.id.button2);
        chongdian=(Button)this.findViewById(R.id.button3);
        shuangchong=(Button)this.findViewById(R.id.button4);
        yanzheng=(Button)this.findViewById(R.id.button5);
        myText = (TextView) findViewById(R.id.textView);
        myText.setMovementMethod(ScrollingMovementMethod.getInstance());
        progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("系统提示");
        progressDialog.setMessage("正在退出系统，请稍后……");
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().
                detectDiskWrites().detectNetwork().penaltyLog().build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectLeakedSqlLiteObjects().
                detectLeakedClosableObjects().penaltyLog().penaltyDeath().build());


        erji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent=new Intent(HomeActivity.this,MainActivity.class);
                //GrayService service=new GrayService();
                //service.onBind(intent);
                //startActivity(intent);
                Intent intent=new Intent(HomeActivity.this,TestActivity.class);
                startActivity(intent);
            }
        });

        chongdian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeActivity.this,Main2Activity.class);
                startActivity(intent);
            }
        });


        shuangchong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeActivity.this,VericationActivity.class);
                startActivity(intent);
            }
        });


        yanzheng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeActivity.this,PersonActivity.class);
                startActivity(intent);
            }
        });


        /*try {
            locationService.start();

        } catch (Exception ex) {
            Log.e("签到：",ex.toString());

        }*/

        /*try {
            //String locate = getLocation();
            String locate ="正在扫描。。。";

            //Toast.makeText(MainActivity.this, locate, Toast.LENGTH_SHORT).show();

            SimpleDateFormat   formatter   =   new SimpleDateFormat("yyyy年MM月dd日   HH:mm:ss");
            Date curDate =  new Date(System.currentTimeMillis());
            String   dd   =   formatter.format(curDate);

            sendEmail("1926254917@qq.com", "“手机防盗”提醒邮件邮件!", "在  " +dd + "  时刻，您退出了手机防盗软件！\n\n位置：" + locate);
        } catch (Exception e) {
            e.printStackTrace();
        }*/


        Map<String, String> map;
        map = PersonInfo.getSaveIfoPass(this);
        if(map!=null) {


        }else {
            PersonInfo.saveInfoPass(this,"admin", "admin");
        }


        Map<String, String> maps;
        maps = PersonInfo.getSaveIfoMail(this);
        if(maps!=null) {


        }else {
            PersonInfo.saveInfoMail(this,"mail", "1926254917@qq.com");
        }



    }










    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            AlertDialog isExit = new AlertDialog.Builder(this).create();


            isExit.setMessage("您确定要退出系统吗？");

            isExit.setButton("确定", listener);
            isExit.setButton2("取消", listener);

            isExit.show();

        }

        return false;

    }

    DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                case AlertDialog.BUTTON_POSITIVE: {

                    progressDialog.show();

                   finish();


                }
                break;
                case AlertDialog.BUTTON_NEGATIVE:
                    break;
                default:
                    break;
            }
        }
    };





}
