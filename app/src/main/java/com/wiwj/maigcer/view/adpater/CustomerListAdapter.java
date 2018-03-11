package com.wiwj.maigcer.view.adpater;

import android.content.Context;

import java.util.List;

/**
 * Created by liuzhao on 2018/3/6.
 */

public class CustomerListAdapter extends BaseRecycleAdapter<String>{
    @Override
    protected void convert(BaseHolder holder, String bean) {

    }

    public CustomerListAdapter(Context context, int layoutId, List<String> data) {
        super(context, layoutId, data);
    }
}
