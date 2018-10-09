package model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Params {

    @SerializedName("sane")
    @Expose
    public List<Object> sane = null;
    @SerializedName("q")
    @Expose
    public List<String> q = null;
    @SerializedName("from")
    @Expose
    public List<String> from = null;
    @SerializedName("app_key")
    @Expose
    public List<String> appKey = null;
    @SerializedName("to")
    @Expose
    public List<String> to = null;
    @SerializedName("app_id")
    @Expose
    public List<String> appId = null;

    public List<Object> getSane() {
        return sane;
    }

    public void setSane(List<Object> sane) {
        this.sane = sane;
    }

    public List<String> getQ() {
        return q;
    }

    public void setQ(List<String> q) {
        this.q = q;
    }

    public List<String> getFrom() {
        return from;
    }

    public void setFrom(List<String> from) {
        this.from = from;
    }

    public List<String> getAppKey() {
        return appKey;
    }

    public void setAppKey(List<String> appKey) {
        this.appKey = appKey;
    }

    public List<String> getTo() {
        return to;
    }

    public void setTo(List<String> to) {
        this.to = to;
    }

    public List<String> getAppId() {
        return appId;
    }

    public void setAppId(List<String> appId) {
        this.appId = appId;
    }
}