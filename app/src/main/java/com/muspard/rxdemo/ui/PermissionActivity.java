package com.muspard.rxdemo.ui;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.muspard.rxdemo.R;
import com.muspard.rxdemo.base.BaseActivity;
import com.vondear.rxtools.RxActivityTool;
import com.vondear.rxtools.RxDeviceTool;
import com.vondear.rxtools.RxLogTool;
import com.vondear.rxtools.RxPermissionsTool;
import com.vondear.rxtools.view.RxToast;

import java.lang.reflect.Array;
import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by Administrator on 2018-04-17.
 */

public class PermissionActivity extends BaseActivity {

    @BindView(R.id.iv_permission_icon)
    ImageView mIv_icon;
    @BindView(R.id.pb_activity_permission)
    ProgressBar mPb;
    @BindView(R.id.tv_loaded_info)
    TextView mTv_load;
    private RxPermissionsTool.Builder mBuilder;

    private String[] mPermissionList = new String[]{
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.INTERNET,
            Manifest.permission.CAMERA,
    };

    private ArrayList<String> mPermission = new ArrayList<>();

    @Override
    public int inflateLayout() {
        return R.layout.activity_permission;
    }

    @Override
    protected void initData() {
        mBuilder = RxPermissionsTool.with(this);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initSetting() {

        for (String s : mPermissionList) {
            if (ContextCompat.checkSelfPermission(this, s) != PackageManager.PERMISSION_GRANTED) {
                mPermission.add(s);
                mBuilder.addPermission(s);
            }
        }

        if (mPermission.size() > 0) {
            mBuilder.initPermission();
        } else {
            if (RxActivityTool.isExistActivity(this, this.getPackageName(), "com.muspard.rxdemo.ui.LoadIngActivity")) {
                RxActivityTool.skipActivityAndFinish(this, LoadIngActivity.class);
            }
        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        for (int grantResult : grantResults) {
            if (grantResult != PackageManager.PERMISSION_GRANTED) {
                RxToast.showToast("申请的权限不通过,关闭app");
                finish();
                return;
            }
        }
        if (RxActivityTool.isExistActivity(this, this.getPackageName(), "com.muspard.rxdemo.ui.LoadIngActivity")) {
            RxActivityTool.skipActivityAndFinish(this, LoadIngActivity.class);
        }
    }
}
