package solutions;

import org.junit.Test;
import util.LessonResources;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static util.LessonResources.createMenuOfMenus;
import static util.LessonResources.newFoodList;

public class LessonD_Solutions {

    private int ___;
    private double ____;
    private String _____;
    private boolean ______;

    /**
     * While the list always has the state of the items it holds,
     * a stream is temporal and performs operations on the 'stream'
     * of data from the collection it was created from.
     *
     * Streams are like a fire hose where it can see the water coming out
     * of the hose and performs operations on every drop of water.
     *
     */
    @Test
    public void _1_aStreamIsJustALazyListLooper() {

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        Stream<Integer> stream = list.stream();

        assertThat(list).hasSize(5);
        //the count operation will 'iterate' over the stream of data,
        //and count the number of elements it sees
        long count = stream.count();
        assertThat(count).isEqualTo(5);
    }

    /**
     * Streams have a bunch of standard operations defined. Let's look at
     * a few of them. A common one is a filter. How many times have you
     * written code that loops over a collection and performs an if statement
     * right away? Something along the lines of the following:
     * <code>
     * for( int i : integers ) {
     *    if( i % 2 == 0 ) {
     *        //do something
     *    }
     * }
     * </code>
     * Streams are lazy and will not perform any work until a finalizing
     * operation is invoked.
     */
    @Test
    public void _2_streamsAreLazy() {

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        Stream<Integer> stream = list.stream();
        //the filter operation gives us a stream back and won't perform
        //the 'isEven' operation until a 'final' operator is invoked
        Stream<Integer> filteredStream = stream.filter(i -> i % 2 == 0);

        assertThat(filteredStream.count()).isEqualTo(2);
    }

    /**
     * Filtering streams are more useful when you combine them with other operations.
     * For example, we have a breakfast menu of food. Food has a name, whether it is
     * gluten free or not, and a price.
     *
     * We can filter the breakfast menu and calculate how much a gluten free breakfast
     * would cost. Note that stream operators can be chained together.
     *
     * For extra fun, convert the filter and mapToDouble functions to use the :: operator
     */
    @Test
    public void _3_filtering() {

        List<LessonResources.Food> foods = Arrays.asList(new LessonResources.Food("pancakes", false, 2.0),
                new LessonResources.Food("buckwheat pancakes", true, 3.0),
                new LessonResources.Food("eggs", true, 1.0),
                new LessonResources.Food("toast", false, 2.0),
                new LessonResources.Food("muffins", false, 3.0));

        double price = foods.stream()
                .filter(f -> f.glutenFree)
                .mapToDouble(f -> f.price)
                .sum();

        assertThat(price).isEqualTo(4.0);
    }

    /**
     * One of the trickier concepts to get the hang of is the difference between map
     * and flatMap. Let's look at map first. Map literally means "I want to convert
     * this item in the stream to something else" as in a one to one mapping. If we
     * think in terms of mathematics, it is a function to apply on a set to get another
     * set of equal number of elements back. For example, we could map a set of numbers
     * to a set of even numbers like so:
     *
     *  1 -> 2
     *  2 -> 4
     *  3 -> 6
     *  4 -> 8
     *  <code>
     *  Arrays.asList(1, 2, 3, 4).stream()
     *      .map( n -> n * 2);
     *  </code>
     *
     *
     * We can map all of the foods into their names and then collect them
     * into a list. We will get into collectors in the next test.
     */
    @Test
    public void _4_mapping() {

        List<LessonResources.Food> foods = Arrays.asList(new LessonResources.Food("pancakes", false, 2.0),
                new LessonResources.Food("buckwheat pancakes", true, 3.0),
                new LessonResources.Food("eggs", true, 1.0),
                new LessonResources.Food("toast", false, 2.0),
                new LessonResources.Food("muffins", false, 3.0));

        List<String> names = foods.stream()
                .map(LessonResources.Food::getName)
                .collect(Collectors.toList());

        assertThat(names).isNotEmpty();
        assertThat(names).containsOnlyOnce("eggs");
        assertThat(names).containsOnlyOnce("muffins");
    }

    /**
     * Before we get back to map vs flatMap, let's look at another common use case.
     * Converting streams of lists into a cache map. Often we like create small caches
     * in memory with a particular key for quick reference later. Converting lists to
     * maps for caching is just one example, there are many reasons why we would want
     * to collect the stream into a map.
     *
     * To perform this magic, we will need to get into the world of collecting with
     * collectors. #{@link Collectors} has a bunch of ways to mutate a stream into
     * the type of collection you need.
     *
     * In this test we will collect the breakfast menu into a cache map of names to food.
     * We will use the Collectors.toMap() function to convert the stream into a map by
     * providing a key function and a value function. The key function will let us determine
     * what we want the keys to be for the map and the value function will let us determine
     * what we want the values to be in the map.
     */
    @Test
    public void _5_collectingToMap() {

        List<LessonResources.Food> foods = Arrays.asList(new LessonResources.Food("pancakes", false, 2.0),
                new LessonResources.Food("buckwheat pancakes", true, 3.0),
                new LessonResources.Food("eggs", true, 1.0),
                new LessonResources.Food("toast", false, 2.0),
                new LessonResources.Food("muffins", false, 3.0));

        Map<String, LessonResources.Food> cache = foods.stream()
                .collect(Collectors.toMap(LessonResources.Food::getName, f -> f));

        assertThat(cache).isNotEmpty();
        assertThat(cache.keySet()).hasSize(5);
        assertThat(cache.keySet()).containsOnlyOnce("toast");
        assertThat(cache.values()).hasSize(5);
    }

