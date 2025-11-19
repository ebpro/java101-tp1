package fr.univtln.bruno.samples.java101.tp3.functional;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Examples demonstrating parallel streams usage and common pitfalls.
 */
@Slf4j
public class ParallelStreamsExamples {
    public static void parallelSumExample() {
        log.info("=== Parallel sum example (measure) ===");
        List<Integer> data = IntStream.rangeClosed(1, 1_000_000).boxed().collect(Collectors.toList());

        long t0 = System.currentTimeMillis();
        long sumSeq = data.stream().mapToLong(Integer::longValue).sum();
        long t1 = System.currentTimeMillis();

        long sumParStart = System.currentTimeMillis();
        long sumPar = data.parallelStream().mapToLong(Integer::longValue).sum();
        long sumParEnd = System.currentTimeMillis();

        log.info("seq sum={} time={}ms", sumSeq, (t1 - t0));
        log.info("par sum={} time={}ms", sumPar, (sumParEnd - sumParStart));
    }

    public static void sharedMutablePitfall() {
        log.info("=== Shared mutable state pitfall ===");
        List<Integer> data = IntStream.rangeClosed(1, 1000).boxed().collect(Collectors.toList());

        AtomicInteger counter = new AtomicInteger(0);
        data.parallelStream().forEach(n -> counter.addAndGet(n));
        log.info("Counter (with parallel forEach) = {} (should be {})", counter.get(), data.stream().mapToInt(Integer::intValue).sum());

        int reduced = data.parallelStream().mapToInt(Integer::intValue).reduce(0, Integer::sum);
        log.info("Reduced (parallel) = {}", reduced);
    }

    public static void main(String[] args) {
        parallelSumExample();
        sharedMutablePitfall();
    }
}
