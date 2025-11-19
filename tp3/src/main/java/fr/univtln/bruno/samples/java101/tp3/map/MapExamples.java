package fr.univtln.bruno.samples.java101.tp3.map;

import fr.univtln.bruno.samples.java101.tp3.Person;
import fr.univtln.bruno.samples.java101.tp3.functionnal.GroupingDisplayExamples;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * Examples demonstrating Map implementations and modern Map APIs (compute, merge, etc.).
 */
@Slf4j
public class MapExamples {
    /** Basic HashMap usage and update semantics. */
    public static void hashMapExample() {
        log.info("=== HashMap Example ===");
        Map<String, Person> people = new HashMap<>();
        people.put("P001", Person.of("Alice","Smith",30));
        people.put("P002", Person.of("Bob","Jones",25));
        people.put("P003", Person.of("Charlie","Brown",35));
        log.info("Size: {}", people.size());
        log.info("P001 -> {}", people.get("P001").getFullName());
        Person prev = people.put("P001", Person.of("Alice","Smith",31));
        log.info("Updated P001 age {} -> {}", prev.getAge(), people.get("P001").getAge());
        people.remove("P002");
        log.info("After remove P002 size: {}", people.size());
    }
    /** LinkedHashMap to preserve insertion order. */
    public static void linkedHashMapExample() {
        log.info("=== LinkedHashMap Example ===");
        Map<String,String> capitals = new LinkedHashMap<>();
        capitals.put("France","Paris"); capitals.put("Germany","Berlin"); capitals.put("Italy","Rome");
        capitals.forEach((c,a) -> log.info("{} -> {}", c,a));
    }
    /** TreeMap (sorted map) demonstration. */
    public static void treeMapExample() {
        log.info("=== TreeMap Example ===");
        NavigableMap<String,String> capitals = new TreeMap<>();
        capitals.put("Spain","Madrid"); capitals.put("France","Paris"); capitals.put("Italy","Rome"); capitals.put("Germany","Berlin");
        capitals.forEach((c,a) -> log.info("{} -> {}", c,a));
        log.info("FirstEntry {} LastEntry {}", capitals.firstEntry(), capitals.lastEntry());
    }
    /** Modern Map methods: getOrDefault, putIfAbsent, computeIfAbsent, computeIfPresent, compute, merge. */
    public static void modernMapMethodsExample() {
        log.info("=== Modern Map Methods ===");
        Map<String,Integer> scores = new HashMap<>();
        scores.put("Alice",100); scores.put("Bob",85);
        log.info("getOrDefault Charlie: {}", scores.getOrDefault("Charlie",0));
        scores.putIfAbsent("Bob",90); scores.putIfAbsent("David",90);
        scores.computeIfAbsent("Eve", k -> k.length()*10);
        scores.computeIfPresent("Alice", (k,v)-> v+10);
        scores.compute("Bob", (k,v)-> v==null?0:v+5);
        scores.merge("Alice",20,Integer::sum);
        scores.merge("Frank",75,Integer::sum);
        scores.replace("Bob", 95);
        log.info("Scores: {}", scores);
    }
    /** Different ways to iterate a map's entries, keys and values. */
    public static void iterationExample() {
        log.info("=== Iteration Example ===");
        Map<String,Integer> ages = Map.of("Alice",30,"Bob",25,"Charlie",35);
        for (Map.Entry<String,Integer> e: ages.entrySet()) log.info("Entry {}={} ", e.getKey(), e.getValue());
        ages.keySet().forEach(k -> log.info("Key {}", k));
        ages.values().forEach(v -> log.info("Val {}", v));
    }
    /** Counting and frequency aggregation examples using loops and merge. */
    public static void countingExample() {
        log.info("=== Counting Example ===");
        List<String> words = List.of("apple","banana","apple","cherry","banana","apple");
        Map<String,Integer> manual = new HashMap<>();
        for (String w: words) manual.put(w, manual.getOrDefault(w,0)+1);
        Map<String,Integer> mergeMap = new HashMap<>(); words.forEach(w -> mergeMap.merge(w,1,Integer::sum));
        log.info("Manual {} Merge {}", manual, mergeMap);
    }
    /** Grouping example implemented manually with computeIfAbsent. */
    public static void groupingExample() {
        log.info("=== Grouping Example ===");
        List<Person> people = List.of(
                Person.of("Alice","Smith",30),
                Person.of("Bob","Jones",25),
                Person.of("Charlie","Brown",35),
                Person.of("David","Smith",40)
        );
        GroupingDisplayExamples.showGroupingByLastName(people);
    }
    /** Immutable map creation and views. */
    public static void immutableMapExample() {
        log.info("=== Immutable Map Example ===");
        Map<String,Integer> m1 = Map.of("A",1,"B",2,"C",3);
        log.info("Map.of {}", m1);
        try { m1.put("D",4); } catch (UnsupportedOperationException e) { log.info("Cannot modify immutable map"); }
        Map<String,Integer> backing = new HashMap<>(Map.of("X",1,"Y",2));
        Map<String,Integer> view = Collections.unmodifiableMap(backing);
        backing.put("Z",3);
        log.info("View after backing change: {}", view);
    }
    public static void main(String[] args) {
        hashMapExample();
        linkedHashMapExample();
        treeMapExample();
        modernMapMethodsExample();
        iterationExample();
        countingExample();
        groupingExample();
        immutableMapExample();
    }
}
