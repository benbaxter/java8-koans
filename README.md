##Java 8 Koans
___

This koan was heavily influenced by the [rxjava-koans](https://github.com/mutexkid/rxjava-koans).

The Koans walk you through some of the most notable features introduced in Java 8.

The koans are broken out into subjects by file. Each koan file builds up your knowledge of java 8 and builds upon itself. It will stop at the first place you need to correct.

Some koans simply need to have the correct answer substituted for an incorrect one. Some, however, require you to supply your own answer. If you see the method __ (a double underscore) listed, it is a hint to you to supply your own code in order to make it work correctly. Your task is to make each test pass!


### Instructions for how to run the project

1. Java 8 is needed for the exercise. [Download from here](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) if you don't have it already. 
2. [download IntelliJ Community Edition](https://www.jetbrains.com/idea/download/)
3. `git clone https://github.com/benbaxter/java8-koans.git`
4. In Intellij, select File > Import Project... and select the cloned directory
5. In the Import Project dialog, select Import Project from External Model, choose Gradle and click next.
6. On the next screen, make sure "use default gradle wrapper" is selected and click Finish
7. Last, under File > Project Structure, set Project SDK: to Java 1.8 and click ok!

Run the test suite by right clicking on `src/test/java` and selecting `Run 'All Tests'`.
5. The test suite will fail - make each test pass!

For more information about what's new with Java 8, [check out oracle's documentation](http://www.oracle.com/technetwork/java/javase/8-whats-new-2157071.html).

