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

    public static class Section {
        String name;
        List<Food> items;

        public Section(String name, List<Food> items) {
            this.name = name;
            this.items = items;
        }

        public String getName() {
            return name;
        }

        public List<Food> getItems() {
            return items;
        }
    }

    public static class Menu {
        List<Section> sections;

        public Menu(Section... sections) {
            this.sections = Arrays.asList(sections);
        }

        public List<Section> getSections() {
            return sections;
        }
    }

    public static Menu createMenu() {
        List<Food> breakfast = Arrays.asList(
                new Food("pancakes", false, 2.0),
                new Food("buckwheat pancakes", true, 3.0),
                new Food("eggs", true, 1.0),
                new Food("toast", false, 1.0),
                new Food("muffins", false, 3.0));
        List<Food> lunch = Arrays.asList(
                new Food("B.L.T.", false, 4.0),
                new Food("Chicken Salad", true, 3.0),
                new Food("Turkey Wrap", true, 5.0));
        List<Food> dinner = Arrays.asList(
                new Food("Country Fried Steak", false, 9.0),
                new Food("Cheese Burger", true, 8.0),
                new Food("Chicken Parmesan", true, 11.0),
                new Food("Salmon and Rice", false, 10.0));

        /*
         * Our menu has three sections: breakfast, lunch, and dinner.
         */
        Menu menu = new Menu(
                new Section("Breakfast", breakfast),
                new Section("Lunch", lunch),
                new Section("Dinner", dinner));

        return menu;
    }

}
