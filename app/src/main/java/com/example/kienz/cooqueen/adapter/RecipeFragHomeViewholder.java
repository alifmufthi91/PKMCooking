package com.example.kienz.cooqueen.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.kienz.cooqueen.R;

public class RecipeFragHomeViewholder extends RecyclerView.ViewHolder {
    TextView mRecyclerTextView;
//    RecyclerView mRecyclerView;


    public RecipeFragHomeViewholder(@NonNull View itemView) {
        super(itemView);
        mRecyclerTextView.findViewById(R.id.textView_fraghome);
//        mRecyclerView.findViewById(R.id.recyclerView_fraghome);

    }
}
