package team.star.blog;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import team.star.blog.util.CommonUtil;

import java.math.BigInteger;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

class VirtualThreadTests {
    private int num, repeatTimes;
    private long start;

    private static Stream<NamedFactorialMethod> factorialMethods() {
        return Stream.of(
            new NamedFactorialMethod(CommonUtil::factorial, "factorial direct"),
            new NamedFactorialMethod(CommonUtil::factorialWithParallelStream, "factorial direct by parallel stream"),
            new NamedFactorialMethod(CommonUtil::factorialWithDivideConquer, "factorial with divide and conquer"),
            new NamedFactorialMethod(CommonUtil::factorialWithDP, "factorial with dynamic programming")
        );
    }

    @BeforeEach
    void setUp() {
        num = 100000;
        repeatTimes = 10;
        start = System.currentTimeMillis();
    }

    @AfterEach
    void tearDown() {
        long elapsedTime = System.currentTimeMillis() - start;
        System.out.println("Average cost in " + repeatTimes + " times: " + elapsedTime / repeatTimes + "ms");
    }

    @ParameterizedTest
    @MethodSource("factorialMethods")
    void testVirtualThread(NamedFactorialMethod method) {
        // Using virtual thread to calculate factorial
        System.out.println("VIRTUAL THREAD - " + method.getName());
        for (int i = 0; i < repeatTimes; i++) {
            try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
                executor.submit(() -> method.calculate(num));
            }
        }
    }

    @ParameterizedTest
    @MethodSource("factorialMethods")
    void testCachedThreadPool(NamedFactorialMethod method) {
        // Using cached thread pool to calculate factorial
        System.out.println("CACHED THREAD POOL - " + method.getName());
        for (int i = 0; i < repeatTimes; i++) {
            try (var executor = Executors.newCachedThreadPool()) {
                executor.submit(() -> method.calculate(num));
            }
        }
    }

    @FunctionalInterface
    interface FactorialMethod {
        BigInteger calculate(int num);
    }

    static class NamedFactorialMethod {
        private final FactorialMethod method;
        private final String name;

        NamedFactorialMethod(FactorialMethod method, String name) {
            this.method = method;
            this.name = name;
        }

        BigInteger calculate(int num) {
            return method.calculate(num);
        }

        String getName() {
            return name;
        }
    }
}
