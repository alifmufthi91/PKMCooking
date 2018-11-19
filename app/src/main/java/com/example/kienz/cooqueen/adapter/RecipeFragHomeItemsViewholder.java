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
import model.Resepi;

public class RecipeFragHomeItemsViewholder extends RecyclerView.ViewHolder {
    @BindView(R.id.imageView_fraghome_items) ImageView mRecipeImageView;
    @BindView(R.id.textView_fraghome_items) TextView mRecipeNameTextView;
    private Context mContext;
    private ArrayList<Resepi> mRecipes = new ArrayList<>();


    public RecipeFragHomeItemsViewholder(View itemView, ArrayList<Resepi> recipes) {
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

    public void bindRecipe(Resepi recipe) {
        Picasso.get().load(recipe.getImageUrl()).into(mRecipeImageView);
        mRecipeNameTextView.setText(recipe.getName());
    }
}
