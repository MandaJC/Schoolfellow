package com.example.lizhy.schoolfellow;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener,
        ViewPager.OnPageChangeListener {

    //UI Objects
    //private TextView txt_topbar;
    private RadioGroup rg_tab_bar;
    private RadioButton rb_discover;
    private RadioButton rb_newpost;
    //private RadioButton rb_me;
    private RadioButton rb_setting;
    private ViewPager vpager;

    private MyFragmentPagerAdapter mAdapter;

    //几个代表页面的常量
    public static final int PAGE_ONE = 0;
    public static final int PAGE_TWO = 1;
    public static final int PAGE_THREE = 2;
    //public static final int PAGE_FOUR = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        bindViews();
        rb_discover.setChecked(true);
    }

    private void bindViews() {
       // txt_topbar = (TextView) findViewById(R.id.txt_topbar);
        rg_tab_bar = (RadioGroup) findViewById(R.id.rg_tab_bar);
        rb_discover = (RadioButton) findViewById(R.id.rb_discover);
        rb_newpost = (RadioButton) findViewById(R.id.rb_newpost);
        //rb_me = (RadioButton) findViewById(R.id.rb_me);
        rb_setting = (RadioButton) findViewById(R.id.rb_setting);
        rg_tab_bar.setOnCheckedChangeListener(this);

        vpager = (ViewPager) findViewById(R.id.vpager);
        vpager.setAdapter(mAdapter);
        vpager.setCurrentItem(0);
        vpager.addOnPageChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_discover:
                vpager.setCurrentItem(PAGE_TWO);
                break;
            case R.id.rb_newpost:
                vpager.setCurrentItem(PAGE_ONE);
                break;
            /*case R.id.rb_me:
                vpager.setCurrentItem(PAGE_THREE);
                break;*/
            case R.id.rb_setting:
                vpager.setCurrentItem(PAGE_THREE);
                break;
        }
    }


    //重写ViewPager页面切换的处理方法
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        //state的状态有三个，0表示什么都没做，1正在滑动，2滑动完毕
        if (state == 2) {
            switch (vpager.getCurrentItem()) {
                case PAGE_TWO:
                    rb_discover.setChecked(true);
                    break;
                case PAGE_ONE:
                    rb_newpost.setChecked(true);
                    break;
                /*case PAGE_THREE:
                    rb_me.setChecked(true);
                    break;*/
                case PAGE_THREE:
                    rb_setting.setChecked(true);
                    break;
            }
        }
    }
}
