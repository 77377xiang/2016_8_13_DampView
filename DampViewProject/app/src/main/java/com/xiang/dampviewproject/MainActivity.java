package com.xiang.dampviewproject;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {
    private static final String[] names = new String[]{
            "张顺",
            "皇甫端",
            "王英",
            "扈三娘",
            "周通",
            "汤隆",
            "杜兴",
            "蔡庆",
            "李立",
            "李云",
            "焦挺",
            "石勇",
    };
    private MyListView myListView;
    private ImageView headerView;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initData();
    }

    private void initData() {
        myListView.addHeaderView(view);
        myListView.setAdapter(new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, names) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView tv = (TextView) super.getView(position, convertView, parent);
                tv.setTextColor(Color.BLACK);
                return tv;
            }
        });
        myListView.setOverScrollMode(ListView.OVER_SCROLL_NEVER);

        headerView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                myListView.setParallaxImageView(headerView);
                //headerView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                headerView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });
    }

    private void initViews() {
        view = View.inflate(MainActivity.this, R.layout.item_head, null);
        headerView = (ImageView) view.findViewById(R.id.image);
        myListView = (MyListView) findViewById(R.id.myListView);
    }
}
