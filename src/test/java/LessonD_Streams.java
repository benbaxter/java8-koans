import org.junit.Test;
import util.LessonResources.Food;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class LessonD_Streams {

    private int ___;
    private double ____;

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

        assertThat(list).hasSize(___);
        //the count operation will 'iterate' over the stream of data,
        //and count the number of elements it sees
        long count = stream.count();
        assertThat(count).isEqualTo(___);
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

        assertThat(filteredStream.count()).isEqualTo(___);
    }

    /**
     * Filtering streams are more useful when you combine them with other operations.
     * For example, we have a breakfast menu of food. Food has a name, whether it is
     * gluten free or not, and a price.
     *
     * We can filter the breakfast menu and calculate how much a gluten free breakfast
     * would cost.
     *
     * For extra fun, convert the filter andd mapToDouble functions to use the :: operator
     */
    @Test
    public void _3_filtering() {

        List<Food> foods = Arrays.asList(new Food("pancakes", false, 2.0),
                                        new Food("buckwheat pancakes", true, 3.0),
                                        new Food("eggs", true, 1.0),
                                        new Food("toast", false, 2.0),
                                        new Food("muffins", false, 3.0));

        double price = foods.stream()
                .filter(f -> f.glutenFree)
                .mapToDouble(f -> f.price)
                .sum();

        assertThat(price).isEqualTo(____);
    }
}
