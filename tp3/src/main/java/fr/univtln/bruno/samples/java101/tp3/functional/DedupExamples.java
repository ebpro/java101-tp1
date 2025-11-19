package fr.univtln.bruno.samples.java101.tp3.functional;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * Examples to remove duplicates and show LinkedHashSet behavior.
 */
@Slf4j
public class DedupExamples {
    public static void removeDuplicatesPreserveOrder(List<String> withDup) {
        List<String> uniqueOrder = new ArrayList<>(new LinkedHashSet<>(withDup));
        log.info("[functional] Unique (LinkedHashSet): {}", uniqueOrder);
        log.info("[functional] Unique (Stream.distinct): {}", withDup.stream().distinct().toList());
    }

    public static void linkedHashSetOrder(List<String> values) {
        var ordered = new LinkedHashSet<>(values);
        log.info("[functional] LinkedHashSet order: {}", ordered);
    }
}
