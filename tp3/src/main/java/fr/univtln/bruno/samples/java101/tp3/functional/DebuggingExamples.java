package fr.univtln.bruno.samples.java101.tp3.functional;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * Small helpers showing how to debug stream pipelines: peek, sampling and safe terminal operations.
 */
@Slf4j
public class DebuggingExamples {
    public static void peekPipelineExample() {
        log.info("[functional] peek example");
        List<Integer> nums = List.of(1,2,3,4,5);
        List<Integer> result = nums.stream()
                .peek(n -> log.info("orig {}", n))
                .filter(n -> n % 2 == 0)
                .peek(n -> log.info("filtered {}", n))
                .map(n -> n * 2)
                .peek(n -> log.info("mapped {}", n))
                .toList();
        log.info("[functional] final result {}", result);
    }

    public static void safeFirstExample() {
        log.info("[functional] safe first example");
        List<Integer> empty = List.of();
        empty.stream().findFirst().ifPresentOrElse(
                v -> log.info("first {}", v),
                () -> log.info("no elements")
        );
    }
}
