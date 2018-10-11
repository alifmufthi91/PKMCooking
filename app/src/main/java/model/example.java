package model;



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class example {

    @SerializedName("q")
    @Expose
    public String q;
    @SerializedName("from")
    @Expose
    public Integer from;
    @SerializedName("to")
    @Expose
    public Integer to;
    @SerializedName("params")
    @Expose
    public Params params;
    @SerializedName("more")
    @Expose
    public String more;
    @SerializedName("count")
    @Expose
    public String count;
    @SerializedName("hits")
    @Expose
    public List<Hit> hits = null;

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public Integer getFrom() {
        return from;
    }

    public void setFrom(Integer from) {
        this.from = from;
    }

    public Integer getTo() {
        return to;
    }

    public void setTo(Integer to) {
        this.to = to;
    }

    public Params getParams() {
        return params;
    }

    public void setParams(Params params) {
        this.params = params;
    }

    public String getMore() {
        return more;
    }

    public void setMore(String more) {
        this.more = more;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public List<Hit> getHits() {
        return hits;
    }

    public void setHits(List<Hit> hits) {
        this.hits = hits;
    }
}