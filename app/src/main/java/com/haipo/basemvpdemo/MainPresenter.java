package com.haipo.basemvpdemo;

import com.bumptech.glide.Glide;
import com.haipo.basemvp.BasePresenter;

import rx.Subscriber;

/**
 * PackageName: com.haipo.basemvpdemo
 * FileName：   MainPresenter
 * Created by haipo on 2016/12/15.
 */

class MainPresenter extends BasePresenter<IView> {
    private HomePage mHomePage;
    private HomePage.CreativesBean creativesBean;


    MainPresenter(IView view) {
        super(view);
    }

    void getData() {

        HttpRequest.getInstance().getHome()
                .subscribe(new Subscriber<HomePage>() {
                    @Override
                    public void onNext(HomePage homePage) {
                        mHomePage = homePage;
                        creativesBean = mHomePage.getCreatives().get(0);

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onCompleted() {
                        if (isViewAttached()) {
                            showData();
                        }
                    }

                });
    }

    private void showData() {
        Glide.with(BaseApplication.getAppContext())
                .load(creativesBean.getUrl())
                .asBitmap()
                .into(getView().setImage());

    }
}
