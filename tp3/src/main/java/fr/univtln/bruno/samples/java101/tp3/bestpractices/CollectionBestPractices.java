package fr.univtln.bruno.samples.java101.tp3.bestpractices;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * Demonstrates best practices for using collections in Java.
 */
@Slf4j
public class CollectionBestPractices {

  /**
   * Private constructor to prevent instantiation.
   */
  private CollectionBestPractices() {
  }

  /**
   * Demonstrates defensive copying to protect internal state.
   */
  public static void defensiveCopyingExample() {
    log.info("=== Defensive Copying Example ===");

    // BAD: Direct exposure of mutable collection
    class BadExample {
      private final List<String> items = new ArrayList<>();

      public List<String> getItems() {
        return items;  // DANGEROUS!
      }
    }

    BadExample bad = new BadExample();
    bad.getItems().add("Item 1");
    List<String> exposed = bad.getItems();
    exposed.add("Item 2");  // External modification!
    log.debug("BadExample internal items: {}", bad.getItems());
    log.info("Bad example - items modified externally: {}", bad.getItems());

    // GOOD: Defensive copy
    class GoodExample {
      private final List<String> items = new ArrayList<>();

      public List<String> getItems() {
        return List.copyOf(items);  // Immutable copy
      }

      public void addItem(String item) {
        items.add(item);
      }
    }

    GoodExample good = new GoodExample();
    good.addItem("Item 1");
    List<String> copy = good.getItems();
    try {
      copy.add("Item 2");  // Throws UnsupportedOperationException
    } catch (UnsupportedOperationException e) {
      log.debug("GoodExample returned list (immutable) snapshot: {}", copy);
      log.info("Good example - cannot modify returned list");
    }
  }

  /**
   * Demonstrates choosing the right collection type.
   */
  public static void choosingCollectionTypeExample() {
    log.info("=== Choosing Collection Type Example ===");
    List<String> arrayList = new ArrayList<>();
    arrayList.add("A");
    arrayList.add("B");
    arrayList.add("C");
    log.info("ArrayList access index 1: {}", arrayList.get(1));

    LinkedList<String> linkedList = new LinkedList<>();
    linkedList.addFirst("First");
    linkedList.addLast("Last");
    log.info("LinkedList: {}", linkedList);

    Set<String> hashSet = new HashSet<>();
    hashSet.add("A");
    hashSet.add("A");
    log.info("HashSet uniqueness: {}", hashSet);

    TreeSet<Integer> treeSet = new TreeSet<>(Set.of(5, 1, 3, 2, 4));
    log.info("TreeSet sorted: {}", treeSet);
    log.info("TreeSet headSet(<4): {}", treeSet.headSet(4));

    Map<String, Integer> hashMap = new HashMap<>();
    hashMap.put("Alice", 30);
    log.info("HashMap lookup Alice: {}", hashMap.get("Alice"));
  }

  /**
   * Immutability best practices.
   */
  public static void immutabilityExample() {
    log.info("=== Immutability Example ===");
    List<String> immutableList = List.of("A", "B", "C");
    Set<String> immutableSet = Set.of("X", "Y", "Z");
    Map<String, Integer> immutableMap = Map.of("A", 1, "B", 2);
    log.info("Immutable list: {}", immutableList);
    log.info("Immutable set: {}", immutableSet);
    log.info("Immutable map: {}", immutableMap);
    try {
      immutableList.add("D");
    } catch (UnsupportedOperationException e) {
      log.info("Cannot modify immutable list");
    }

    List<String> mutable = new ArrayList<>(List.of("A", "B", "C"));
    List<String> copy = List.copyOf(mutable);
    mutable.add("D");
    log.info("Mutable after add: {}", mutable);
    log.info("Immutable copy unchanged: {}", copy);
  }

  /**
   * Proper initialization examples.
   */
  public static void initializationExample() {
    log.info("=== Initialization Example ===");
    List<String> emptyList = Collections.emptyList();
    Set<String> emptySet = Collections.emptySet();
    Map<String, Integer> emptyMap = Collections.emptyMap();
    log.info("Empty collections ready");

    List<String> singletonList = Collections.singletonList("Only");
    Set<String> singletonSet = Collections.singleton("Only");
    Map<String, Integer> singletonMap = Collections.singletonMap("Key", 1);
    log.info("Singletons: {}, {}, {}", singletonList, singletonSet, singletonMap);

    List<String> modernList = List.of("A", "B", "C");
    Set<String> modernSet = Set.of("X", "Y", "Z");
    Map<String, Integer> modernMap = Map.of("A", 1, "B", 2);
    log.info("Modern init: {}, {}, {}", modernList, modernSet, modernMap);

    List<String> withCapacity = new ArrayList<>(100);
    Map<String, Integer> mapWithCapacity = new HashMap<>(100);
    log.info("Pre-sized collections created (100)");
  }

