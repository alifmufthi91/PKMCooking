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
import model.Resepop;

public class RecipeSearchViewholder extends RecyclerView.ViewHolder {
    @BindView(R.id.recipe_img_search)
    ImageView mRecipeImageView;
    @BindView(R.id.recipe_name_search)
    TextView mRecipeNameTextView;
    private Context mContext;
    private ArrayList<Resepop> mRecipes = new ArrayList<>();

    public RecipeSearchViewholder(View itemView, ArrayList<Resepop> recipes) {
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
                intent.putExtra("deskripsiResep",mRecipes.get(itemPosition).getDescription());
                intent.putExtra("sumberResep",mRecipes.get(itemPosition).getSourceUrl());
                intent.putExtra("sajianResep",mRecipes.get(itemPosition).getSajian());
                intent.putExtra("kandungannutResep",mRecipes.get(itemPosition).getNutritions());
                intent.putExtra("nilaireviewResep",mRecipes.get(itemPosition).getRating_value());
                intent.putExtra("jumlahreviewResep",mRecipes.get(itemPosition).getRating_giver());
                intent.putExtra("alatResep",mRecipes.get(itemPosition).getListString(mRecipes.get(itemPosition).getAlat()));
                intent.putExtra("bahanResep",mRecipes.get(itemPosition).getListString(mRecipes.get(itemPosition).getIngredients()));
                intent.putExtra("labelResep",mRecipes.get(itemPosition).getListString(mRecipes.get(itemPosition).getLabels()));
                intent.putExtra("instruksiResep",mRecipes.get(itemPosition).getListString(mRecipes.get(itemPosition).getInstruksi()));
                mContext.startActivity(intent);
            }
        });
    }

    public void bindRecipe(Resepop recipe) {
        Picasso.get().load(recipe.getImageUrl()).into(mRecipeImageView);
        mRecipeNameTextView.setText(recipe.getName());
    }
}
