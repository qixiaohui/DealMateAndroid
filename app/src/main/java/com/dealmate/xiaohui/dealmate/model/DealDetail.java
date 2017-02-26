package com.dealmate.xiaohui.dealmate.model;

/**
 * Created by xiaohui on 1/22/2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DealDetail {

    @SerializedName("detailDescription")
    @Expose
    private String detailDescription;
    @SerializedName("expire")
    @Expose
    private String expire;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("mainPoster")
    @Expose
    private String mainPoster;

    public String getDetailDescription() {
        return detailDescription;
    }

    public void setDetailDescription(String detailDescription) {
        this.detailDescription = detailDescription;
    }

    public String getExpire() {
        return expire;
    }

    public void setExpire(String expire) {
        this.expire = expire;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getMainPoster() {
        return mainPoster;
    }

    public void setMainPoster(String mainPoster) {
        this.mainPoster = mainPoster;
    }

}
