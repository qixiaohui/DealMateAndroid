package com.dealmate.xiaohui.dealmate.model;

/**
 * Created by xiaohui on 1/22/2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DealModel {

    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("detail")
    @Expose
    private String detail;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("imagePath")
    @Expose
    private String imagePath;
    @SerializedName("listPrice")
    @Expose
    private String listPrice;
    @SerializedName("overview")
    @Expose
    private String overview;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("shipping")
    @Expose
    private String shipping;
    @SerializedName("title")
    @Expose
    private String title;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getListPrice() {
        return listPrice;
    }

    public void setListPrice(String listPrice) {
        this.listPrice = listPrice;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getShipping() {
        return shipping;
    }

    public void setShipping(String shipping) {
        this.shipping = shipping;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
