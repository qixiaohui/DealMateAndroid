package com.dealmate.xiaohui.dealmate.activity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.drawable.GradientDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.dealmate.xiaohui.dealmate.R;
import com.dealmate.xiaohui.dealmate.adapter.LocalDealListAdapter;
import com.dealmate.xiaohui.dealmate.service.LocationService;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by xiaohui on 3/6/2017.
 */

public class LocalDealListActivity extends ActivityBase implements LocationService{
    public static final int REQUEST_LOCATION =  1;
    RecyclerView localDealList;
    StaggeredGridLayoutManager staggeredGridLayoutManager;
    LocalDealListAdapter localDealListAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_deal_list);
        initView();
        actionBar();
        requestLocationPermission();
    }

    @Override
    protected void initView() {
        super.initView();
        localDealList = (RecyclerView) findViewById(R.id.localDealList);
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, 1);
        localDealList.setLayoutManager(staggeredGridLayoutManager);
        localDealList.setAdapter(localDealListAdapter);
    }

    @Override
    protected void actionBar() {
        toolbar.setTitle(R.string.local);
        toolbar.setNavigationIcon(R.drawable.back);
        super.actionBar();
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void requestLocationPermission() {
        LocationManager locationManager
                = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
            if(!checkPermission()) {
                ActivityCompat.requestPermissions(LocalDealListActivity.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_COARSE_LOCATION},
                        REQUEST_LOCATION);
            }
        else
            Toast.makeText(this,
                    "Your GPS is turned off, please turn on your GPS",
                    Toast.LENGTH_LONG).show();

        return;
    }

    @Override
    public String getCurrentCity() {
        LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        double longitude = location.getLongitude();
        double latitude = location.getLatitude();
        Geocoder geoCoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses = geoCoder.getFromLocation(longitude, latitude, 1);
            System.out.println(addresses.get(0).getAddressLine(0));
        } catch (Exception e) {
            Log.e("LOCATION", e.getMessage());
        }
        return null;
    }

    @Override
    public void getData(String city) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_LOCATION:
                if(grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getData(getCurrentCity());
                } else
                    Toast.makeText(LocalDealListActivity.this,
                            "Without known your location we can't find the local deals",
                            Toast.LENGTH_LONG).show();
        }

    }
}
