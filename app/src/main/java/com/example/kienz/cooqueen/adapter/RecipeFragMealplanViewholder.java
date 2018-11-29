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
import model.Meal_Plan;

public class RecipeFragMealplanViewholder extends RecyclerView.ViewHolder {
    @BindView(R.id.imageView_fragmealplan_type)
    ImageView mRecipeImageView;
    @BindView(R.id.textView_fragmealplan_type)
    TextView mRecipeNameTextView;

    private Context mContext;
    private ArrayList<Meal_Plan> mRecipes = new ArrayList<>();

    public RecipeFragMealplanViewholder(View itemView, ArrayList<Meal_Plan> recipes) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mContext = itemView.getContext();
        mRecipes = recipes;
//        itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int itemPosition = getLayoutPosition();
//
//                Intent intent = new Intent(mContext, SearchRecipe.class);
//                intent.putExtra("resep", mRecipes.get(itemPosition).getName());
//                mContext.startActivity(intent);
//            }
//        });
    }

    public void bindRecipe(Meal_Plan recipe) {
        Picasso.get().load(recipe.getImage_url()).into(mRecipeImageView);
        mRecipeNameTextView.setText(recipe.getName());
    }
}
