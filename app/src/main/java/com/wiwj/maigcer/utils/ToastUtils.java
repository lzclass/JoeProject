package com.wiwj.maigcer.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.wiwj.maigcer.R;

/**
 * Created by liuzhao on 2018/3/5.
 */

public enum  ToastUtils {
    builder;

    private View v;
    private TextView tv;
    private Toast toast;

    public void init(Context c) {
        v = LayoutInflater.from(c).inflate(R.layout.toast, null);
        tv = (TextView) v.findViewById(R.id.tv_toast);
        toast = new Toast(c);
        toast.setView(v);
        toast.setGravity(Gravity.CENTER, 0, 0);
    }

    public void display(CharSequence text, int duration) {
        if (text.length() != 0) {
            tv.setText(text);
            toast.setDuration(duration);
            toast.show();
        }
    }

    public void display(int Resid, int duration) {
        if (Resid != 0) {
            tv.setText(Resid);
            toast.setDuration(duration);
            toast.show();
        }
    }

    public void display() {
        toast.cancel();
    }

    public Toast getToast() {
        return toast;
    }
}
