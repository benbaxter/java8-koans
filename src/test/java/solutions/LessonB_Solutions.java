package solutions;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LessonB_Solutions {
    private boolean  ___;
    private int      ____;
    private String   _____;
    private Class<?> ______;

    /**
     * Now that we have seen some lambdas, let's expand on them.
     *
     * In the past it was common to make an abstract class or a
     * class with static methods that were more like helper methods
     * but were specific to our subclasses implementation. Now we can
     * do more with interfaces in java. These tests will expand on how.
     *
     * There is a new keyword called 'default' that can be used with interfaces.
     * You can declare full stateless methods in interfaces by using the default
     * keyword. The #{@link java.util.function.Predicate} class is a great example
     * of how the default keyword allows more functionality to be built into an
     * interface. A predicate has one method 'test' which returns a boolean yet
     * we know how to combine multiple tests to form and, or, and negate operations.
     *
     * <p>
     * For this test, use a custom comparator (defined below the test) that can
     * compare and reverse the comparison when needed.
     * </p>
     */
    @Test
    public void _1_introducingTheDefaultKeyword() {
        String x = "x";
        String y = "y";
        // Note that lambdas can be variables.
        // Just like in other functional programming languages,
        // lambdas can be passed around as function but they are
        // still classes under the covers
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
     * There is a new annotation called #{@link FunctionalInterface}
     * that is used to document and let java know that your interface
     * has one abstract method, aka one non-default method.
     *
     * This is not a real junit test but by uncommenting the @FunctionalInterface
     * a compilation error will be thrown. Uncomment the annotation and resolve
     * the compilation error by making 'subtract' become a default method that
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
     * demonstrate the usefulness. (Hint: its multiple inheritance!)
     *
     * Building from the MyInteger interface above, we will creating a
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
     * than one non-default method. This should look and feel more familiar
     * to 'regular' java with the exception that the class will also inherit
     * extra behavior from the interfaces. In previous versions of java, we
     * would have to create an abstract class and an interface to accomplish
     * the same behaviour.
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
    }
}
