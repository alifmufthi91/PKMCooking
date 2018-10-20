package com.example.kienz.cooqueen.adapter;

import android.app.LauncherActivity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kienz.cooqueen.R;

import java.util.ArrayList;
import java.util.List;

import model.Recipe;
import model.RecyclerHome;

public class RecipeFragHomeAdapter extends RecyclerView.Adapter<RecipeFragHomeViewholder> {

    List<RecyclerHome> items;
    Context context;

    public RecipeFragHomeAdapter(List<RecyclerHome> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public RecipeFragHomeViewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.viewholder_fraghome_recycler,viewGroup,false);
        return new RecipeFragHomeViewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeFragHomeViewholder holder, int i) {
        RecyclerHome item = items.get(i);
        holder.mRecyclerTextView.setText(item.getTitle());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
