package com.tencent.streamshare.View;

import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.avast.android.dialogs.core.BaseDialogFragment;
import com.avast.android.dialogs.fragment.SimpleDialogFragment;
import com.avast.android.dialogs.iface.IPositiveButtonDialogListener;
import com.tencent.streamshare.R;

/**
 * Created by henryrhe on 2016/8/19.
 * 弹框输入流地址
 */
public class StreamUrlDialog extends SimpleDialogFragment {
    public static String TAG = "StreamUrlDialog";
    private PositiveBtnListener mListener;
    public static void show(FragmentActivity activity,PositiveBtnListener listener) {
        new StreamUrlDialog().setmListener(listener).show(activity.getSupportFragmentManager(), TAG);
    }

    public StreamUrlDialog setmListener(PositiveBtnListener mListener) {
        this.mListener = mListener;
        return this;
    }

    @Override
    protected Builder build(Builder builder){
            builder.setMessage("输入流地址");
        final View parentView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_add_stream, null);
        builder.setView(parentView);
        ((EditText)parentView.findViewById(R.id.streamAddress)).setText("rtmp://192.168.43.15/live/livestream");
        ((EditText)parentView.findViewById(R.id.streamAddress)).setSelection(7);
        builder.setPositiveButton("打开流", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onBtnClicked(((EditText)parentView.findViewById(R.id.streamAddress)).getText().toString());
                }
                dismiss();
            }
        });
        return builder;
    }

    public interface PositiveBtnListener{
        void onBtnClicked(String streamAddress);
    }
}
