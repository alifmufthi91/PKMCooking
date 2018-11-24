package com.example.kienz.cooqueen.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kienz.cooqueen.R;

import java.util.ArrayList;

import model.ResepV2;

public class RecipeFragSearchAdapter extends RecyclerView.Adapter<RecipeFragSearchViewholder> {
    private ArrayList<ResepV2> mRecipes = new ArrayList<>();
    private Context mContext;


    public RecipeFragSearchAdapter(Context context, ArrayList<ResepV2> recipes) {
        mContext = context;
        mRecipes = recipes;
    }

    @Override
    public RecipeFragSearchViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_fragsearch, parent, false);
        RecipeFragSearchViewholder viewHolder = new RecipeFragSearchViewholder(view, mRecipes);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecipeFragSearchViewholder holder, int position) {
        holder.bindRecipe(mRecipes.get(position));

    }

    @Override
    public int getItemCount() {
        return mRecipes.size();
    }
}
