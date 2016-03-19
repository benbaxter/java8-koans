package util;

public class LessonResources {

    public interface MyFirstFunction {
        boolean apply();
    }

    public static boolean applyMyFirstFunction(MyFirstFunction function) {
        return function.apply();
    }

}
