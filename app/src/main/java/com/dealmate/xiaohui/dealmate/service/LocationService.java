package com.dealmate.xiaohui.dealmate.service;

/**
 * Created by xiaohui on 3/6/2017.
 */

public interface LocationService  {
    public void requestLocationPermission();

    public String getCurrentCity();

    public void getData(String city);
}
