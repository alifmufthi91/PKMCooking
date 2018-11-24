package com.example.kienz.cooqueen.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kienz.cooqueen.R;
import com.example.kienz.cooqueen.ui.SearchRecipe;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.picasso.transformations.BlurTransformation;
import model.ResepV2;

public class RecipeFragSearchViewholder extends RecyclerView.ViewHolder {
    @BindView(R.id.img_fragsearch)
    ImageView mRecipeImageView;
    @BindView(R.id.text_fragsearch)
    TextView mRecipeNameTextView;

    private Context mContext;
    private ArrayList<ResepV2> mRecipes = new ArrayList<>();

    public RecipeFragSearchViewholder(View itemView, ArrayList<ResepV2> recipes) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mContext = itemView.getContext();
        mRecipes = recipes;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int itemPosition = getLayoutPosition();

                Intent intent = new Intent(mContext, SearchRecipe.class);
                intent.putExtra("resep", mRecipes.get(itemPosition).getName());
                mContext.startActivity(intent);
            }
        });

    }

    public void bindRecipe(ResepV2 recipe) {
        Picasso.get().load(recipe.getImageUrl()).transform(new BlurTransformation(mContext,3,3)).into(mRecipeImageView);
        mRecipeNameTextView.setText(recipe.getName());
    }
}
