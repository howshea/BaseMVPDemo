package com.haipo.basemvp;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * PackageName: com.haipo.artisanmusic.base.basemvp
 * FileName：   BaseFragment
 * Created by haipo on 2016/11/9.
 *
 */

public abstract class BaseFragment<V extends IBaseView, P extends BasePresenter<V>> extends Fragment {

    //
    protected P mPresenter;



    /**
     * @return frgment的布局文件
     */
    @LayoutRes
    protected abstract int getLayoutId();

    /**
     * @return presenter对象
     */
    protected abstract P getPresenter();

    /**
     * 因为fragment的onCreateView需要return
     * 留出这个抽象方法
     * 把一些额外的view任务以及presenter的任务都交给子类
     */
    protected abstract void initView();

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        mPresenter = getPresenter();
        initView();
        return view;
    }

    @Override
    public void onDestroyView() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        super.onDestroyView();

    }
}
