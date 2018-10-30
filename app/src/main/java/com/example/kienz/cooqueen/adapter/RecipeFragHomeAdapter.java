package com.example.kienz.cooqueen.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kienz.cooqueen.R;
import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper;

import java.util.ArrayList;
import java.util.List;

import model.Recipe;
import model.Recommender;
import util.PaddingItemDecoration;

public class RecipeFragHomeAdapter extends RecyclerView.Adapter<RecipeFragHomeViewholder> {
    private ArrayList<Recommender> recommenders;
    private Context mContext;

    public RecipeFragHomeAdapter(Context context, ArrayList<Recommender> recipes) {
        mContext = context;
        recommenders = recipes;
    }

    public RecipeFragHomeAdapter(){
        recommenders = new ArrayList<Recommender>();
    }


    @Override
    public RecipeFragHomeViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_fraghome_recycler, parent, false);
        RecipeFragHomeViewholder viewHolder = new RecipeFragHomeViewholder(view, recommenders);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecipeFragHomeViewholder holder, int position) {
        Recommender recommender = recommenders.get(position);

        holder.mRecipeNameTextView.setText(recommender.getTitle());


        holder.mRecipeNameRecyclerView.setLayoutManager(new LinearLayoutManager(holder
                .mRecipeNameRecyclerView.getContext(),
                LinearLayoutManager.HORIZONTAL, false));

        SnapHelper snapHelperStart = new GravitySnapHelper(Gravity.START);
        snapHelperStart.attachToRecyclerView(holder.mRecipeNameRecyclerView);
        int size = 16; // Get the offset that you want
        holder.mRecipeNameRecyclerView.addItemDecoration(new PaddingItemDecoration(size));

        holder.mRecipeNameRecyclerView.setOnFlingListener(null);

        holder.mRecipeNameRecyclerView.setAdapter(new RecipeFragHomeItemsAdapter(holder.getmContext(),recommender.getRecipeList()));

    }

    @Override
    public int getItemCount() {
        return recommenders.size();
    }



}
