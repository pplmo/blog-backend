package team.star.blog.util;

import java.math.BigInteger;
import java.util.stream.IntStream;

public class CommonUtil {
    private static final BigInteger[] dp = new BigInteger[10001];

    /**
     * calculate factorial directly
     *
     * @param n number
     * @return factorial of n
     */
    public static BigInteger factorial(int n) {
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
    public static BigInteger factorial2(int n) {
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
    public static BigInteger factorial3(int n) {
        if (n <= 20) { // 对于小于等于20的数，直接计算
            return smallFactorial(n);
        }

        int mid = n / 2;

        // 分治计算
        BigInteger left = factorial3(mid);
        BigInteger right = factorial3(n - mid);

        // 合并结果
        return left.multiply(right);
    }

    /**
     * calculate factorial using dynamic programming
     *
     * @param n number
     * @return factorial of n
     */
    public static BigInteger factorial4(int n) {
        if (n == 0) {
            return BigInteger.ONE;
        }

        if (dp[n] != null) {
            return dp[n];
        }

        dp[n] = BigInteger.valueOf(n).multiply(factorial4(n - 1));
        return dp[n];
    }

    private static BigInteger smallFactorial(int n) {
        return IntStream.rangeClosed(1, n)
            .mapToObj(BigInteger::valueOf)
            .reduce(BigInteger.ONE, BigInteger::multiply);
    }
}
