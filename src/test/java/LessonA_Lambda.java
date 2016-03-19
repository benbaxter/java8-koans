import org.junit.Test;
import util.LessonResources.MyFirstFunction;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;
import static util.LessonResources.applyMyFirstFunction;

public class LessonA_Lambda {

    private boolean ___;
    private String _____;
    private int ____;

    /**
     * The most notable change that everyone talks about is lambda functions
     * with java 8. A lambda function is just syntactic sugar for anonymous
     * classes that only need one method to be implemented.
     *
     * The anonymous class can now be represented with an arrow like so:
     * () -> [do something]
     *
     * If you have a method with no args, you will still need to use parenthesis'
     * to represent that there is no input to the function. A function takes input
     * and transform it to output, that is what the arrow symbolizes; "Input will
     * be transformed with the following."
     *
     * <p>
     * For this test, we will be working with MyFirstFunction which is defined
     * for us in #{@link util.LessonResources.MyFirstFunction}, it is a simple
     * interface with one method
     * </p>
     */
    @Test
    public void _1_OneMethodInterfaceCanBeALambda() {
        MyFirstFunction function = new MyFirstFunction() {
            @Override
            public boolean apply() {
                return true;
            }
        };
        boolean result = applyMyFirstFunction(function);
        assertThat(result).isEqualTo(___);

        result = applyMyFirstFunction(() -> true);
        assertThat(result).isEqualTo(___);
    }

    /**
     * Okay, so empty parenthesis' feel odd but they start to make sense
     * when we want to have some input. For example, #{@link Comparator}
     * has 'one' method: compare(o1, o2). Now those parenthesis' will
     * contain our variables that we would normally define when we
     * define our method. Just like previously, we will use another
     * one line lambda expression. When using a one line expression,
     * there is an implicit return, so unlike with the anonymous class,
     * we do not have to write the explicit return statement.
     *
     * <p>
     *     In this test we will sort letters in reverse order using a
     *     custom comparator.
     * </p>
     *
     */
    @Test
    public void _2_lambdaWithInput() {

        List<String> letters = Arrays.asList("b", "c", "a", "d", "e");
        Collections.sort(letters, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return b.compareTo(a);
            }
        });

        assertThat(letters).hasSize(____);
        assertThat(letters.get(0)).isEqualTo(_____);
        assertThat(letters.get(4)).isEqualTo(_____);

        //reset letters back to unsorted
        letters = Arrays.asList("b", "c", "a", "d", "e");
        Collections.sort(letters, (a, b) -> b.compareTo(a));

        assertThat(letters).hasSize(____);
        assertThat(letters.get(0)).isEqualTo(_____);
        assertThat(letters.get(4)).isEqualTo(_____);
    }

    /**
     * In the last test we mentioned an implicit return. If you cannot perform
     * your lambda transformation in one line, which is sometimes better for
     * readability, you can create a block of code to be executed. Note that
     * a return statement will be required once you have multiple lines. Also
     * note that you will need {} to contain the scope of your block.
     *
     * <p>
     *     This test will sort names based on how many characters are in the name
     * </p>
     *
     */
    @Test
    public void _3_largerLambdas() {

        List<String> names = Arrays.asList("Alice", "Bob", "Charles", "Doe", "Ed");
        Collections.sort(names, (a, b) -> {
            int aSize = a.length();
            int bSize = b.length();
            return aSize - bSize;
        });

        assertThat(names.get(0)).isEqualTo(_____);
        assertThat(names.get(4)).isEqualTo(_____);
    }
}
