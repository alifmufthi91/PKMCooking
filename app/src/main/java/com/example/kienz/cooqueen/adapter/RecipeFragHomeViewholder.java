package com.example.kienz.cooqueen.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.kienz.cooqueen.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import model.Recommender;

public class RecipeFragHomeViewholder extends RecyclerView.ViewHolder {
    @BindView(R.id.textView_fraghome) TextView mRecipeNameTextView;
    @BindView(R.id.recyclerView_fraghome_items) RecyclerView mRecipeNameRecyclerView;
    private Context mContext;
    private ArrayList<Recommender> mRecipes = new ArrayList<>();


    public RecipeFragHomeViewholder(View itemView, ArrayList<Recommender> recipes) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mContext = itemView.getContext();
        mRecipes = recipes;
    }

    public TextView getmRecipeNameTextView() {
        return mRecipeNameTextView;
    }

    public RecyclerView getmRecipeNameRecyclerView() {
        return mRecipeNameRecyclerView;
    }

    public Context getmContext() {
        return mContext;
    }

    public ArrayList<Recommender> getmRecipes() {
        return mRecipes;
    }
}
