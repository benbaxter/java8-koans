import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LessonC_DoubleColons {

    /**
     * A new operator has also been introduced in Java 8, "::".
     *
     * Venkat Subramaniam said it best, in a talk he gave, by
     * calling it the pass through operator. The way he described it
     * was to think of it as a way to short hand passing the input
     * through to the function you want to call.
     *
     * By itself this is not that powerful, but in the next lesson, we
     * will get into streams and chaining actions and this operator
     * will show its true strength.
     *
     * In this test we will convert the comparator implementation to use
     * the :: operator. Our comparator and the default sort will produce
     * the same results; we are just demonstrating a simple example.
     */
    @Test
    public void _1_doubleColon() {

        List<String> letters = Arrays.asList("b", "c", "a", "d", "e");

        /*
         * Here's the lambda function we saw earlier.
         */
        Collections.sort(letters, (a, b) -> a.compareTo(b));

        assertThat(letters).hasSize(____);
        assertThat(letters.get(0)).isEqualTo(_____);
        assertThat(letters.get(4)).isEqualTo(_____);

        // Reset letters back to unsorted.
        letters = Arrays.asList("b", "c", "a", "d", "e");

        /*
         * We know we have two variables, a and b as above, and we just
         * want to turn around and call a.compareTo(b). In other words,
         * we just want to pass our variables through into the String's
         * compareTo method. We can do this using the :: operator.
         */
        Collections.sort(letters, String::compareTo);

        assertThat(letters).hasSize(____);
        assertThat(letters.get(0)).isEqualTo(_____);
        assertThat(letters.get(4)).isEqualTo(_____);

        /*
         * What if we've defined our own sorting method on some
         * class other than String, and we want to use that instead?
         * We're in luck: the :: operator can be called on arbitrary
         * objects as well.  We've defined a compareStrings method
         * below that compares the lengths of the strings.  Let's
         * use that to sort a list of names.
         */
        List<String> names = Arrays.asList("Alice", "Bob", "Charles", "Dave", "Ed");
        Collections.sort(names, this::compareStrings);

        assertThat(names).hasSize(____);
        assertThat(names.get(0)).isEqualTo(_____);
        assertThat(names.get(4)).isEqualTo(_____);
    }

    private int compareStrings(String a, String b) {
        return a.length() - b.length();
    }

    /*
     * Please do not change these variables.  They are required for the tests to
     * compile with the underscores in them.
     */
    private boolean ___;
    private String _____;
    private int ____;
}