    /**
     * Getting back to map vs. flatMap. We have found out that the map
     * operator is a one to one function. flatMap on the other hand is
     * a one to many function. A great time to use it is when you have
     * a list of items in the stream and want to literally flatten the
     * list out into individual items in the stream.
     *
     * In this test we will have a list of menus. A menu contains a list of
     * breakfast foods, a list of lunch foods, and a list of dinner foods.
     *
     * We will have a list of 3 menus that we will stream and flatten out to
     * examine only the breakfast foods. Basically we will flatten one list
     * of items into a list of the sublists that it contains
     */
    @Test
    public void _6_flatMap() {

        List<LessonResources.Food> breakfast = Arrays.asList(new LessonResources.Food("pancakes", false, 2.0),
                new LessonResources.Food("buckwheat pancakes", true, 3.0),
                new LessonResources.Food("eggs", true, 1.0),
                new LessonResources.Food("toast", false, 2.0),
                new LessonResources.Food("muffins", false, 3.0));
        List<LessonResources.Food>  lunch = Arrays.asList(new LessonResources.Food("B.L.T.", false, 4.0),
                new LessonResources.Food("Chicken Salad", true, 3.0),
                new LessonResources.Food("Turkey Wrap", true, 5.0));
        List<LessonResources.Food> dinner = Arrays.asList(new LessonResources.Food("Country Fried Steak", false, 9.0),
                new LessonResources.Food("Cheese Burger", true, 8.0),
                new LessonResources.Food("Chicken Parmesan", true, 11.0),
                new LessonResources.Food("Salmon and Rice", false, 10.0));

        //menu contains breakfast and dinner
        LessonResources.Menu breakfastSpecialMenu = new LessonResources.Menu(breakfast, newFoodList(), dinner);
        //menu contains breakfast and lunch
        LessonResources.Menu brunchMenu = new LessonResources.Menu(breakfast, lunch, newFoodList());
        //menu contains lunch and dinner
        LessonResources.Menu supperMenu = new LessonResources.Menu(newFoodList(), lunch, dinner);

        List<LessonResources.Menu> menus = Arrays.asList(breakfastSpecialMenu, brunchMenu, supperMenu);

        List<LessonResources.Food> breakfastFoods = menus.stream()
                .flatMap(menu -> menu.getBreakfast().stream())
                .collect(Collectors.toList());

        assertThat(breakfastFoods).isNotEmpty();
        assertThat(breakfastFoods).hasSize(10);
    }

    /**
     * A concept that has been in many languages, such as Scala,
     * and libraries like Google's Guava is the concept Option or {@link Optional}.
     * The idea is to be more explicit and prevent NullPointerExceptions.
     * Instead of getting the object you want, you get an interface that
     * wraps object. The interface has two main methods, isPresent() and get().
     * isPresent() is equivalent to testing if the object is null or not. The
     * get() call will actually return the object underneath the Optional.
     *
     * This Optional concept has be integrated into the stream api. When search
     * or finding for specific items in a stream, they will be wrapped in the
     * Optional.
     *
     * Sometimes we have a list of items and want to find only one particular
     * item. In previous versions of java, this would consist of setting up a
     * for loop, then instantly constructing an if statement for 'filtering'
     * for the particular object we are looking for. Before the for loop, we would
     * setup a placeholder variable that we would assign a value to from inside
     * the for loop. (Code smell: side effects) There is a possibility that this
     * variable could be null, forcing us to have a null check after the for loop
     * if we remember to add it. With streams, we can combine operations, Optionals,
     * and the findFirst/findAny operators to 'safely' perform the same actions.
     *
     * In this test we will combine all of our learning's so far. We will
     * use the same menu from the previous test, it has just been abstracted
     * into the {@link LessonResources} class. You will need to know the prices
     * of certain foods in order to pass the test. Feel free to read the source
     * code of the LessonResources class to discover the prices used or review the
     * previous test to see the prices.
     *
     * We will flatten the menus into the breakfast items, filter for gluten free,
     * then chain another filter for pancakes (because who does not like pancakes),
     * then map to the price of the food item, and see what we find.
     */
    @Test
    public void _7_finding() {

        List<LessonResources.Menu> menus = createMenuOfMenus();

        Optional<Double> priceOption = menus.stream()
                .flatMap(menu -> menu.getBreakfast().stream())
                .filter(LessonResources.Food::isGlutenFree)
                .filter(f -> f.getName().contains("pancakes"))
                .map(LessonResources.Food::getPrice)
                .findFirst();

        assertThat(priceOption).isNotNull();
        assertThat(priceOption.isPresent()).isEqualTo(true);
        assertThat(priceOption.get()).isEqualTo(3.0);

        priceOption = menus.stream()
                .flatMap(menu -> menu.getBreakfast().stream())
                .filter(LessonResources.Food::isGlutenFree)
                .filter(f -> f.getName().contains("toast"))
                .map(LessonResources.Food::getPrice)
                .findFirst();

        assertThat(priceOption).isNotNull();
        assertThat(priceOption.isPresent()).isEqualTo(false);
        //will this next assertion fail? If so, why? If not,
        // continue to make the test pass
        try {
            assertThat(priceOption.get()).isNotNull();
            fail("There is no gluten free toast");
        } catch (NoSuchElementException e) {
            assertThat(e).hasMessage("No value present");
        }
    }

}
