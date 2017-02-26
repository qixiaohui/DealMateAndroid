package com.dealmate.xiaohui.dealmate.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.dealmate.xiaohui.dealmate.R;
import com.dealmate.xiaohui.dealmate.adapter.DealListAdapter;
import com.dealmate.xiaohui.dealmate.config.CategoryEnum;
import com.dealmate.xiaohui.dealmate.model.DealModel;

import java.util.List;

/**
 * Created by xiaohui on 2/19/2017.
 */

public class ListGeneralActivity extends ActivityBase {
    protected RecyclerView recyclerView;
    protected DealListAdapter dealListAdapter;
    protected LinearLayoutManager linearLayoutManager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.general_list_activity);
        initView();
        actionBar();
    }

    @Override
    protected void initView() {
        super.initView();
        recyclerView = (RecyclerView) findViewById(R.id.dealList);
        dealListAdapter = new DealListAdapter();
        linearLayoutManager = new LinearLayoutManager(ListGeneralActivity.this);
        recyclerView.setAdapter(dealListAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    protected void getData() {

    }

    protected void loadData(List<DealModel> dealModels) {
        dealListAdapter.setClassType(DealDetailSqlActivity.class);
        dealListAdapter.setDeals(dealModels);
    }

    @Override
    protected void actionBar() {
        toolbar.setTitle("My favorite");
        toolbar.setNavigationIcon(R.drawable.back);
        super.actionBar();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
