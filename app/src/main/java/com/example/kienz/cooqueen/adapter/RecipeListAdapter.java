package com.example.kienz.cooqueen.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kienz.cooqueen.R;

import java.util.ArrayList;

import model.Recipe;

public class RecipeListAdapter extends RecyclerView.Adapter<RecipeViewholderRecommend> {
    private ArrayList<Recipe> mRecipes = new ArrayList<>();
    private Context mContext;

    public RecipeListAdapter(Context context, ArrayList<Recipe> recipes) {
        mContext = context;
        mRecipes = recipes;
    }

    @Override
    public RecipeViewholderRecommend onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listrecommend, parent, false);
        RecipeViewholderRecommend viewHolder = new RecipeViewholderRecommend(view, mRecipes);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecipeViewholderRecommend holder, int position) {
        holder.bindRecipe(mRecipes.get(position));
    }

    @Override
    public int getItemCount() {
        return mRecipes.size();
    }



}
