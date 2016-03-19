package util;

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
    }

}
