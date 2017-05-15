package com.haipo.basemvpdemo;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by haipo
 * on 2016/10/28.
 *
 */

interface ApiService {
    @GET("1080*1920")
    Observable<HomePage> getHome();
}
