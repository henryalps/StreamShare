package com.tencent.streamshare.View;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.tencent.streamshare.R;

/**
 * Created by zhaoyongfei on 2016/8/21.
 */
public class MaskLoadingView extends RelativeLayout {
    public MaskLoadingView(Context context) {
        super(context);
        init(context);
    }

    public MaskLoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MaskLoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        inflate(context, R.layout.mask_layout, this);
    }

    public void showLoading() {
        setVisibility(VISIBLE);
    }

    public void dismissLoading() {
        setVisibility(GONE);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return true;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }
}
