package team.star.blog;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import static java.lang.Thread.sleep;
import static java.time.Duration.ofSeconds;

class VirtualThreadTests {
    @Test
    void testVirtualThread() {
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            IntStream.range(0, 10000).forEach(i -> executor.submit(() -> {
                sleep(ofSeconds(1));
                System.out.println(i);
                return i;
            }));
        }
    }

    @Test
    void testCachedThreadPool() {
        try (var executor = Executors.newCachedThreadPool()) {
            IntStream.range(0, 10000).forEach(i -> executor.submit(() -> {
                sleep(ofSeconds(1));
                System.out.println(i);
                return i;
            }));
        }
    }
}
