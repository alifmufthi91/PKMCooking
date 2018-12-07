package model;

import java.util.ArrayList;

public class Meal_Plan_Holder {
    private ArrayList<Meal_Plan> mealPlanArrayList;

    public Meal_Plan_Holder() {
        this.mealPlanArrayList = new ArrayList<Meal_Plan>();
        ResepV2 breakfast = new ResepV2();
        breakfast.setName("SARAPAN");
        ResepV2 lunch = new ResepV2();
        lunch.setName("MAKAN SIANG");
        ResepV2 dinner = new ResepV2();
        dinner.setName("MAKAN MALAM");
        mealPlanArrayList.add(new Meal_Plan("type_breakfast",breakfast,"01"));
        mealPlanArrayList.add(new Meal_Plan("type_lunch",lunch,"02"));
        mealPlanArrayList.add(new Meal_Plan("type_dinner",dinner,"03"));
    }

    public ArrayList<Meal_Plan> getMealPlanArrayList() {
        return mealPlanArrayList;
    }

    public void add(String type,ResepV2 resep, String planId){
        Meal_Plan mealPlan = new Meal_Plan(type,resep,planId);

        switch (type){
            case "Sarapan":
                mealPlanArrayList.add(getBreakfastIndex(mealPlanArrayList),mealPlan);break;
            case "Makan siang":
                mealPlanArrayList.add(getLunchIndex(mealPlanArrayList),mealPlan);break;
            case "Makan malam":
                mealPlanArrayList.add(getDinnerIndex(mealPlanArrayList),mealPlan);break;
        }

    }

    public void delete(int position){
        this.mealPlanArrayList.remove(position);
    }

    public void clear(){
        this.mealPlanArrayList.clear();
    }

    public void add(Meal_Plan mealPlan){

        switch (mealPlan.getType()){
            case "breakfast":
                mealPlanArrayList.add(getBreakfastIndex(mealPlanArrayList),mealPlan);break;
            case "dinner":
                mealPlanArrayList.add(getDinnerIndex(mealPlanArrayList),mealPlan);break;
            case "lunch":
                mealPlanArrayList.add(getLunchIndex(mealPlanArrayList),mealPlan);break;
        }

    }

    private int getBreakfastIndex(ArrayList<Meal_Plan> meal_plans){
        int i=0;
        boolean found=false;
        while(!found && i<meal_plans.size()){
            if (isBreakfast(meal_plans.get(i))){
                found=true;
            }else{
                i++;
            }
        }
        return i+1;
    }
    private int getDinnerIndex(ArrayList<Meal_Plan> meal_plans){
        int i=0;
        boolean found=false;
        while(!found && i<meal_plans.size()){
            if (isDinner(meal_plans.get(i))){
                found=true;
            }else{
                i++;
            }
        }
        return i+1;
    }
    private int getLunchIndex(ArrayList<Meal_Plan> meal_plans){
        int i=0;
        boolean found=false;
        while(!found && i<meal_plans.size()){
            if (isLunch(meal_plans.get(i))){
                found=true;
            }else{
                i++;
            }
        }
        return i+1;
    }

    private boolean isBreakfast(Meal_Plan meal_plan){
        return meal_plan.getType().equals("type_breakfast");
    }
    private boolean isDinner(Meal_Plan meal_plan){
        return meal_plan.getType().equals("type_dinner");
    }
    private boolean isLunch(Meal_Plan meal_plan){
        return meal_plan.getType().equals("type_lunch");
    }
}
