package com.example.kienz.cooqueen.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kienz.cooqueen.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import model.Meal_Plan;

public class MealPlanTypeViewholder extends RecyclerView.ViewHolder {
    @BindView(R.id.imageView_fragmealplan_type)
    ImageView mRecipeImageView;
    @BindView(R.id.textView_fragmealplan_type)
    TextView mRecipeNameTextView;

    private Context mContext;
    private ArrayList<Meal_Plan> mRecipes = new ArrayList<>();

    public MealPlanTypeViewholder(View itemView, ArrayList<Meal_Plan> recipes) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mContext = itemView.getContext();
        mRecipes = recipes;
    }

    public void bindRecipe(Meal_Plan recipe) {
        mRecipeNameTextView.setText(recipe.getName());
    }
}
