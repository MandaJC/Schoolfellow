package com.example.lizhy.schoolfellow;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by lizhy on 2018/6/24.
 */

public class MyFragment1 extends Fragment {


    private ListView listView;
    private List<List<Image>> imagesList;

    private String[][] images=new String[][]{
            {"file:///android_asset/img1.jpg","250","250"}
            ,{"file:///android_asset/img2.jpg","250","250"}
            ,{"file:///android_asset/img3.jpg","250","250"}
            ,{"file:///android_asset/img4.jpg","250","250"}
            ,{"file:///android_asset/img5.jpg","250","250"}
            ,{"file:///android_asset/img6.jpg","250","250"}
            ,{"file:///android_asset/img7.jpg","250","250"}
            ,{"file:///android_asset/img8.jpg","250","250"}
            ,{"file:///android_asset/img9.jpg","1280","800"}
    };


    public MyFragment1() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.discover,container,false);
        /*TextView txt_content = (TextView) view.findViewById(R.id.txt_content);
        txt_content.setText("第1个Fragment");
        Log.e("HEHE", "第1个Fragment");*/

        listView= (ListView) view.findViewById(R.id.lv_main);
        initData();
        listView.setAdapter(new MainAdapter(getActivity(),imagesList));

        return view;
    }


    private void initData() {
        imagesList=new ArrayList<>();
        //这里单独添加一条单条的测试数据，用来测试单张的时候横竖图片的效果
        ArrayList<Image> singleList=new ArrayList<>();
        singleList.add(new Image(images[8][0],Integer.parseInt(images[8][1]),Integer.parseInt(images[8][2])));
        imagesList.add(singleList);
        //从一到9生成9条朋友圈内容，分别是1~9张图片
        for(int i=0;i<9;i++){
        ArrayList<Image> itemList=new ArrayList<>();
        for(int j=0;j<=i;j++){
            itemList.add(new Image(images[j][0],Integer.parseInt(images[j][1]),Integer.parseInt(images[j][2])));
        }
        imagesList.add(itemList);
    }
}

}

