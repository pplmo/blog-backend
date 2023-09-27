package team.star.blog;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import team.star.blog.util.CommonUtil;

import java.math.BigInteger;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

import static team.star.blog.util.CommonUtil.factorial;

class VirtualThreadTests {
    private int num;
    private long start;

    private static Stream<FactorialMethod> factorialMethods() {
        return Stream.of(CommonUtil::factorial, CommonUtil::factorial2, CommonUtil::factorial3, CommonUtil::factorial4);
    }

    @BeforeEach
    void setUp() {
        num = 10;
        start = System.currentTimeMillis();
    }

    @AfterEach
    void tearDown(TestInfo testInfo) {
        long elapsedTime = System.currentTimeMillis() - start;
        System.out.println(testInfo.getDisplayName() + " costs: " + elapsedTime + "ms");
    }

    @Test
    void testVirtualThread() {
        // Using virtual thread to calculate factorial
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            executor.submit(() -> factorial(num));
        }
    }

    @ParameterizedTest
    @MethodSource("factorialMethods")
    void testVirtualThread(FactorialMethod method) {
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            executor.submit(() -> method.calculate(num));
        }
    }

    @ParameterizedTest
    @MethodSource("factorialMethods")
    void testCachedThreadPool(FactorialMethod method) {
        // Using cached thread pool to calculate factorial
        try (var executor = Executors.newCachedThreadPool()) {
            executor.submit(() -> method.calculate(num));
        }
    }

    @FunctionalInterface
    interface FactorialMethod {
        BigInteger calculate(int num);
    }
}