  /**
   * Null handling.
   */
  public static void nullHandlingExample() {
    log.info("=== Null Handling Example ===");
    try {
      List.of("A", null, "C");
    } catch (NullPointerException e) {
      log.info("List.of() rejects nulls");
    }
    List<String> arrayList = new ArrayList<>();
    arrayList.add("A");
    arrayList.add(null);
    arrayList.add("C");
    log.info("ArrayList allows nulls: {}", arrayList);
    Map<String, String> hashMap = new HashMap<>();
    hashMap.put("key1", "value1");
    hashMap.put(null, "nullKey");
    hashMap.put("key2", null);
    log.info("HashMap with nulls: {}", hashMap);
    try {
      Set<String> ts = new TreeSet<>();
      ts.add(null);
    } catch (NullPointerException e) {
      log.info("TreeSet rejects nulls");
    }
    Map<String, Integer> scores = new HashMap<>();
    scores.put("Alice", 100);
    int bobScore = scores.getOrDefault("Bob", 0);
    log.info("Default for Bob: {}", bobScore);
  }

  /**
   * Iteration best practices.
   */
  public static void iterationExample() {
    log.info("=== Iteration Best Practices ===");
    List<String> items = new ArrayList<>(List.of("A", "B", "C", "D"));
    try {
      for (String item : items) {
        if (item.equals("B")) items.remove(item);
      }
    } catch (ConcurrentModificationException e) {
      log.info("Foreach removal triggers CME");
    }

    items = new ArrayList<>(List.of("A", "B", "C", "D"));
    Iterator<String> it = items.iterator();
    while (it.hasNext()) {
      if (it.next().equals("B")) it.remove();
    }
    log.info("After iterator removal: {}", items);

    items = new ArrayList<>(List.of("A", "B", "C", "D"));
    items.removeIf("B"::equals);
    log.info("After removeIf: {}", items);

    items = new ArrayList<>(List.of("A", "B", "C", "D"));
    List<String> filtered = items.stream().filter(i -> !i.equals("B")).toList();
    log.info("Stream filtered: {}", filtered);
  }

  /**
   * Performance considerations demo (small, indicative only).
   */
  public static void performanceExample() {
    log.info("=== Performance Considerations ===");
    int size = 10_000;
    List<Integer> arrayList = new ArrayList<>();
    for (int i = 0; i < size; i++) arrayList.add(i);
    long start = System.nanoTime();
    long sum = 0;
    for (int i = 0; i < size; i++) sum += arrayList.get(i);
    long arrayNs = System.nanoTime() - start;

    List<Integer> linkedList = new LinkedList<>();
    for (int i = 0; i < size; i++) linkedList.add(i);
    start = System.nanoTime();
    sum = 0;
    for (int i = 0; i < size; i++) sum += linkedList.get(i);
    long linkedNs = System.nanoTime() - start;
    log.info("Random access ArrayList={}ns LinkedList={}ns", arrayNs, linkedNs);

    Set<Integer> hashSet = new HashSet<>();
    for (int i = 0; i < size; i++) hashSet.add(i);
    start = System.nanoTime();
    for (int i = 0; i < size; i++) hashSet.contains(i);
    long hashNs = System.nanoTime() - start;
    Set<Integer> treeSet = new TreeSet<>();
    for (int i = 0; i < size; i++) treeSet.add(i);
    start = System.nanoTime();
    for (int i = 0; i < size; i++) treeSet.contains(i);
    long treeNs = System.nanoTime() - start;
    log.debug("Benchmark timings ns arrayList={} linkedList={} hashSet={} treeSet={}", arrayNs, linkedNs, hashNs, treeNs);
    log.info("Contains HashSet={}ns TreeSet={}ns", hashNs, treeNs);
  }

  /**
   * Common pitfalls.
   */
  public static void commonPitfallsExample() {
    log.info("=== Common Pitfalls Example ===");
    List<Integer> list1 = Arrays.asList(1, 2, 3);
    List<Integer> list2 = Arrays.asList(1, 2, 3);
    log.info("list1 == list2: {}", list1 == list2);
    log.info("list1.equals(list2): {}", list1.equals(list2));

    class BadPerson {
      final String name;

      BadPerson(String n) {
        name = n;
      }
    }
    Set<BadPerson> badSet = new HashSet<>();
    badSet.add(new BadPerson("Alice"));
    badSet.add(new BadPerson("Alice"));
    log.info("BadPerson set size (expected 1 but got {}): {}", badSet.size(), badSet.size());

    class MutableKey {
      int value;

      MutableKey(int v) {
        value = v;
      }

      @Override
      public int hashCode() {
        return value;
      }

      @Override
      public boolean equals(Object o) {
        return o instanceof MutableKey mk && mk.value == value;
      }
    }
    Map<MutableKey, String> map = new HashMap<>();
    MutableKey key = new MutableKey(1);
    map.put(key, "Value");
    log.info("Contains before mutation: {}", map.containsKey(key));
    key.value = 2;
    log.info("Contains after mutation: {}", map.containsKey(key));

    List<String> fixed = Arrays.asList("A", "B", "C");
    try {
      fixed.add("D");
    } catch (UnsupportedOperationException e) {
      log.info("Cannot change size of Arrays.asList() list");
    }
  }

  /**
   * Runner for collection best practices examples.
   *
   * @param args ignored
   */
  public static void main(String[] args) {
    defensiveCopyingExample();
    choosingCollectionTypeExample();
    immutabilityExample();
    initializationExample();
    nullHandlingExample();
    iterationExample();
    performanceExample();
    commonPitfallsExample();
  }
}
