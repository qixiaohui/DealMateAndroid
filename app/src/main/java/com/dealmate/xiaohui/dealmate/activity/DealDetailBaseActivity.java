package com.dealmate.xiaohui.dealmate.activity;

import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dealmate.xiaohui.dealmate.R;
import com.dealmate.xiaohui.dealmate.model.DealDetail;
import com.dealmate.xiaohui.dealmate.model.DealModel;
import com.dealmate.xiaohui.dealmate.network.RestClient;
import com.dealmate.xiaohui.dealmate.persistant.DbHelper;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by xiaohui on 1/28/2017.
 */

public class DealDetailBaseActivity extends ActivityBase {
    private ImageView productImage;
    private TextView productTitle;
    private TextView expire;
    private TextView price;
    private TextView originPrice;
    private TextView freeShipping;
    private WebView dealWeb;
    private Button checkDeal;
    protected String productId;
    protected String title;
    protected DbHelper dbHelper;
    protected DealModel dealModel;
    protected DealDetail dealDetail;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deal_detail);
        initView();
        actionBar();
    }

    @Override
    protected void initView() {
        super.initView();
        productImage = (ImageView) findViewById(R.id.product_image);
        productTitle = (TextView) findViewById(R.id.product_title);
        expire = (TextView) findViewById(R.id.expire);
        price = (TextView) findViewById(R.id.price);
        originPrice = (TextView) findViewById(R.id.origin_price);
        freeShipping = (TextView) findViewById(R.id.free_shipping);
        dealWeb = (WebView) findViewById(R.id.dealWeb);
        checkDeal = (Button) findViewById(R.id.checkout);

        dealWeb.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return true;
            }
        });
        try {
            String[] data = getIntent().getStringExtra("KEY").split("~");
            title = data[0];
            productId = data[1];
            dealModel = new Gson().fromJson(data[2], DealModel.class);
            loadDetail(this.productId);
        } catch (NullPointerException e) {
            title = "DealMate";
        }
    }

    @Override
    protected void actionBar() {
        toolbar.setTitle(title);
        toolbar.setNavigationIcon(R.drawable.back);
        super.actionBar();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.activity_detail_menu, menu);
        if(checkIsFavorite(dealModel.getId())) {
            menu.getItem(0).setIcon(R.drawable.like);
            menu.getItem(0).setChecked(true);
        } else {
            menu.getItem(0).setIcon(R.drawable.unlike);
            menu.getItem(0).setChecked(false);
        }
        return true;
    }

    protected boolean checkIsFavorite(int id) {
        dbHelper = new DbHelper(DealDetailBaseActivity.this);
        return dbHelper.checkExist(id);
    }

    protected void addFavorite() {
        this.dbHelper.insertDeal(dealModel, dealDetail);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.star) {
            item.setChecked(!item.isChecked());
            if(!item.isChecked()) {
                this.dbHelper.deleteDeal(dealModel.getId());
                Toast.makeText(DealDetailBaseActivity.this,
                        "Romved from favorite list", Toast.LENGTH_LONG)
                        .show();
            }else {
                addFavorite();
                Toast.makeText(DealDetailBaseActivity.this, "Added to favorite list",
                        Toast.LENGTH_LONG)
                        .show();
            }
            item.setIcon(item.isChecked()?R.drawable.like:R.drawable.unlike);
            return true;
        }
        return false;
    }

    protected void loadDetail(String productId) {
    }

    protected void loadDetail(DealDetail dealDetail) {

    }

    protected void loadDataToView(final DealDetail dealDetail) {
        this.dealDetail = dealDetail;
        Picasso.with(DealDetailBaseActivity.this)
                .load(dealModel.getImagePath().replace("124","496").replace("110","440"))
                .into(productImage);
        productTitle.setText(dealModel.getTitle());
        expire.setText("Expire: "+dealDetail.getExpire());
        price.setText(dealModel.getPrice());
        originPrice.setText(dealModel.getListPrice());
        originPrice.setPaintFlags(originPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        freeShipping.setText(dealModel.getShipping());
        dealWeb = (WebView) findViewById(R.id.dealWeb);
        dealWeb.getSettings().setJavaScriptEnabled(true);
        dealWeb.loadDataWithBaseURL("",
                dealDetail.getDetailDescription().replace("\\n", "").replace("\"", "")
                        .replace("<a", "<p").replace("</a","</p"),
                "text/html", "UTF-8", "");

        checkDeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://www.logicbuy.com/"+dealDetail.getLink()));
                startActivity(browserIntent);
//                DealWebViewActivity.invokeActivity(DealDetailBaseActivity.this,
//                        DealWebViewActivity.class,
//                        "KEY",
//                        "http://www.logicbuy.com/"+dealDetail.getLink());
            }
        });
    }
}
