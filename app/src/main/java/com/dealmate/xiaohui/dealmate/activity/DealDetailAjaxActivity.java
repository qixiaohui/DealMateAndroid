package com.dealmate.xiaohui.dealmate.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.dealmate.xiaohui.dealmate.model.DealDetail;
import com.dealmate.xiaohui.dealmate.model.DealModel;
import com.dealmate.xiaohui.dealmate.network.RestClient;
import com.google.gson.Gson;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by xiaohui on 2/18/2017.
 */

public class DealDetailAjaxActivity extends DealDetailBaseActivity {

    @Override
    protected void loadDetail(String productId) {
        super.loadDetail(productId);
        RestClient.getDealGateway().getDetail(productId, new Callback<DealDetail>() {
            @Override
            public void success(DealDetail dealDetail, Response response) {
                loadDataToView(dealDetail);
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }
}
