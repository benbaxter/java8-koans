package solutions;

import org.junit.Test;
import util.LessonResources.MyFirstFunction;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;
import static util.LessonResources.applyMyFirstFunction;

public class LessonA_Solutions {

    /**
     * The most notable change that everyone talks about in Java 8 is lambda
     * functions. A lambda function in most languages is just an anonymous
     * function.  But Java doesn't have functions outside classes, so in Java
     * lambda functions are syntactic sugar for anonymous classes that only
     * need one method to be implemented.
     */
    @Test
    public void _1_OneMethodInterfaceCanBeALambda() {
        /**
         * For this test, we will be working with MyFirstFunction which is defined
         * for us in {@link util.LessonResources.MyFirstFunction}.  It is a simple
         * interface with one method, apply(), that returns a boolean.
         */
        MyFirstFunction function = new MyFirstFunction() {
            @Override
            public boolean apply() {
                return true;
            }
        };
        boolean result = applyMyFirstFunction(function);
        assertThat(result).isEqualTo(true);

        /*
         * Since MyFirstFunction has a single abstract method, we can replace
         * the class and method declarations with a lambda function.  The empty
         * parentheses are the argument list to the apply method.  The arrow
         * symbolizes transformation, since a function takes input and transforms
         * it to output.
         */
        result = applyMyFirstFunction(() -> {
            return true;
        });
        assertThat(result).isEqualTo(true);

        /*
         * In fact, for one-line lambda functions, you don't even need the return
         * statement or the braces.  The lambda function automatically returns the
         * value of the single expression after the arrow.
         */
        result = applyMyFirstFunction(() -> true);
        assertThat(result).isEqualTo(true);
    }

    /**
     * Okay, so empty parentheses feel odd but they start to make sense
     * when we want to have some input. For example, {@link Comparator}
     * has one method: compare(o1, o2). Now those parentheses will
     * contain the parameters to our method.
     */
    @Test
    public void _2_lambdaWithInput() {

        List<String> letters = Arrays.asList("b", "c", "a", "d", "e");

        /*
         * In this test we will sort letters in reverse order using a
         * custom comparator.  Here's the old way, using an anonymous
         * inner class.  If you're using IntelliJ, take a look at the
         * hint it provides.  It'll automatically convert this to a
         * lambda if you tell it to!
         */
        Collections.sort(letters, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return b.compareTo(a);
            }
        });

        assertThat(letters).hasSize(5);
        assertThat(letters.get(0)).isEqualTo("e");
        assertThat(letters.get(4)).isEqualTo("a");

        // Reset letters back to unsorted.
        letters = Arrays.asList("b", "c", "a", "d", "e");

        /*
         * Here's the new way, with a lambda function.
         */
        Collections.sort(letters, (a, b) -> b.compareTo(a));

        assertThat(letters).hasSize(5);
        assertThat(letters.get(0)).isEqualTo("e");
        assertThat(letters.get(4)).isEqualTo("a");
    }

    /**
     * Earlier we mentioned an implicit return. If putting all your code on
     * one line isn't feasible, you can create a block of code to be executed,
     * surrounded by curly braces. Note that a return statement will be
     * required once you have multiple lines.
     */
    @Test
    public void _3_largerLambdas() {

        /*
         * This test will sort names based on how many characters are in the name.
         */
        List<String> names = Arrays.asList("Alice", "Bob", "Charles", "Dave", "Ed");
        Collections.sort(names, (a, b) -> {
            int aSize = a.length();
            int bSize = b.length();
            return aSize - bSize;
        });

        assertThat(names.get(0)).isEqualTo("Ed");
        assertThat(names.get(4)).isEqualTo("Charles");
    }

    /**
     * Now it's time to try writing your own lambda function!
     *
     * For this example, you'll use a handy new interface in Java 8 called
     * {@link java.util.function.Function}.  It's pretty simple:
     *
     * <pre>
     *     public class Function<T, R>
     *         R apply(T t);
     *     }
     * </pre>
     *
     * In other words, a Function takes a single parameter of type T and
     * returns a value of type R.  There are lots of other new interfaces
     * like this, such as BiFunction and Predicate, that capture common
     * types of function so that we don't have to reinvent the wheel.  In
     * practice, pre-existing methods will already be using these
     * interfaces, and using a lambda will automatically convert to an
     * instance of one of these types anyway.
     */
    @Test
    public void _4_writeYourOwnLambda() {
        /*
         * See if you can write a lambda that will convert its integer
         * argument to a string using String.valueOf().  You should be
         * able to do this in a single expression.  And don't worry if
         * IntelliJ tells you that this can be replaced with a method
         * reference -- we'll cover this later.
         */
        Function<Integer, String> intToString = (x) -> String.valueOf(x);

        assertThat(intToString.apply(1)).isEqualTo("1");
        assertThat(intToString.apply(50)).isEqualTo("50");

        /*
         * Now let's write the opposite function, using
         * Integer.parseInt().  If it throws a NumberFormatException,
         * just return 0.
         */
        Function<String, Integer> stringToInt = (s) -> {
            try {
                return Integer.parseInt(s);
            } catch (NumberFormatException e) {
                return 0;
            }
        };

        assertThat(stringToInt.apply("1")).isEqualTo(1);
        assertThat(stringToInt.apply("2")).isEqualTo(2);
        assertThat(stringToInt.apply("abc")).isEqualTo(0);
    }

    /*
     * Please do not change these variables.  They are required for the tests to
     * compile with the underscores in them.
     */
    private boolean ___;
    private String _____;
    private int ____;
    private Function<Integer, String> ______;
    private Function<String, Integer> _______;
}
