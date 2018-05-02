package com.muspard.rxdemo.ui;

import android.graphics.Color;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.muspard.rxdemo.R;
import com.muspard.rxdemo.base.BaseActivity;
import com.muspard.rxdemo.image.GlideImageLoader;
import com.vondear.rxtools.RxActivityTool;
import com.vondear.rxtools.RxLogTool;
import com.vondear.rxtools.view.RxToast;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;

/**
 * Created by Administrator on 2018-04-17.
 */

public class LoadIngActivity extends BaseActivity implements OnBannerListener, ViewPager.OnPageChangeListener {


    @BindView(R.id.banner)
    Banner mBanner;

    /**
     * 图片资源
     */
    private List<Integer> images = new ArrayList<>();
    /**
     * UI线程的handler
     */
    private Handler mUiHandler;

    /**
     * view pager当前滑动页
     */
    private int curPosition = -1;
    /**
     * 是否启动了关闭loading界面的runnable
     */
    private boolean isClose = false;


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
        mUiHandler = getUiHandler();
        images.add(R.drawable.loading_1);
        images.add(R.drawable.loading_2);
        images.add(R.drawable.loading_3);
    }

    @Override
    protected void initView() {
        //设置banner样式
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        mBanner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        mBanner.setImages(images);
        //设置banner动画效果
        mBanner.setBannerAnimation(Transformer.CubeOut);
        //设置标题集合（当banner样式有显示title时）
//        mBanner.setBannerTitles(titles);
        //设置自动轮播，默认为true
        mBanner.isAutoPlay(true);
        //设置轮播时间
        mBanner.setDelayTime(1500);
        //设置指示器位置（当banner模式中有指示器时）
        mBanner.setIndicatorGravity(BannerConfig.CENTER);
        mBanner.setOnPageChangeListener(this);
        mBanner.setOnBannerListener(this);
        //banner设置方法全部调用完毕时最后调用
        mBanner.start();
    }

    @Override
    protected void initSetting() {

    }


    @Override
    protected void onStop() {
        super.onStop();
        //结束轮播
        mBanner.stopAutoPlay();
    }

    @Override
    public void OnBannerClick(int position) {
        String[] stringArray = getResources().getStringArray(R.array.welcomeList);
        Random random = new Random();
        int i = random.nextInt(stringArray.length);
        RxToast.custom(this, stringArray[i], R.drawable.love_red, Color.BLUE, getResources().getColor(R.color.hotpink), Toast.LENGTH_SHORT, true, true).show();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//        if (isClose) {
//            isClose = false;
//            mBanner.isAutoPlay(true);
//            mUiHandler.removeCallbacks(mCloseRunnable);
//        }
    }

    @Override
    public void onPageSelected(int position) {
        curPosition = position;
        RxLogTool.d("当前poition=" + position);
        if (position == (images.size() - 1)) {
            isClose = true;
            mBanner.stopAutoPlay();
            mUiHandler.postDelayed(mCloseRunnable, 1500);
        } else {
            if (isClose) {
                isClose = false;
                mBanner.isAutoPlay(true);
                mUiHandler.removeCallbacks(mCloseRunnable);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    Runnable mCloseRunnable = new Runnable() {
        @Override
        public void run() {
            if (curPosition == images.size() - 1) {
                if (RxActivityTool.isExistActivity(LoadIngActivity.this, LoadIngActivity.this.getPackageName(), "com.muspard.rxdemo.ui.MainActivity")) {
                    RxActivityTool.skipActivityAndFinish(LoadIngActivity.this, MainActivity.class);
                }
                finish();
            }
        }
    };


    private void stopOrStartRoll() {
        if (isClose) {
            mBanner.stopAutoPlay();
        } else {
            mBanner.isAutoPlay(false);
        }
    }
}
