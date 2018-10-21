package com.example.kienz.cooqueen.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kienz.cooqueen.R;
import com.example.kienz.cooqueen.ui.RecipeDetail;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import model.Recipe;
import model.Recommender;

public class RecipeFragHomeViewholder extends RecyclerView.ViewHolder {
    @BindView(R.id.textView_fraghome) TextView mRecipeNameTextView;
    private Context mContext;
    private ArrayList<Recommender> mRecipes = new ArrayList<>();


    public RecipeFragHomeViewholder(View itemView, ArrayList<Recommender> recipes) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mContext = itemView.getContext();
        mRecipes = recipes;
    }

    public void bindRecipe(Recommender recipe) {
        mRecipeNameTextView.setText(recipe.getTitle());
    }
}
