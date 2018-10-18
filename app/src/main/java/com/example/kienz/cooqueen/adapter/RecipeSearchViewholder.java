package com.example.kienz.cooqueen.adapter;

import android.content.Context;
import android.content.Intent;
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

public class RecipeSearchViewholder extends RecyclerView.ViewHolder {
    @BindView(R.id.recipe_img_search)
    ImageView mRecipeImageView;
    @BindView(R.id.recipe_name_search)
    TextView mRecipeNameTextView;
    private Context mContext;
    private ArrayList<Recipe> mRecipes = new ArrayList<>();

    public RecipeSearchViewholder(View itemView, ArrayList<Recipe> recipes) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mContext = itemView.getContext();
        mRecipes = recipes;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int itemPosition = getLayoutPosition();
                Intent intent = new Intent(mContext, RecipeDetail.class);
                intent.putExtra("namaResep", mRecipes.get(itemPosition).getName());
                intent.putExtra("gambarResep",mRecipes.get(itemPosition).getImageUrl());
                mContext.startActivity(intent);
            }
        });
    }

    public void bindRecipe(Recipe recipe) {
        Picasso.get().load(recipe.getImageUrl()).into(mRecipeImageView);
        mRecipeNameTextView.setText(recipe.getName());
    }
}
