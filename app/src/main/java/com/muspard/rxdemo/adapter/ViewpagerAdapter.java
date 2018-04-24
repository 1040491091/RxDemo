package com.muspard.rxdemo.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.muspard.rxdemo.R;

/**
 * Created by Administrator on 2018-04-17.
 */

public class ViewpagerAdapter extends PagerAdapter {

    private Context mContext;
    int[] res = new int[]{
            R.drawable.loading_1,
            R.drawable.loading_2,
            R.drawable.loading_3
    };


    public ViewpagerAdapter(Context context) {
        mContext = context;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(container.getContext());
        imageView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));  //设置图片宽高
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageDrawable(container.getContext().getDrawable(res[position]));
        container.addView(imageView);

        // 返回填充的View对象
        return imageView;
    }

    @Override
    public int getCount() {
        if (res != null && res.length > 0) {
            return res.length;
        } else {
            return 0;
        }
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
        container.removeView((View) object);
    }
}
