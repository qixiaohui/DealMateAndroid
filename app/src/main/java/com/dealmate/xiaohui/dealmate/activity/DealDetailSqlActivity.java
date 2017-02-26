package com.dealmate.xiaohui.dealmate.activity;

import com.dealmate.xiaohui.dealmate.model.DealDetail;
import com.dealmate.xiaohui.dealmate.persistant.DbHelper;

/**
 * Created by xiaohui on 2/18/2017.
 */

public class DealDetailSqlActivity extends DealDetailBaseActivity {
    @Override
    protected void loadDetail(String id) {
        super.loadDetail(id);
        DbHelper dbHelper = new DbHelper(DealDetailSqlActivity.this);
        dealDetail = dbHelper.getDetail(Integer.parseInt(id));
        loadDataToView(dealDetail);
    }
}
