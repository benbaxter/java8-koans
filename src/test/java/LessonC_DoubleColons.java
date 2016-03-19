import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LessonC_DoubleColons {

    private boolean ___;
    private String _____;
    private int ____;

    /**
     * A new operator has also been introduces in java 8; '::'
     *
     * Venkat Subramaniam said it best, in a talk he gave, by
     * calling it the pass through operator. The way he described
     * it was to think of it as a way to short hand passing the input
     * through to the function you want to call.
     *
     * By itself this is not that powerful, but in the next lesson,
     * we will get into streams and chaining actions and this operator
     * will show its true strength.
     *
     * In this test we will convert the comparator implementation
     * with to use the :: operator. Our comparator and the
     * default sort will have the same results, we are just
     * demonstrating a simple example.
     */
    @Test
    public void _1_doubleColon() {

        List<String> letters = Arrays.asList("b", "c", "a", "d", "e");
        Collections.sort(letters, (a, b) -> a.compareTo(b));

        assertThat(letters).hasSize(____);
        assertThat(letters.get(0)).isEqualTo(_____);
        assertThat(letters.get(4)).isEqualTo(_____);

        //reset letters back to unsorted
        letters = Arrays.asList("b", "c", "a", "d", "e");
        //we know we have two variables, a and b as above
        //and we just want to turn around and call a.compareTo(b)
        //ie. pass our variables through into the string's
        //compare to method. By using the :: operator, we can
        //accomplish this.
        Collections.sort(letters, String::compareTo);

        assertThat(letters).hasSize(____);
        assertThat(letters.get(0)).isEqualTo(_____);
        assertThat(letters.get(4)).isEqualTo(_____);
    }
}
