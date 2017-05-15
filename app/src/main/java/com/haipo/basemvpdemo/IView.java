package com.haipo.basemvpdemo;

import android.widget.ImageView;

import com.haipo.basemvp.IBaseView;

/**
 * PackageName: com.haipo.basemvpdemo
 * FileName：   IView
 * Created by haipo on 2016/12/15.
 */

interface IView extends IBaseView {
    ImageView setImage();
    void setText(String text);
}
