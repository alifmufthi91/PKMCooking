package com.example.kienz.cooqueen.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kienz.cooqueen.R;

import java.util.ArrayList;
import java.util.List;

import model.Resepi;

public class RecipeSearchAdapter  extends RecyclerView.Adapter<RecipeSearchViewholder> {
    private ArrayList<Resepi> mRecipes = new ArrayList<>();
    private Context mContext;


    public RecipeSearchAdapter(Context context, ArrayList<Resepi> recipes) {
        mContext = context;
        mRecipes = recipes;
    }

    public void swapItems(ArrayList<Resepi> items) {
        this.mRecipes = items;
        notifyDataSetChanged();
    }

    @Override
    public RecipeSearchViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_searchrecipe, parent, false);
        RecipeSearchViewholder viewHolder = new RecipeSearchViewholder(view, mRecipes);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecipeSearchViewholder holder, int position) {
        holder.bindRecipe(mRecipes.get(position));
    }

    @Override
    public int getItemCount() {
        return mRecipes.size();
    }



}
