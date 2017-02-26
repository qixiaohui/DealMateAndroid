package com.dealmate.xiaohui.dealmate.network;

import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Created by xiaohui on 1/22/2017.
 */

public class RestClient {
    private static final String BASE_URL = "http://192.241.203.172:8000";

    public static DealGateway getDealGateway() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(BASE_URL)
                .setClient(new OkClient(SelfSigningClientBuilder.createClient()))
                .build();
        return restAdapter.create(DealGateway.class);
    }
}
