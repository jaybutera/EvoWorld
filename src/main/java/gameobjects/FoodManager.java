package gameobjects;

import java.util.ArrayList;

public class FoodManager {
    public Food[] food;
    //public ArrayList<Food> foodToAdd = new ArrayList<>();
    public ArrayList<Food> foodToRemove = new ArrayList<>();

    public FoodManager (/*int num_food*/) {
        //this.food = new Food[num_food];
    }

    public void initFood (Food[] food) {
        this.food = food;
    }

    public void remove (Food food_entity) {
        if ( !foodToRemove.contains(food_entity) )
            foodToRemove.add(food_entity);
    }

    public void clearFood() {
        Food[] tmp_food = new Food[food.length - foodToRemove.size()];

        int j = 0;
        for (int i = 0; i < food.length; i++) {
            if (!foodToRemove.contains(food[i])) {
                tmp_food[j] = food[i];
                j++;
            }
        }

        food = tmp_food;
        //System.out.println("Food to remove length: " + foodToRemove.size());
        foodToRemove = new ArrayList<>();

        //System.out.println("Food length: " + food.length);
    }
}
