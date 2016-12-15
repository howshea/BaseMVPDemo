package com.haipo.basemvpdemo;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by haipo on 2016/10/28.
 */

public interface ApiService {
    @GET("{width}*{height}")
    Observable<HomePage> getHome(@Path("width") int width, @Path("height") int height);
}
