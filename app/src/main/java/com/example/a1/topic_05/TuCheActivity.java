package com.example.a1.topic_05;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.TextView;

import com.example.a1.topic_05.ben.NewsBen;
import com.youth.banner.Banner;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TuCheActivity extends AppCompatActivity {

    private TextView fanhui;
    private TextView tuche;
    private Banner banner;
    private TextView time;
    private List<NewsBen.ResultBean.DataBean> data;
    private List<String> imglist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tu_che);
        initView();
        initData();

    }


    private void initData() {
        NewsBen newsben = (NewsBen) getIntent().getSerializableExtra("newsben");
        data = newsben.getResult().getData();
        imglist = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            if (TextUtils.isEmpty(data.get(i).getThumbnail_pic_s02()) && TextUtils.isEmpty(data.get(i).getThumbnail_pic_s03())) {
                imglist.add(data.get(i).getThumbnail_pic_s());
            } else if (TextUtils.isEmpty(data.get(i).getThumbnail_pic_s03())) {
                imglist.add(data.get(i).getThumbnail_pic_s());
                imglist.add(data.get(i).getThumbnail_pic_s02());
            } else {
                imglist.add(data.get(i).getThumbnail_pic_s());
                imglist.add(data.get(i).getThumbnail_pic_s02());
                imglist.add(data.get(i).getThumbnail_pic_s03());
            }
        }
    }

    private void initView() {
        fanhui = (TextView) findViewById(R.id.fanhui);
        tuche = (TextView) findViewById(R.id.tuche);
        banner = (Banner) findViewById(R.id.banner);
        time = (TextView) findViewById(R.id.time);
    }
}
