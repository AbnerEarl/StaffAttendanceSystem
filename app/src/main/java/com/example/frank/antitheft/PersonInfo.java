package com.example.frank.antitheft;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * PROJECT_NAME:AntiTheft
 * PACKAGE_NAME:com.example.frank.antitheft
 * USER:Frank
 * DATE:2018/3/31
 * TIME:0:30
 * DAY_NAME_FULL:星期六
 * DESCRIPTION:On the description and function of the document
 **/
public class PersonInfo {
    public static boolean saveInfoPass(Context context, String username, String password)//因为没有使用定义的变量就定义成static的方法这样的执行效率高
    {

        try {
            //File file=new File("/data/data/com.gongxin.login/info.dat");
            //context.getFilesDir();//帮助我们获取上下文文件目录/data/data/包名/files
            File file=new File(context.getFilesDir(), "passinfo.dat");
            FileOutputStream fos=new FileOutputStream(file);
            //context.openFileOutput(name, mode);
            fos.write((username+"##"+password).getBytes());
            fos.close();
            return true;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }


    public static boolean saveInfoMail(Context context,String username,String password)//因为没有使用定义的变量就定义成static的方法这样的执行效率高
    {

        try {
            //File file=new File("/data/data/com.gongxin.login/info.dat");
            //context.getFilesDir();//帮助我们获取上下文文件目录/data/data/包名/files
            File file=new File(context.getFilesDir(), "mailinfo.dat");
            FileOutputStream fos=new FileOutputStream(file);
            //context.openFileOutput(name, mode);
            fos.write((username+"##"+password).getBytes());
            fos.close();
            return true;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }



    public static Map<String, String> getSaveIfoPass(Context context)
    {
        Map<String, String> map;
        try {
            File file=new File(context.getFilesDir(), "passinfo.dat");
            FileInputStream fis=new  FileInputStream(file);
            BufferedReader br=new BufferedReader(new InputStreamReader(fis));
            String str=br.readLine();
            String[] infos=str.split("##");
            map = new LinkedHashMap<String,String>();
            map.put("用户名", infos[0]);
            map.put("密码", infos[1]);
            fis.close();
            return map;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }

    }


    public static Map<String, String> getSaveIfoMail(Context context)
    {
        Map<String, String> map;
        try {
            File file=new File(context.getFilesDir(), "mailinfo.dat");
            FileInputStream fis=new  FileInputStream(file);
            BufferedReader br=new BufferedReader(new InputStreamReader(fis));
            String str=br.readLine();
            String[] infos=str.split("##");
            map = new LinkedHashMap<String,String>();
            map.put("用户名", infos[0]);
            map.put("密码", infos[1]);
            fis.close();
            return map;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }

    }

}
