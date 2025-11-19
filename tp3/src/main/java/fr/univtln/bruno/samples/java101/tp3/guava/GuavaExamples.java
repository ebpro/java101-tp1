package fr.univtln.bruno.samples.java101.tp3.guava;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import lombok.extern.slf4j.Slf4j;

/**
 * Small Guava Multimap example used for demonstration in TP3.
 */
@Slf4j
public class GuavaExamples {
    public static void main(String[] args) {
        Multimap<String, String> mm = ArrayListMultimap.create();
        mm.put("key1", "v1");
        mm.put("key1", "v2");
        mm.put("key2", "v3");

        for (String k : mm.keySet()) {
            log.info("{} -> {}", k, mm.get(k));
        }
    }
}
