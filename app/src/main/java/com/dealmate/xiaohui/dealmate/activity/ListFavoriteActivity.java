package com.dealmate.xiaohui.dealmate.activity;

import com.dealmate.xiaohui.dealmate.model.DealModel;
import com.dealmate.xiaohui.dealmate.persistant.DbHelper;

import java.util.List;

/**
 * Created by xiaohui on 2/19/2017.
 */

public class ListFavoriteActivity extends ListGeneralActivity{
    @Override
    protected void getData() {
        super.getData();
        DbHelper dbHelper = new DbHelper(ListFavoriteActivity.this);
        List<DealModel> dealModels = dbHelper.getDeals();
        loadData(dealModels);
    }

    @Override
    protected void onStart() {
        super.onStart();
        getData();
    }
}
