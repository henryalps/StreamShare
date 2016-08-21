package com.tencent.streamshare.View;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.wang.avi.AVLoadingIndicatorView;

/**
 * Created by zhaoyongfei on 2016/8/21.
 */
public class MaskLoadingView extends RelativeLayout {
    private AVLoadingIndicatorView loading;
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
        loading = new AVLoadingIndicatorView(context);
        setBackgroundColor(Color.parseColor("#66666666"));
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        params.addRule(CENTER_IN_PARENT);
        addView(loading,params);
    }

    public void showLoading() {
        setVisibility(VISIBLE);
        loading.smoothToShow();
    }

    public void dismissLoading() {
        loading.smoothToHide();
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
