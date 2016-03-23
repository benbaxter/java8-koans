import org.junit.Test;
import util.LessonResources;
import util.LessonResources.Food;
import util.LessonResources.Menu;
import util.LessonResources.Section;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.data.Offset.offset;
import static util.LessonResources.createMenu;

public class LessonD_Streams {

    /**
     * While the list always has the state of the items it holds,
     * a stream is temporal and performs operations on the 'stream'
     * of data from the collection it was created from.
     *
     * Streams are like a fire hose where it can see the water coming out
     * of the hose and performs operations on every drop of water.
     */
    @Test
    public void _1_aStreamIsJustALazyListLooper() {

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);

        /*
         * Here we create a stream from a list.
         */
        Stream<Integer> stream = list.stream();

        /*
         * The count method on a stream will 'iterate' over the stream of data
         * and count the number of elements it sees.
         */
        assertThat(list).hasSize(___);
        long count = stream.count();
        assertThat(count).isEqualTo(___);
    }

    /**
     * Streams are unlike lists in that they can be infinite!  But streams are
     * lazy and will not perform any work until a terminal operation is
     * invoked.
     */
    @Test
    public void _2_streamsAreLazy() {
        /*
         * This is an infinite stream of integers.  Don't try to count it, it
         * might take a while!  In this example, the first integer will be 1,
         * and each subsequent integer will be twice the previous one.
         */
        Stream<Integer> stream = Stream.iterate(1, i -> i * 2);

        /*
         * We can create a new stream that is limited to, at most, the first
         * four elements of the original stream.
         */
        Stream<Integer> firstFourElements = stream.limit(4);

        /*
         * Let's put these all in a string.  We'll use the forEach method to
         * loop over the stream.  This is called a "terminal" operation,
         * since it requires iterating over the elements in the stream.
         */
        StringBuilder sb = new StringBuilder();
        firstFourElements.forEach(sb::append);
        assertThat(sb.toString()).isEqualTo(_____);

        /*
         * Note that you can only iterate over a stream once.  Try uncommenting
         * the following line and see what happens:
         */
        //assertThat(firstFourElements.count()).isEqualTo(4);
    }

    /**
     * Streams have a bunch of standard operations defined. Let's look at
     * a few of them. A common one is a filter. How many times have you
     * written code that loops over a collection and performs an if statement
     * right away? Something along the lines of the following:
     * <pre>
     * for (int i : integers) {
     *    if (i % 2 == 0) {
     *        //do something
     *    }
     * }
     * </pre>
     */
    @Test
    public void _3_filteringStreams() {

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        Stream<Integer> stream = list.stream();

        /*
         * The filter operation gives us a stream back and won't call our
         * lambda until a terminal operator is invoked.
         */
        Stream<Integer> filteredStream = stream.filter(i -> i % 2 == 0);

        assertThat(filteredStream.count()).isEqualTo(___);
    }

    /**
     * Filtering streams is more useful when you combine it with other operations.
     * For example, we have a breakfast menu of food. Food has a name, whether it is
     * gluten free or not, and a price.
     *
     * We can filter the breakfast menu and calculate how much a gluten free breakfast
     * would cost. Note that stream operators can be chained together, but only the
     * last one can be a "terminal" operation that actually reads the stream.
     *
     * For extra fun, convert the filter and mapToDouble functions to use the :: operator.
     */
    @Test
    public void _4_combiningFilters() {

        List<Food> foods = Arrays.asList(
                new Food("pancakes", false, 2.0),
                new Food("buckwheat pancakes", true, 3.0),
                new Food("eggs", true, 1.0),
                new Food("toast", false, 1.0),
                new Food("muffins", false, 3.0));

        double price = foods.stream()
                .filter(f -> f.glutenFree)
                .mapToDouble(f -> f.price) // returns a DoubleStream, which has a sum() method
                .sum(); // sum() consumes the stream

        assertThat(price).isEqualTo(____);
    }

    /**
     * Another very common stream operation is "map". Map literally means "I
     * want to convert each item in the stream to something else." In math,
     * this is called a one-to-one mapping.  You send n items in, you get
     * n different items out.
     */
    @Test
    public void _5_mapping() {

        List<Food> foods = Arrays.asList(
                new Food("pancakes", false, 2.0),
                new Food("buckwheat pancakes", true, 3.0),
                new Food("eggs", true, 1.0),
                new Food("toast", false, 1.0),
                new Food("muffins", false, 3.0));

        /*
         * We can map all of the foods into their names and then collect them
         * into a list. We will get into collectors in the next test.
         */
        List<String> names = foods.stream()
                .map(Food::getName)
                .collect(Collectors.toList());

        assertThat(names).isNotEmpty();
        assertThat(names).containsOnlyOnce(_____);
        assertThat(names).containsOnlyOnce(_____);

        /*
         * Let's map numbers to even numbers as follows.
         *
         * <pre>
         *  1 -> 2
         *  2 -> 4
         *  3 -> 6
         *  ...
         *  n -> n * 2
         * </pre>
         *
         * Define the mapping function in the code below.
         */
        int integerSum = IntStream.of(1, 2, 3, 4, 5) // IntStream implements Stream<Integer>
                .map(_______)
                .sum();

        assertThat(integerSum).isEqualTo(2 + 4 + 6 + 8 + 10);
    }

    /**
     * Let's look at another common use case: converting streams of lists into
     * a cache map. Often we create small caches in memory with a particular key
     * for quick reference later. Converting lists to maps for caching is just
     * one example; there are many reasons why we would want to collect a stream
     * into a map (or a list).
     *
     * To perform this magic, we will need to get into the world of collecting
     * with collectors. The {@link Collectors} class has a bunch of ways to
     * mutate a stream into the type of collection you need.
     */
    @Test
    public void _6_collectingToMap() {

        List<Food> foods = Arrays.asList(
                new Food("pancakes", false, 2.0),
                new Food("buckwheat pancakes", true, 3.0),
                new Food("eggs", true, 1.0),
                new Food("toast", false, 1.0),
                new Food("muffins", false, 3.0));

        /*
         * In this test we will convert the breakfast menu into a cache map of
         * names to food. We will use the Collectors.toMap() function to
         * convert the stream into a map by providing a key function and a
         * value function. The key function returns the map key for each Food,
         * and the value function returns the map value for the Food -- in our
         * case, just the Food itself.
         */
        Map<String, Food> cache = foods.stream()
                .collect(Collectors.toMap(Food::getName, f -> f));

        assertThat(cache).isNotEmpty();
        assertThat(cache.keySet()).hasSize(___);
        assertThat(cache.get("eggs").getPrice()).isEqualTo(____);
    }

    /**
     * One of the trickier things to learn is the difference between "map" and
     * "flatMap".  So let's talk about "flatMap" now.
     *
     * We have found out that the map operator is a one to one function.
     * flatMap, on the other hand, is a one to many function. A great time to
     * use it is when you have a stream of lists of items in the stream and want
     * to flatten each list out into a stream of individual items.
     *
     * In this test we will have a list of menus. A menu contains a list of
     * breakfast foods, a list of lunch foods, and a list of dinner foods.
     *
     * We will have a list of 3 menus that we will stream and flatten out to
     * examine only the breakfast foods. Basically we will flatten one list
     * of items into a list of the sublists that it contains
     */
    @Test
    public void _7_flatMap() {

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

        /*
         * Now let's create a list of all the menu items from every section.
         */
        List<Food> breakfastFoods = menu.getSections().stream()
                .flatMap(section -> section.getItems().stream()) // Turns a Section into a Stream<Food>
                .collect(Collectors.toList());

        assertThat(breakfastFoods).isNotEmpty();
        assertThat(breakfastFoods).hasSize(___);

        /*
         * What's the average price of a menu item?  There's a Collector for
         * that!
         */
        double averagePrice = menu.getSections().stream()
                .flatMap(section -> section.getItems().stream())
                .collect(Collectors.averagingDouble(________));

        assertThat(averagePrice).isCloseTo(5.0, offset(0.0001));

        /*
         * How many expensive items are there on the menu?  An expensive menu
         * item costs at least $5.
         */
        long expensiveItemCount = menu.getSections().stream()
                .flatMap(_________)
                .filter(__________)
                .count();

        assertThat(expensiveItemCount).isEqualTo(5);
    }

    /**
     * A concept that has been in many languages, such as Scala
     * and libraries like Google's Guava, is Option, or {@link Optional}.
     * The idea is to be more explicit about whether a value can be null or not,
     * so as to prevent NullPointerExceptions. Instead of getting the object you
     * want, you get an interface that wraps an object. The interface has two
     * main methods, isPresent() and get(). isPresent() is equivalent to testing
     * if the object is null or not. The get() call will actually return the
     * object underneath the Optional.
     *
     * This Optional concept has be integrated into the stream API. When
     * searching for specific items in a stream, they will be wrapped in an
     * Optional, because it's possible that no matches will be found.
     *
     * Sometimes we have a list of items and want to find only one particular
     * item. In previous versions of Java, this would consist of setting up a
     * for loop, then instantly constructing an if statement for 'filtering'
     * for the particular object we are looking for. Before the for loop, we would
     * setup a placeholder variable that we would assign a value to from inside
     * the for loop. (Code smell: side effects) There is a possibility that this
     * variable could be null, forcing us to have a null check after the for loop
     * if we remember to add it. With streams, we can combine operations, Optionals,
     * and the findFirst/findAny operators to 'safely' perform the same actions.
     *
     * In this test we will combine all of our learnings so far. We will
     * use the same menu from the previous test, it has just been abstracted
     * into the {@link LessonResources} class. You will need to know the prices
     * of certain foods in order to pass the test. Feel free to read the source
     * code of the LessonResources class to discover the prices used or review the
     * previous test to see the prices.
     */
    @Test
    public void _8_finding() {

        Menu menu = createMenu();

        /*
         * Find the price of the first gluten-free pancakes menu item.
         */
        Optional<Double> priceOption = menu.getSections().stream()
                .flatMap(section -> section.getItems().stream())
                .filter(Food::isGlutenFree)
                .filter(f -> f.getName().contains("pancakes"))
                .map(Food::getPrice)
                .findFirst();

        assertThat(priceOption).isNotNull();
        assertThat(priceOption.isPresent()).isEqualTo(______);
        assertThat(priceOption.get()).isEqualTo(____);

        priceOption = menu.getSections().stream()
                .flatMap(section -> section.getItems().stream())
                .filter(Food::isGlutenFree)
                .filter(f -> f.getName().contains("toast"))
                .map(Food::getPrice)
                .findFirst();

        assertThat(priceOption).isNotNull();
        assertThat(priceOption.isPresent()).isEqualTo(______);
        /*
         * Will this next assertion fail? If so, why? If not,
         * continue to make the test pass.
         */
        assertThat(priceOption.get()).isEqualTo(____);

        /*
         * anyMatch tells us whether any item in the list matches a given
         * condition (called a "predicate").
         *
         * Fill in the blanks so that we see if there are any items in the
         * section named "Dinner" whose name contains "Chicken".
         */
        boolean anyChickenDinnerItems = menu.getSections().stream()
                .filter(___________)
                .flatMap(_________)
                .anyMatch(__________);

        assertThat(anyChickenDinnerItems).isTrue();

        /*
         * Look at the documentation for DoubleStream.min() and see if you can
         * chain operations together to find the price of the cheapest item in
         * the Dinner section.
         *
         * By the way, why did we have to declare cheapestDinnerPrice as an
         * OptionalDouble rather than just a double?
         * (Hint: Try replacing "Dinner" with "Dessert".)
         */
        OptionalDouble cheapestDinnerPrice = ____________;
        assertThat(cheapestDinnerPrice.isPresent()).isEqualTo(true);
        assertThat(cheapestDinnerPrice.getAsDouble()).isEqualTo(8.0);
    }

    /**
     * Something to watch out for is the "normal" behavior of trying to use side
     * effects to accomplish what you need.
     *
     * The simple example of trying to calculate a sum of a list of numbers is a
     * great example used to teach newbies nuances of any language. Oddly enough,
     * that same simple example can be used to teach us some gotchas of Java 8's
     * lambda implementation.
     */
    @Test
    public void _9_bewareTheFakeClosureAllure() {

        /*
         * Here's the traditional way to sum a list of numbers.
         */
        int sum = 0;
        for( int i = 0; i <= 10; ++i ) {
            sum += i;
        }

        assertThat(sum).isEqualTo(___);

        /*
         * Since lambdas are just compiled to anonymous inner classes, they
         * can't modify any outside variables.  So this code will not compile:
         */
//        Integer badSum = 0;
//        IntStream.rangeClosed(1, 10)
//                .forEach(i -> badSum += i);

        /*
         * Instead, we have to figure out how to do what we want without side
         * effects.  In this case, since summing a stream of numbers is so
         * common, there's a sum() method that already does what we want.
         */
        sum = IntStream.rangeClosed(1, 10)
                .sum();

        assertThat(sum).isEqualTo(___);
    }

    /**
     * A very powerful and flexible technique when working with streams is
     * "reduction".  The idea behind reduction is to apply some operation
     * repeatedly to each item in a stream to produce a single value.  In fact,
     * sum(), count(), and min() are all special cases of reduction.
     *
     * This is the main way to avoid side effects, as we discussed in the
     * previous test.
     */
    @Test
    public void _10_reduction() {
        List<Integer> ints = Arrays.asList(5, 2, 1, 4, 3);

        /*
         * Here's how we normally get the sum.  The awkward mapToInt() call is
         * required because ints.stream() returns a Stream<Integer>, which
         * doesn't have a sum() method.  That's because it wouldn't make much
         * sense to sum a Stream<Food> or Stream<Menu>.  But it makes perfect
         * sense to sum an IntStream, which is exactly what mapToInt() returns.
         */
        int sum = ints.stream().mapToInt(i -> i).sum();
        assertThat(sum).isEqualTo(___);

        /*
         * Now let's do the same thing via Stream.reduce().
         *
         * Calling stream.reduce(initialValue, fn) basically does this:
         *
         *     result = initialValue;
         *     for (item : stream)
         *         result = fn(result, item);
         *     return result;
         *
         * In our case, to sum the items in a list, we want to do this:
         *
         *     result = 0;
         *     for (item in stream)
         *         result = result + item;
         *     return result;
         *
         * We can see that our "initialValue" is 0, and our "fn" just adds the
         * two numbers.  So here's our reduce call:
         */
        sum = ints.stream().reduce(0, (a, b) -> a + b); // 0 is our initial value
        assertThat(sum).isEqualTo(___);

        /*
         * Here's how we normally call the min() function.
         */
        OptionalInt min = ints.stream().mapToInt(i -> i).min();
        assertThat(min.getAsInt()).isEqualTo(___);

        /*
         * This one-argument form of reduce() returns:
         *     - Optional.empty() if the stream is empty
         *     - The first item in the stream if the stream has one item
         *     - The result of a normal reduce() call (where the initial value
         *       is the first item in the stream) otherwise
         *
         * For extra credit, replace the lambda with a method reference.
         */
        Optional<Integer> reducedMin = ints.stream().reduce((a, b) -> Math.min(a, b));
        assertThat(reducedMin.get()).isEqualTo(1);

        /*
         * Now let's count items in the stream via reduce() instead of via
         * count().
         *
         * Hint: You don't care about one of the parameters to your lambda....
         */
        int count = ints.stream().reduce(___, _____________);
        assertThat(count).isEqualTo(5);

        /*
         * Okay, okay, I hear you say -- why would we ever use reduce() in real
         * life when we've got these sum(), count() and min() methods that
         * already do what we want to do?
         *
         * Let's take another look at our menu.  Up above, we figured out the
         * lowest price of any item on the Dinner section of the menu.  But
         * what we really want is the cheapest menu item itself, not just the
         * price.  See if you can figure out how to get this with filter,
         * flatMap and reduce.
         */
        Menu menu = createMenu();

        Optional<Food> cheapestDinnerMenuItem = ______________;
        assertThat(cheapestDinnerMenuItem.isPresent()).isTrue();
        assertThat(cheapestDinnerMenuItem.get().getName()).isEqualTo("Cheese Burger");
    }

    /*
     * Please do not change these variables.  They are required for the tests to
     * compile with the underscores in them.
     */
    private int ___;
    private double ____;
    private String _____;
    private boolean ______;
    private IntUnaryOperator _______;
    private ToDoubleFunction<Food> ________;
    private Function<Section, Stream<Food>> _________;
    private Predicate<Food> __________;
    private Predicate<Section> ___________;
    private OptionalDouble ____________;
    private BinaryOperator<Integer> _____________;
    private Optional<Food> ______________;
}
