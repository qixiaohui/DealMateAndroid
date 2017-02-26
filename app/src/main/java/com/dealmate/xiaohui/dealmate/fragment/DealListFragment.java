package com.dealmate.xiaohui.dealmate.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dealmate.xiaohui.dealmate.R;
import com.dealmate.xiaohui.dealmate.activity.DealDetailAjaxActivity;
import com.dealmate.xiaohui.dealmate.adapter.DealListAdapter;
import com.dealmate.xiaohui.dealmate.config.CategoryEnum;
import com.dealmate.xiaohui.dealmate.model.DealModel;
import com.dealmate.xiaohui.dealmate.network.RestClient;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by xiaohui on 1/22/2017.
 */

public class DealListFragment extends Fragment {
    public static final String TAG = "DEAL_LIST_FRAGMENY";
    private OnMenuSelectedListener callback;
    private CategoryEnum categoryEnum;

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private DealListAdapter dealListAdapter;

    private static boolean loading = false;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_deal_list, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.dealList);
        dealListAdapter = new DealListAdapter();
        recyclerView.setAdapter(dealListAdapter);
        dealListAdapter.setClassType(DealDetailAjaxActivity.class);
        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        return view;
    }

    public void invokeCategory(CategoryEnum categoryEnum, String subcategory) {
        this.categoryEnum = categoryEnum;
        fetchDeal(categoryEnum.toString(), subcategory);
    }

    private void fetchDeal(String category, String subcategory) {
        RestClient.getDealGateway().getDeals(category, subcategory, new Callback<List<DealModel>>() {
            @Override
            public void success(List<DealModel> dealModel, Response response) {
                dealListAdapter.setDeals(dealModel);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, error.getMessage());
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = this.getActivity();
        try {
            callback = (OnMenuSelectedListener) activity;
        } catch (ClassCastException e) {
            Log.e(TAG, e.getMessage());
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public interface OnMenuSelectedListener {
        public void selectMenu(String category);
    }
}
