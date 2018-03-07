package com.example.a1.topic_05;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.a1.topic_05.ben.NewsBen;
import com.youth.banner.Banner;

import java.io.Serializable;
import java.util.List;

public class TuCheActivity extends AppCompatActivity {

    private TextView fanhui;
    private TextView tuche;
    private Banner banner;
    private TextView time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tu_che);
        initView();
        initData();
    }

    private void initData() {
        NewsBen newsben = (NewsBen) getIntent().getSerializableExtra("newsben");
        List<NewsBen.ResultBean.DataBean> data = newsben.getResult().getData();

    }

    private void initView() {
        fanhui = (TextView) findViewById(R.id.fanhui);
        tuche = (TextView) findViewById(R.id.tuche);
        banner = (Banner) findViewById(R.id.banner);
        time = (TextView) findViewById(R.id.time);
    }
}
