package com.haipo.basemvpdemo;

import com.bumptech.glide.Glide;
import com.haipo.basemvp.BasePresenter;

import rx.Subscriber;

/**
 * PackageName: com.haipo.basemvpdemo
 * FileName：   MainPresenter
 * Created by haipo on 2016/12/15.
 */

public class MainPresenter extends BasePresenter<IView> {
    HomePage mHomePage;

    public MainPresenter(IView view) {
        super(view);
    }

    public void getData(int width, int height) {

        HttpRequest.getInstance().getHome(width, height)
                .subscribe(new Subscriber<HomePage>() {
                    @Override
                    public void onCompleted() {
                        if (isViewAttached()) {
                            showData();
                            getView().setText(mHomePage.getText());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(HomePage homePage) {
                        mHomePage = homePage;
                    }
                });
    }

    public void showData() {
        Glide.with(BaseApplication.getAppContext())
                .load(mHomePage.getImg())
                .asBitmap()
                .into(getView().setImage());

    }
}
