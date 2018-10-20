package model;

import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.List;

public class RecyclerHome {
    String title;
//    List<RecyclerView> items;

    public RecyclerHome(String title) {
        this.title = title;
//        this.items = items;
    }

    public String getTitle() {
        return title;
    }
//
//    public List<RecyclerView> getItems() {
//        return items;
//    }
}
