package com.example.kienz.cooqueen.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kienz.cooqueen.R;

import java.util.ArrayList;

import model.Meal_Plan;

public class RecipeFragMealplanAdapter extends RecyclerView.Adapter<ViewHolder> {
    private ArrayList<Meal_Plan> mRecipes = new ArrayList<>();
    private Context mContext;

    public RecipeFragMealplanAdapter(Context context, ArrayList<Meal_Plan> recipes) {
        mContext = context;
        mRecipes = recipes;
    }

    @Override
    public int getItemViewType(int position) {
        String type = mRecipes.get(position).getType();
        if (type.equals("type_breakfast")||type.equals("type_dinner")||type.equals("type_lunch")){
            return 0;
        }else{
            return 1;
        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view;
        switch (viewType) {
            case 0:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.viewholder_fragplan_type,viewGroup,false);
                return new MealPlanTypeViewholder(view,mRecipes);
            case 1:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.viewholder_fragplan_item, viewGroup, false);
                return new MealPlanItemViewholder(view,mRecipes);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        String type = mRecipes.get(i).getType();
        if (type.equals("type_breakfast")||type.equals("type_dinner")||type.equals("type_lunch")) {
            ((MealPlanTypeViewholder)viewHolder).bindRecipe(mRecipes.get(i));
        }else{
            ((MealPlanItemViewholder)viewHolder).bindRecipe(mRecipes.get(i));
        }
    }

    @Override
    public int getItemCount() {
        return mRecipes.size();
    }

    public Meal_Plan getItem(int position) {
        return mRecipes.get(position);
    }

}
