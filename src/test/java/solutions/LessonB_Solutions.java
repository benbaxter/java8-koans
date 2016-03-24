package solutions;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LessonB_Solutions {

    /**
     * Now that we have seen some lambdas, let's expand on them.
     *
     * In the past it was common to make an abstract class or a
     * class with static methods that were more like helper methods
     * but were specific to our subclass's implementation. Now we can
     * do more with interfaces in java. These tests will expand on how.
     *
     * There is a new keyword called "default" that can be used to define full
     * methods in interfaces. Since you can't define member variables in
     * interfaces, these default methods must be stateless.
     *
     * The {@link java.util.function.Predicate} class is a great example
     * of how the default keyword allows more functionality to be built into an
     * interface. A predicate has one method "test", which returns a boolean.
     * However, we can combine any number of predicates in standard ways to
     * form "and", "or", and "negate" operations, no matter how those
     * individual predicates are implemented.
     */
    @Test
    public void _1_introducingTheDefaultKeyword() {
        String x = "x";
        String y = "y";

        /*
         * For this test, use a custom comparator (defined below the test) that
         * can compare and reverse the comparison when needed.
         *
         * Just like in other functional programming languages, lambdas can be
         * passed around as functions, but they are still classes under the
         * covers.
         */
        MyComparator<String> comparator = (a, b) -> a.compareTo(b);

        assertThat(comparator.compare(x, y)).isEqualTo(-1);
        assertThat(comparator.reverseCompare(x, y)).isEqualTo(1);
    }

    public interface MyComparator<T> {

        int compare(T a, T b);

        default int reverseCompare(T a, T b) {
            return -1 * compare(a, b);
        }
    }

    /**
     * There is a new annotation called {@link FunctionalInterface}
     * that is used to let Java know that your interface contains exactly
     * one abstract method, aka one non-default method. If someone tries to add
     * an additional method to the interface, the compiler will issue an error.
     *
     * This is not a real JUnit test but by uncommenting the @FunctionalInterface
     * below, a compilation error will be thrown. Uncomment the annotation and
     * resolve the compilation error by making "subtract" a default method that
     * leverages the add(int n) method.
     *
     * Hint: the key to thinking about default methods is to stay stateless.
     */
    @FunctionalInterface
    public interface MyInteger {

        int add(int n);

        default int subtract(int n) {
            return add(-n);
        }
    }

    /**
     * This might seem really academic and not very useful and you are correct.
     * We are going to have one more academic example but hopefully this will
     * demonstrate the usefulness. (Hint: it's multiple inheritance!)
     *
     * Building from the MyInteger interface above, we will create a
     * MyLoggingInteger that formats the implementation for logging.
     *
     * By implementing both interfaces, we will get some functionality
     * for free like the format and subtract methods.
     */
    @FunctionalInterface
    public interface MyLoggingInteger {
        void log();
        default String format(int i) {
            return String.format("My value is %d", i);
        }
    }

    /**
     * When 'extending' multiple @FunctionalInterfaces, you will lose the
     * lambda-ness ability since you must provide implementations for more
     * than one non-default method. This should look and feel more familiar,
     * except that the class will also inherit extra behavior from the
     * interfaces. In previous versions of Java, we would have to create an
     * abstract class and an interface to accomplish the same behaviour.
     */
    static class MyAwesomeInteger implements MyInteger, MyLoggingInteger {
        int value;

        public MyAwesomeInteger(int value) {
            this.value = value;
        }

        @Override
        public int add(int n) {
            return value + n;
        }

        @Override
        public void log() {
            System.out.println(format(value));
        }
    }

    /**
     * In this test, we will see that our class is a instance of two
     * 'abstract' classes by using multiple inheritance with abstract
     * interfaces instead.
     */
    @Test
    public void _2_multipleInheritance() {

        MyAwesomeInteger i = new MyAwesomeInteger(42);

        assertThat(i).isInstanceOf(MyInteger.class);
        assertThat(i).isInstanceOf(MyLoggingInteger.class);
        assertThat(i.subtract(2)).isEqualTo(40);
    }

    /*
     * Please do not change these variables.  They are required for the tests to
     * compile with the underscores in them.
     */
    private boolean  ___;
    private int      ____;
    private String   _____;
    private Class<?> ______;
}
