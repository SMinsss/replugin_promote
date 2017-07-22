package com.ssjjsy.sdk.plugin.two.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.ssjjsy.sdk.plugin.two.R;

/**
 * Created by Administrator on 2017/7/22.
 */

public class PluginTwoImage {

    private View view;

    public PluginTwoImage(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.image_layout, null);
        this.view = view;
    }

    public View getView() {
        return view;
    }

}
