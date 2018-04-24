package com.muspard.rxdemo.ui;

import android.graphics.drawable.Drawable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Window;
import android.view.WindowManager;

import com.muspard.rxdemo.R;
import com.muspard.rxdemo.adapter.ViewpagerAdapter;
import com.muspard.rxdemo.base.BaseActivity;
import com.muspard.rxdemo.image.GlideImageLoader;
import com.vondear.rxtools.RxBarTool;
import com.vondear.rxtools.view.dialog.RxDialog;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2018-04-17.
 */

public class LoadIngActivity extends BaseActivity {


    @BindView(R.id.banner)
    Banner mBanner;

    /**
     * 图片资源
     */
    private List<Integer> images = new ArrayList<>();

    @Override
    protected void beforeLayout() {
        super.beforeLayout();
        // Full Screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // No Titlebar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    @Override
    public int inflateLayout() {
        return R.layout.activity_loading;
    }

    @Override
    protected void initData() {
        images.add(R.drawable.loading_1);
        images.add(R.drawable.loading_2);
        images.add(R.drawable.loading_3);
    }

    @Override
    protected void initView() {
        //设置banner样式
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        //设置图片加载器
        mBanner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        mBanner.setImages(images);
        //设置banner动画效果
        mBanner.setBannerAnimation(Transformer.DepthPage);
        //设置自动轮播，默认为true
        mBanner.isAutoPlay(true);
        //设置轮播时间
        mBanner.setDelayTime(1000);
        //设置指示器位置（当banner模式中有指示器时）
        mBanner.setIndicatorGravity(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        //banner设置方法全部调用完毕时最后调用
        mBanner.start();
    }

    @Override
    protected void initSetting() {

    }
}
