package com.example.kienz.cooqueen.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kienz.cooqueen.R;

import java.util.ArrayList;

import model.Recipe;

public class RecipeFragHomeItemsAdapter extends RecyclerView.Adapter<RecipeFragHomeItemsViewholder> {
    private ArrayList<Recipe> mRecipes = new ArrayList<>();
    private Context mContext;


    public RecipeFragHomeItemsAdapter(Context context, ArrayList<Recipe> recipes) {
        mContext = context;
        mRecipes = recipes;
    }


    @Override
    public RecipeFragHomeItemsViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_fraghome_item, parent, false);
        RecipeFragHomeItemsViewholder viewHolder = new RecipeFragHomeItemsViewholder(view, mRecipes);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecipeFragHomeItemsViewholder holder, int position) {
        holder.bindRecipe(mRecipes.get(position));
    }

    @Override
    public int getItemCount() {
        return mRecipes.size();
    }



}
