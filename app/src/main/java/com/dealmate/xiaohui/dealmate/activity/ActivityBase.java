package com.dealmate.xiaohui.dealmate.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.dealmate.xiaohui.dealmate.R;

/**
 * Created by xiaohui on 1/28/2017.
 */

public class ActivityBase extends AppCompatActivity {
    protected Toolbar toolbar;
    protected void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
    }
    protected void actionBar() {
        setSupportActionBar(toolbar);
    }
    public static void invokeActivity(Activity fromActivity, Class c) {
        Intent intent = new Intent(fromActivity, c);
        fromActivity.startActivity(intent);
    }
    public static void invokeActivity(Activity fromActivity, Class c, String key, String data) {
        Intent intent = new Intent(fromActivity, c);
        intent.putExtra(key, data);
        fromActivity.startActivity(intent);
    }
    public static void invokeActivity(Activity fromActivity, Class c, String key, String data, ActivityOptionsCompat option) {
        Intent intent = new Intent(fromActivity, c);
        intent.putExtra(key, data);
        fromActivity.startActivity(intent, option.toBundle());
    }
}
