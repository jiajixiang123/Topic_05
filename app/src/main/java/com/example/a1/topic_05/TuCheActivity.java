package com.example.a1.topic_05;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a1.topic_05.ben.NewsBen;
import com.squareup.picasso.Picasso;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.editorpage.ShareActivity;
import com.umeng.socialize.media.UMImage;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

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
    private List<String> title;
    private UMShareListener shareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Toast.makeText(TuCheActivity.this,"成功                                     了",Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(TuCheActivity.this,"失                                            败"+t.getMessage(),Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(TuCheActivity.this,"取消                                     了",Toast.LENGTH_LONG).show();

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tu_che);
        initView();
        initData();
        initBanner();
        initListener();

    }

    private void initListener() {
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tuche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UMImage image = new UMImage(TuCheActivity.this, "http://imglf5.nosdn.127.net/img/ZFRneEJJMlBVdURVczErMzJ2TklXdzI3YjBDQjU4OVNCT3lLNmg2RWE3VUp4NytsVkZUTW5BPT0.jpg?imageView&thumbnail=500x0&quality=96&stripmeta=0&type=jpg");
                new ShareAction(TuCheActivity.this).withMedia(image).withText("hello")                    .setDisplayList(SHARE_MEDIA.SINA,SHARE_MEDIA.QQ,SHARE_MEDIA.WEIXIN)
                        .setCallback(shareListener).open();
            }
        });
    }

    private void initBanner() {
        banner.setBannerStyle(BannerConfig.NUM_INDICATOR);
        banner.setImages(imglist);
        banner.setBannerTitles(title);
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Picasso.with(TuCheActivity.this).load((String) path).into(imageView);
            }
        });
        banner.start();
        banner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                time.setText(position+"/18");
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
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
        title = new ArrayList<>();
        for (int i = 0; i <data.size() ; i++) {
            title.add(data.get(i).getTitle());
        }
    }

    private void initView() {
        fanhui = (TextView) findViewById(R.id.fanhui);
        tuche = (TextView) findViewById(R.id.tuche);
        banner = (Banner) findViewById(R.id.banner);
        time = (TextView) findViewById(R.id.time);
    }
}
