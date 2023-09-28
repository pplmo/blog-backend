package team.star.blog.util;

import java.math.BigInteger;
import java.util.stream.IntStream;

public class CommonUtil {
    private static final BigInteger[] dp = new BigInteger[10001];

    private CommonUtil() {
    }

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
    public static BigInteger factorialWithParallelStream(int n) {
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
    public static BigInteger factorialWithDivideConquer(int n) {
        if (n <= 20) { // 对于小于等于20的数，直接计算
            return smallFactorial(n);
        }

        int mid = n / 2;

        // 分治计算
        BigInteger left = factorialWithDivideConquer(mid);
        BigInteger right = factorialWithDivideConquer(n - mid);

        // 合并结果
        return left.multiply(right);
    }

    /**
     * calculate factorial using dynamic programming
     *
     * @param n number
     * @return factorial of n
     */
    public static BigInteger factorialWithDP(int n) {
        if (n == 0) {
            return BigInteger.ONE;
        }

        if (dp[n] != null) {
            return dp[n];
        }

        dp[n] = BigInteger.valueOf(n).multiply(factorialWithDP(n - 1));
        return dp[n];
    }

    private static BigInteger smallFactorial(int n) {
        return IntStream.rangeClosed(2, n)
            .parallel()
            .mapToObj(BigInteger::valueOf)
            .reduce(BigInteger.ONE, BigInteger::multiply);
    }
}
