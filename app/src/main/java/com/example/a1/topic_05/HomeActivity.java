package com.example.a1.topic_05;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bartoszlipinski.recyclerviewheader.RecyclerViewHeader;
import com.example.a1.topic_05.adapter.NewsBenAdapter;
import com.example.a1.topic_05.ben.NewsBen;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HomeActivity extends AppCompatActivity {

    private RecyclerViewHeader header;
    private RecyclerView recycler;
    private Banner banner;
    private List<NewsBen.ResultBean.DataBean> data;
    private NewsBenAdapter adapter;
    private NewsBen newsBen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
        initBanner();
        initData();


    }



   private void initListener() {
        adapter.setListener(new NewsBenAdapter.Listener() {
            @Override
            public void ItemListener(int position) {
                Intent intent = new Intent(HomeActivity.this, TuCheActivity.class);
                intent.putExtra("newsben",newsBen);
                startActivity(intent);
            }
        });
    }

    private void initBanner() {
        List<Integer>imglist=new ArrayList<>();
        imglist.add(R.mipmap.a);
        imglist.add(R.mipmap.b);
        imglist.add(R.mipmap.c);
        imglist.add(R.mipmap.d);
        imglist.add(R.mipmap.e);
        banner.setBannerStyle(BannerConfig.NUM_INDICATOR);
        banner.setImages(imglist);
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Picasso.with(HomeActivity.this).load((Integer) path).into(imageView);

            }
        });
        banner.start();


    }


    private void initData() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request build = new Request.Builder().url("http://172.16.54.8:8080/test/data3.json").build();
        Call call = okHttpClient.newCall(build);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Gson gson = new Gson();
                        newsBen = gson.fromJson(string, NewsBen.class);
                        data = newsBen.getResult().getData();
                        LinearLayoutManager manager = new LinearLayoutManager(HomeActivity.this, LinearLayoutManager.VERTICAL, false);
                        adapter = new NewsBenAdapter(data, HomeActivity.this);
                        recycler.setLayoutManager(manager);
                        recycler.setAdapter(adapter);
                        header.attachTo(recycler,true);
                        initListener();
                    }
                });
            }
        });
    }

    private void initView() {
        header = (RecyclerViewHeader) findViewById(R.id.header);
        recycler = (RecyclerView) findViewById(R.id.recycler);
        banner = (Banner) findViewById(R.id.banner);

    }
}
