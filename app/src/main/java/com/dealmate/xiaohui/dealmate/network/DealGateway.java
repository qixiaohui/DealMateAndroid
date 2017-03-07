package com.dealmate.xiaohui.dealmate.network;

import com.dealmate.xiaohui.dealmate.model.DealDetail;
import com.dealmate.xiaohui.dealmate.model.DealModel;
import com.dealmate.xiaohui.dealmate.model.LocalDealModel;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.Part;
import retrofit.http.Path;

/**
 * Created by xiaohui on 1/22/2017.
 */

public interface DealGateway {
    @GET("/deals/{category}/{subcategory}")
    void getDeals(@Path("category") String category, @Path("subcategory")String subcategory, retrofit.Callback<List<DealModel>> callback);

    @GET("/detail/{id}")
    void getDetail(@Path("id") String id, retrofit.Callback<DealDetail> callback);

    @GET("/local/{city}")
    void getLocal(@Path("city") String city, retrofit.Callback<LocalDealModel> callback);
}
