package com.example.frank.antitheft;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
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


public class Main2Activity extends AppCompatActivity {

    private Button btn_qingjia,btn_jiaban,btn_chuqin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().
                detectDiskWrites().detectNetwork().penaltyLog().build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectLeakedSqlLiteObjects().
                detectLeakedClosableObjects().penaltyLog().penaltyDeath().build());


        btn_qingjia=(Button)this.findViewById(R.id.button);
        btn_jiaban=(Button)this.findViewById(R.id.button10);
        btn_chuqin=(Button)this.findViewById(R.id.button11);

        btn_qingjia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(Main2Activity.this,AppActivity.class);
                intent.putExtra("Style","请假申请");
                startActivity(intent);
            }
        });


        btn_jiaban.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(Main2Activity.this,AppActivity.class);
                intent.putExtra("Style","加班申请");
                startActivity(intent);
            }
        });

        btn_chuqin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(Main2Activity.this,AppActivity.class);
                intent.putExtra("Style","出勤申请");
                startActivity(intent);
            }
        });


    }





    /**
     * 邮件发送程序
     *
     * @param to
     *            接受人
     * @param subject
     *            邮件主题
     * @param content
     *            邮件内容
     * @throws Exception
     * @throws MessagingException
     */
    public static void sendEmail(String to, String subject, String content) throws Exception, MessagingException {
        String host = "smtp.qq.com";
        String address = "1320259466@qq.com";
        String from = "1320259466@qq.com";
        String password = "fcucszzgvpodhiff";// 密码fcucszzgvpodhiff  zyeqcneqptmpbafe  ripstxxsrrfmgabi
        if ("".equals(to) || to == null) {
            to = "1272275196@qq.com";
        }
        String port = "25";
        SendEmaill(host, address, from, password, to, port, subject, content);
    }

    /**
     * 邮件发送程序
     *
     * @param host
     *            邮件服务器 如：smtp.qq.com
     * @param address
     *            发送邮件的地址 如：545099227@qq.com
     * @param from
     *            来自： wsx2miao@qq.com
     * @param password
     *            您的邮箱密码
     * @param to
     *            接收人
     * @param port
     *            端口（QQ:25）
     * @param subject
     *            邮件主题
     * @param content
     *            邮件内容
     * @throws Exception
     */
    public static void SendEmaill(String host, String address, String from, String password, String to, String port, String subject, String content) throws Exception {
        Multipart multiPart;
        String finalString = "";

        Properties props = System.getProperties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", address);
        props.put("mail.smtp.password", password);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.auth", "true");
        Log.i("Check", "done pops");
        Session session = Session.getDefaultInstance(props, null);
        DataHandler handler = new DataHandler(new ByteArrayDataSource(finalString.getBytes(), "text/plain"));
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.setDataHandler(handler);
        Log.i("Check", "done sessions");

        multiPart = new MimeMultipart();
        InternetAddress toAddress;
        toAddress = new InternetAddress(to);
        message.addRecipient(Message.RecipientType.TO, toAddress);
        Log.i("Check", "added recipient");
        message.setSubject(subject);
        message.setContent(multiPart);
        message.setText(content);

        Log.i("check", "transport");
        Transport transport = session.getTransport("smtp");
        Log.i("check", "connecting");
        transport.connect(host, address, password);
        Log.i("check", "wana send");
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
        Log.i("check", "sent");

    }



}
