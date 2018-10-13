package com.example.kienz.cooqueen.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kienz.cooqueen.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import model.Recipe;

public class RecipeViewholderRecommend extends RecyclerView.ViewHolder {
    @BindView(R.id.recipe_img) ImageView mRecipeImageView;
    @BindView(R.id.recipe_name) TextView mRecipeNameTextView;
    private Context mContext;
    private ArrayList<Recipe> mRecipes = new ArrayList<>();

    public RecipeViewholderRecommend(View itemView, ArrayList<Recipe> recipes) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mContext = itemView.getContext();
        mRecipes = recipes;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                int itemPosition = getLayoutPosition();
//                Intent intent = new Intent(mContext, MainActivity.class);
//                intent.putExtra("position", itemPosition + "");
//                intent.putExtra("recipes", Parcels.wrap(mRecipes));
//                mContext.startActivity(intent);
            }
        });
    }

    public void bindRecipe(Recipe recipe) {
        Picasso.with(mContext).load(recipe.getImageUrl()).into(mRecipeImageView);
        mRecipeNameTextView.setText(recipe.getName());
    }
}
