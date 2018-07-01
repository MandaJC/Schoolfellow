package com.example.lizhy.schoolfellow;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableRow;
import android.widget.TextView;

/**
 * Created by lizhy on 2018/6/24.
 */

public class MyFragment4 extends Fragment {

    private TableRow head,nickname,gender,school,region,sign,change,suggestion,about,exit;
    private TextView vnick,vgender,vshool,vregion,vsign;

    public MyFragment4() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.setting,container,false);
        //TextView txt_content = (TextView) view.findViewById(R.id.txt_content);
        //txt_content.setText("第四个Fragment");
        Log.e("HEHE", "设置页");
       vnick = (TextView) view.findViewById(R.id.view_nick);
       vgender = (TextView) view.findViewById(R.id.view_gender);
       vshool = (TextView) view.findViewById(R.id.view_school);
       vregion = (TextView) view.findViewById(R.id.view_region);
       vsign = (TextView) view.findViewById(R.id.view_sign);
       head = (TableRow) view.findViewById(R.id.head);
       nickname = (TableRow) view.findViewById(R.id.nickname);
       gender = (TableRow) view.findViewById(R.id.gender);
       school = (TableRow) view.findViewById(R.id.school);
       region = (TableRow) view.findViewById(R.id.region);
       sign = (TableRow) view.findViewById(R.id.sign);
       change = (TableRow) view.findViewById(R.id.change);
       suggestion = (TableRow) view.findViewById(R.id.suggestion);
       about = (TableRow) view.findViewById(R.id.about);
       exit = (TableRow) view.findViewById(R.id.exit);

       nickname.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent it1 = new Intent();
               it1.setClass(getActivity(),SetActivity.class);
               startActivity(it1);
           }
       });
        return view;
    }
}
