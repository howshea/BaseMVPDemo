package com.haipo.basemvpdemo;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.haipo.basemvp.BaseActivtiy;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivtiy<IView,MainPresenter> implements IView {


    @BindView(R.id.home_image_view)
    ImageView mHomeImageView;
    @BindView(R.id.home_text_view)
    TextView mHomeTextView;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected MainPresenter getPresenter() {
        return new MainPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        mPresenter.getData();
    }

    @Override
    public ImageView setImage() {
        return mHomeImageView;
    }

    @Override
    public void setText(String text) {
//        mHomeTextView.setText(text);
    }

    public int getWidth() {
        return mHomeTextView.getWidth();
    }

    public int getHeight() {
        return mHomeTextView.getHeight();
    }
}
