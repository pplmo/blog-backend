package team.star.blog;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class VirtualThreadTests {
    private int num, repeatTimes;
    private long start;

    private static Stream<NamedFactorialMethod> factorialMethods() {
        return Stream.of(
            new NamedFactorialMethod(Factorial::multiplyDirectly, "factorial direct"),
            new NamedFactorialMethod(Factorial::multiplyDirectlyButWithParallelStream, "factorial direct by parallel stream"),
            new NamedFactorialMethod(Factorial::withDivideConquer, "factorial with divide and conquer")
            // if test DP, it seems it only supports the max number of 20000, otherwise it will throw Connection reset
            // new NamedFactorialMethod(Factorial::withDP, "factorial with dynamic programming")
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
                Future<BigInteger> future = executor.submit(() -> method.calculate(num));
                Assertions.assertDoesNotThrow(() -> {
                    future.get(); // Wait for the result to keep the thread alive until the task is done
                });
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
                Future<BigInteger> future = executor.submit(() -> method.calculate(num));
                Assertions.assertDoesNotThrow(() -> {
                    future.get(); // Wait for the result to keep the thread alive until the task is done
                });
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

class Factorial {
    private static final List<BigInteger> dp = new ArrayList<>();

    static {
        dp.add(BigInteger.ONE); // 0! = 1
    }

    private Factorial() {
    }

    /**
     * calculate factorial directly
     *
     * @param n number
     * @return factorial of n
     */
    public static BigInteger multiplyDirectly(int n) {
        BigInteger result = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }

    /**
     * calculate factorial using stream and parallel
     *
     * @param n number
     * @return factorial of n
     */
    public static BigInteger multiplyDirectlyButWithParallelStream(int n) {
        return IntStream.rangeClosed(2, n)
            .parallel()
            .mapToObj(BigInteger::valueOf)
            .reduce(BigInteger.ONE, BigInteger::multiply);
    }

    /**
     * calculate factorial using divide and conquer
     *
     * @param n number
     * @return factorial of n
     */
    public static BigInteger withDivideConquer(int n) {
        if (n <= 20) { // for small number, use direct method
            return multiplyDirectlyButWithParallelStream(n);
        }

        int mid = n / 2;
        BigInteger left = withDivideConquer(mid);
        BigInteger right = withDivideConquer(n - mid);

        return left.multiply(right);
    }

    /**
     * calculate factorial using dynamic programming
     *
     * @param n number
     * @return factorial of n
     */
    public static BigInteger withDP(int n) {
        for (int i = dp.size(); i <= n; i++) {
            dp.add(BigInteger.valueOf(i).multiply(dp.get(i - 1)));
        }
        return dp.get(n);
    }
}
