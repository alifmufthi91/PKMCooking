package model;

import java.util.ArrayList;

public class Meal_Plan_Holder {
    private ArrayList<Meal_Plan> mealPlanArrayList;

    public Meal_Plan_Holder() {
        this.mealPlanArrayList = new ArrayList<Meal_Plan>();
        mealPlanArrayList.add(new Meal_Plan("type_breakfast","https://image.freepik.com/free-icon/sweet-dessert_318-29824.jpg","BREAKFAST"));
        mealPlanArrayList.add(new Meal_Plan("type_lunch","https://image.freepik.com/free-icon/sweet-dessert_318-29824.jpg","LUNCH"));
        mealPlanArrayList.add(new Meal_Plan("type_dinner","https://image.freepik.com/free-icon/sweet-dessert_318-29824.jpg","DINNER"));
    }

    public ArrayList<Meal_Plan> getMealPlanArrayList() {
        return mealPlanArrayList;
    }

    public void add(String type,String URL,String name){
        Meal_Plan mealPlan = new Meal_Plan(type,URL,name);

        switch (type){
            case "breakfast":
                mealPlanArrayList.add(getBreakfastIndex(mealPlanArrayList),mealPlan);break;
            case "dinner":
                mealPlanArrayList.add(getDinnerIndex(mealPlanArrayList),mealPlan);break;
            case "lunch":
                mealPlanArrayList.add(getLunchIndex(mealPlanArrayList),mealPlan);break;
        }

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
