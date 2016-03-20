package util;

import java.util.Arrays;
import java.util.List;

public class LessonResources {

    public interface MyFirstFunction {
        boolean apply();
    }

    public static boolean applyMyFirstFunction(MyFirstFunction function) {
        return function.apply();
    }

    public static class Food {

        String name;
        public boolean glutenFree;
        public double price;

        public Food(String name, boolean glutenFree, double price) {
            this.name = name;
            this.glutenFree = glutenFree;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public boolean isGlutenFree() {
            return glutenFree;
        }

        public double getPrice() {
            return price;
        }
    }

    public static class Menu {

        List<Food> breakfast;
        List<Food> lunch;
        List<Food> dinner;

        public Menu(List<Food> breakfast, List<Food> lunch, List<Food> dinner) {
            this.breakfast = breakfast;
            this.lunch = lunch;
            this.dinner = dinner;
        }

        public List<Food> getBreakfast() {
            return breakfast;
        }
    }

    public static List<Food> newFoodList(Food... foods) {
        return Arrays.asList(foods);
    }

    public static List<Menu> createMenuOfMenus() {
        List<Food> breakfast = Arrays.asList(new Food("pancakes", false, 2.0),
                new Food("buckwheat pancakes", true, 3.0),
                new Food("eggs", true, 1.0),
                new Food("toast", false, 2.0),
                new Food("muffins", false, 3.0));
        List<Food>  lunch = Arrays.asList(new Food("B.L.T.", false, 4.0),
                new Food("Chicken Salad", true, 3.0),
                new Food("Turkey Wrap", true, 5.0));
        List<Food> dinner = Arrays.asList(new Food("Country Fried Steak", false, 9.0),
                new Food("Cheese Burger", true, 8.0),
                new Food("Chicken Parmesan", true, 11.0),
                new Food("Salmon and Rice", false, 10.0));

        //menu contains breakfast and dinner
        Menu breakfastSpecialMenu = new Menu(breakfast, newFoodList(), dinner);
        //menu contains breakfast and lunch
        Menu brunchMenu = new Menu(breakfast, lunch, newFoodList());
        //menu contains lunch and dinner
        Menu supperMenu = new Menu(newFoodList(), lunch, dinner);

        return Arrays.asList(breakfastSpecialMenu, brunchMenu, supperMenu);
    }

}
