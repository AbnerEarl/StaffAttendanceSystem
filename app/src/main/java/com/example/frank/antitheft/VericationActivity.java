package com.example.frank.antitheft;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.tamic.novate.Throwable;
import com.tamic.novate.callback.RxStringCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class VericationActivity extends AppCompatActivity {


    private ListView lv_app;
    private MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verication);


        lv_app=(ListView)this.findViewById(R.id.lv_applicant);
        mAdapter = new MyAdapter(this);//得到一个MyAdapter对象
        lv_app.setAdapter(mAdapter);//为ListView绑定Adapter


        getData();

    }




    private class MyAdapter extends BaseAdapter {

        private LayoutInflater mInflater;//得到一个LayoutInfalter对象用来导入布局
        //ArrayList<HashMap<String, Object>> listItem = CheckInfo.listItem;
        private ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String,     Object>>();
        /*构造函数*/
        public MyAdapter(Context context) {
            this.mInflater = LayoutInflater.from(context);
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public int getCount() {

            return listItem.size();//返回数组的长度
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }
        /*书中详细解释该方法*/
        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final ViewHolder holder;
            //观察convertView随ListView滚动情况
            Log.v("MyListViewBase", "getView " + position + " " + convertView);
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.app_item,null);
                holder = new ViewHolder();
                /*得到各个控件的对象*/

                holder.style = (TextView) convertView.findViewById(R.id.textView3);
                holder.content = (TextView) convertView.findViewById(R.id.textView4);
                holder.dd = (TextView) convertView.findViewById(R.id.textView5);
                holder.appsum = (TextView) convertView.findViewById(R.id.textView6);
                holder.state = (TextView) convertView.findViewById(R.id.textView7);
                holder.leader = (TextView) convertView.findViewById(R.id.textView8);


                convertView.setTag(holder);//绑定ViewHolder对象
            }
            else{
                holder = (ViewHolder)convertView.getTag();//取出ViewHolder对象
            }
            /*设置TextView显示的内容，即我们存放在动态数组中的数据*/


            /*holder.title.setText(getDate().get(position).get("ItemText").toString());
            holder.pic.setImageResource(Integer.parseInt(getDate().get(position).get("ItemImage").toString()));*/

            //holder.pic.setImageURI(Uri.parse(getDate().get(position).get("ItemImg").toString()));
            /*Bitmap bm = BitmapFactory.decodeFile(listItem.get(position).get("ItemImage").toString());
            holder.pic.setImageBitmap(bm);*/

            holder.style.setText(listItem.get(position).get("style").toString());
            holder.content.setText(listItem.get(position).get("content").toString());
            holder.dd.setText(listItem.get(position).get("dd").toString());
            holder.state.setText(listItem.get(position).get("state").toString());
            holder.appsum.setText(listItem.get(position).get("appsum").toString());
            holder.leader.setText(listItem.get(position).get("leader").toString());




            return convertView;
        }

    }
    /*存放控件*/
    public final class ViewHolder{
        public TextView style,content,dd,state,appsum,leader;


    }





    private void getData(){
        Map<String, Object> paremetes = new HashMap<>();
        paremetes.put("data", PersonAbout.account+"");
        ApiService.GetString(VericationActivity.this, "getApplicant", paremetes, new RxStringCallback() {
            @Override
            public void onError(Object tag, Throwable e) {
                Toast.makeText(VericationActivity.this, "查询失败！" + e, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNext(Object tag, String response) {

                if (response.toString().equals("提交失败！")) {
                    Toast.makeText(VericationActivity.this, "查询失败，请检查网络是否连接，服务器是否开启！", Toast.LENGTH_SHORT).show();
                }
                else {

                    String appinfo[]=response.split("##");
                    for (int i=0;i<appinfo.length;i++){
                        String info[]=appinfo[i].split("#");
                        HashMap<String, Object> map = new HashMap<String, Object>();
                        map.put("style","申请类型："+info[0]);
                        map.put("content","原因内容："+info[1]);
                        map.put("dd","开始时间："+info[2]);
                        map.put("state","审核状态："+info[3]);
                        map.put("appsum","申请天数："+info[4]);
                        if (info[5]==null||info[5].equals("")||info[5].equals("null")){
                            map.put("leader","审 核 人：还没有被审核！");
                        }else {
                            map.put("leader","审 核 人 ："+info[5]);
                        }

                        mAdapter.listItem.add(map);
                    }

                    mAdapter.notifyDataSetChanged();

                }


            }

                       /* @Override
                        public void onError(Object tag, Throwable e) {
                            Toast.makeText(Register.this, "添加失败！" + e, Toast.LENGTH_SHORT).show();

                        }*/

            @Override
            public void onCancel(Object tag, Throwable e) {
                Toast.makeText(VericationActivity.this, "查询失败！" + e, Toast.LENGTH_SHORT).show();

            }
        });


    }


}
