package com.example.lizhy.schoolfellow;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import android.app.Activity;
import android.app.ActivityGroup;
import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by lizhy on 2018/6/24.
 */

public class MyFragment2 extends Fragment {
    private GridView gridView;
    private Context context;
    private List<String> urlList;
    private MyGridViewAdapter mAdapter;
    private static final int REQUEST_CODE_ALUMB = 1;
    private static final int PERMISSION_CODE_ALUMB = 2;
    private static final String TAG = "MainActivity";
    public static final String EXTRA_KEY_IMAGE_LIST = "com.example.lizhy.schoolfellow.MyFragment2_iamge_list";
    private DisplayImageOptions options;
    private String ADD_IMG = "add_img";////////////////////////////////////////////////////////////


    public MyFragment2() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.new_post,container,false);
        /*TextView txt_content = (TextView) view.findViewById(R.id.txt_content);
        txt_content.setText("第二个Fragment");
        Log.e("HEHE", "第二个Fragment");*/

        context = getActivity();
        initImageLoader();
        urlList = new ArrayList<String>();
        gridView = (GridView)view.findViewById(R.id.gridView);
        mAdapter = new MyGridViewAdapter(context, urlList);
        gridView.setAdapter(mAdapter);
        mAdapter.insert(ADD_IMG, 0);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                if (position == urlList.size() - 1) {
                    if(ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                        requestPermissions(new String[]{ Manifest.permission.WRITE_EXTERNAL_STORAGE},PERMISSION_CODE_ALUMB);
                    }
                    else {
                        openAlbum();
                    }
                }
            }
        });

        return view;
    }

    private void openAlbum(){
        Intent intent = new Intent(context, ActivityGallery.class);
        if(urlList.size() > 1){
            intent.putStringArrayListExtra(EXTRA_KEY_IMAGE_LIST, (ArrayList<String>) urlList);
        }
        startActivityForResult(intent, REQUEST_CODE_ALUMB);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case PERMISSION_CODE_ALUMB:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Log.e("PermissionsResult: ", "OK!!!!");
                    openAlbum();
                }else {
                    Toast.makeText(getContext(), "没有权限访问相册", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    class MyGridViewAdapter extends ArrayAdapter<String>{

        private final RelativeLayout.LayoutParams params;

        public MyGridViewAdapter(Context context, List<String> objects) {
            super(context, 0, objects);
            int screenWidth = ImageUtil.getScreenWidth(getContext());
            int itemPadding = (int) context.getResources().getDimension(R.dimen.gridPadding);
            int imageSize = (screenWidth - itemPadding - itemPadding * 4) / 4;
            params = new RelativeLayout.LayoutParams(imageSize, imageSize);
        }

        @Override public View getView(int position, View convertView, ViewGroup parent) {
            BaseViewHolder holder = BaseViewHolder.getViewHolder(getContext(), convertView, parent,R.layout.gridview_item_layout, position);
            ImageView image = (ImageView) holder.getView(R.id.image);
            ImageView addImg = (ImageView) holder.getView(R.id.addImg);
            image.setLayoutParams(params);
            addImg.setLayoutParams(params);
            if(position == getCount() - 1){
                image.setVisibility(View.GONE);
                addImg.setVisibility(View.VISIBLE);
            }else{
                image.setVisibility(View.VISIBLE);
                addImg.setVisibility(View.GONE);
                ImageLoader.getInstance().displayImage("file://" + getItem(position), image);
            }
            return holder.getConvertView();
        }

        @Override public void registerDataSetObserver(DataSetObserver observer) {
            super.registerDataSetObserver(observer);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case REQUEST_CODE_ALUMB:
                if(resultCode == Activity.RESULT_OK){
                    String path = data.getStringExtra(ActivityGallery.EXTRA_KEY_IMAGE_URI);
                    List<String>imageList = (List<String>) data.getSerializableExtra(ActivityGallery.EXTRA_KEY_IMAGE_LIST);
                    urlList.clear();
                    urlList.addAll(imageList);
                    urlList.add(ADD_IMG);
                    mAdapter.notifyDataSetChanged();
                    printList();
                }
                break;
        }
    }

    private void initImageLoader(){
        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(context));
        DisplayImageOptions.Builder builder = new DisplayImageOptions.Builder();
        builder.resetViewBeforeLoading(true).cacheOnDisk(true).cacheInMemory(true).imageScaleType(ImageScaleType.EXACTLY).bitmapConfig(Bitmap.Config.RGB_565).considerExifParams(true).displayer(new FadeInBitmapDisplayer(30));
        options = builder.build();
    }

    private void printList(){
        for(String path : urlList){
            Log.e(TAG, "path = " + path);
        }
        Log.e(TAG, "path size " + urlList.size());
    }

}

