package com.dealmate.xiaohui.dealmate.model;

/**
 * Created by xiaohui on 3/6/2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LocalDealModel {

    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("imagePath")
    @Expose
    private String imagePath;
    @SerializedName("overview")
    @Expose
    private String overview;
    @SerializedName("source")
    @Expose
    private String source;
    @SerializedName("sourceUrl")
    @Expose
    private String sourceUrl;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("url")
    @Expose
    private String url;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
