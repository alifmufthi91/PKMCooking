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
import model.Meal_Plan;

public class MealPlanItemViewholder extends RecyclerView.ViewHolder {
    @BindView(R.id.imageView_fragmealplan_item)
    ImageView mRecipeImageView;
    @BindView(R.id.textView_fragmealplan_item)
    TextView mRecipeNameTextView;


    private Context mContext;
    private ArrayList<Meal_Plan> mRecipes = new ArrayList<>();

    public MealPlanItemViewholder(View itemView, ArrayList<Meal_Plan> recipes) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mContext = itemView.getContext();
        mRecipes = recipes;

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int itemPosition = getLayoutPosition();
                Intent intent = new Intent(mContext, RecipeDetail.class);
                intent.putExtra("idResep",mRecipes.get(itemPosition).getResep().getRecipeId());
                intent.putExtra("namaResep", mRecipes.get(itemPosition).getResep().getName());
                intent.putExtra("gambarResep",mRecipes.get(itemPosition).getResep().getImageUrl());
                intent.putExtra("deskripsiResep",mRecipes.get(itemPosition).getResep().getDescription());
                intent.putExtra("sumberResep",mRecipes.get(itemPosition).getResep().getSourceUrl());
                intent.putExtra("sajianResep",mRecipes.get(itemPosition).getResep().getSajian());
                intent.putExtra("kandungannutResep",mRecipes.get(itemPosition).getResep().getNutritions());
                intent.putExtra("nilaireviewResep",mRecipes.get(itemPosition).getResep().getRating_value());
                intent.putExtra("jumlahreviewResep",mRecipes.get(itemPosition).getResep().getRating_giver());
                intent.putExtra("alatResep",mRecipes.get(itemPosition).getResep().getListString(mRecipes.get(itemPosition).getResep().getAlat()));
                intent.putExtra("bahanResep",mRecipes.get(itemPosition).getResep().getListString(mRecipes.get(itemPosition).getResep().getIngredients()));
                intent.putExtra("labelResep",mRecipes.get(itemPosition).getResep().getListString(mRecipes.get(itemPosition).getResep().getLabels()));
                intent.putExtra("instruksiResep",mRecipes.get(itemPosition).getResep().getListString(mRecipes.get(itemPosition).getResep().getInstruksi()));
                mContext.startActivity(intent);
            }
        });

    }

    public void bindRecipe(Meal_Plan recipe) {
        Picasso.get().load(recipe.getImage_url()).into(mRecipeImageView);
        mRecipeNameTextView.setText(recipe.getName());
    }
}
