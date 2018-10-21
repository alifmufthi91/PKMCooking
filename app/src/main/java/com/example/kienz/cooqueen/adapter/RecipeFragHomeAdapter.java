package com.example.kienz.cooqueen.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kienz.cooqueen.R;

import java.util.ArrayList;

import model.Recipe;
import model.Recommender;

public class RecipeFragHomeAdapter extends RecyclerView.Adapter<RecipeFragHomeViewholder> {
    private ArrayList<Recommender> recommenders;
    private Context mContext;


    public RecipeFragHomeAdapter(Context context, ArrayList<Recommender> recipes) {
        mContext = context;
        recommenders = recipes;
    }

    @Override
    public RecipeFragHomeViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_fraghome_recycler, parent, false);
        RecipeFragHomeViewholder viewHolder = new RecipeFragHomeViewholder(view, recommenders);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecipeFragHomeViewholder holder, int position) {
        holder.bindRecipe(recommenders.get(position));
    }

    @Override
    public int getItemCount() {
        return recommenders.size();
    }



}
