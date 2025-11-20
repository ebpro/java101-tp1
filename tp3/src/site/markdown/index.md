# TP3 — Collections & Streams (student guide)

This module contains concise examples you will use in practical sessions to learn how Java collections and streams work.

What you will learn
- Choose the right collection type (List / Set / Map / Queue / Deque) and why.
- Spot and fix common bugs: nulls, mutable keys, concurrent modification, parallel streams issues.

Quick map of examples
- `list` — ArrayList, LinkedList, subList, unmodifiable views, iteration patterns.
- `set` — HashSet, LinkedHashSet, TreeSet, deduplication and set operations.
- `map` — HashMap, modern map APIs (`computeIfAbsent`, `merge`), iteration patterns.
- `queue` — ArrayDeque, PriorityQueue, FIFO/LIFO usage and task scheduling demos.
- `comparable` — Comparable and Comparator examples, chaining, nullsFirst/nullsLast.
- `bestpractices` — short demonstrations of defensive copying and common pitfalls.
- `guava` — small examples using Google Guava utilities and collections utilities.
- `eclipse` — examples using Eclipse Collections to illustrate alternative collection APIs and primitives support.
- `Person.java`, `Book.java` — small immutable models used in many examples.
- `Demo.java` — runs representative examples; read the code comments for quick pointers.
- `functional` (**read after the functional programming lecture**) — Stream creation, map/filter/flatMap, collectors, parallel streams, `peek` for debugging.

How to run the examples and tests

- Build a shaded (fat) jar for the module and run it directly (preferred for instructors/students who do not want to use the Maven exec plugin):

```bash
# From project root, package the module (includes running tests by default)
./mvnw -pl tp3 -am package

# Run the shaded jar produced in the module's target directory. The project uses the "-withdependencies" suffix
# for the fat artifact (example: tp3-0.0.1-SNAPSHOT-withdependencies.jar). Use a wildcard to match the file:
java -jar tp3/target/*-withdependencies.jar
```

```bash
# Optionally, set the log level (DEBUG, INFO, WARN, ERROR) via system property:
java -DLOG_LEVEL=INFO -jar tp3/target/*-withdependencies.jar
```

Note: if your build is configured to produce a differently named shaded artifact, adjust the path accordingly.

Edge cases you must understand (short explanations & hints)

1) Nulls and Comparators
- Problem: calling methods on `null` causes NPE; sorting lists with `null` elements throws when comparator doesn't handle nulls.
- Hint: use `Comparator.nullsFirst(Comparator.naturalOrder())` or validate inputs (factories that reject nulls).

2) equals() vs compareTo() consistency
- Problem: if `compareTo(a,b) == 0` but `!a.equals(b)`, `TreeSet` and `TreeMap` behave strangely (duplicates or lost entries).
- Hint: design `compareTo` to consider the same fields used by `equals`, or document the difference and avoid mixing them as keys.

3) Mutable keys in Hash-based collections
- Problem: mutating a key's fields that affect `equals`/`hashCode` after insertion breaks lookups: the entry becomes "lost".
- Hint: use immutable objects as keys (records) or avoid mutating key fields.

4) subList is a view (backed by the original list)
- Problem: modifying the subList modifies the original list and structural changes on the original list may invalidate the subList.
- Hint: when you need a detached list, create a copy: `new ArrayList<>(original.subList(a,b))`.

5) ConcurrentModificationException (modifying during iteration)
- Problem: modifying a collection while iterating (for-each) throws `ConcurrentModificationException`.
- Hint: use `Iterator` + `iterator.remove()` or collect elements to remove, or use concurrent collections (e.g., `CopyOnWriteArrayList`) if appropriate.

6) Parallel streams and shared mutable state
- Problem: using shared mutable containers inside parallel streams leads to race conditions and data corruption.
- Hint: use collectors (e.g., `Collectors.toList()`), thread-safe collectors (e.g., `toConcurrentMap`) or avoid side-effects.

7) Performance surprises: LinkedList vs ArrayList
- Problem: `LinkedList` has O(n) random access; `ArrayList` has O(1) indexed access and better cache locality.
- Hint: prefer `ArrayList` unless you specifically need frequent head/tail inserts/removals.

8) Boxing/unboxing and collections of primitives
- Problem: using `List<Integer>` causes boxing overhead; large numeric collections may be slow/memory heavy.
- Hint: consider `IntStream`/primitive streams or third-party primitive collections if performance matters.

9) PriorityQueue and comparator mutability
- Problem: if comparator or elements change after insertion, the queue ordering may become inconsistent.
- Hint: avoid mutating elements used for ordering, or reinsert elements after mutation.

10) Floating point ordering and NaN
- Problem: `Double.NaN` ordering and equality behave oddly with `Double.compare` and `equals`.
- Hint: be explicit about handling NaN if it can appear in your data (filter or map to a sentinel).

11) toArray and generics pitfalls
- Problem: `someList.toArray(new T[0])` idiom is common, but raw `toArray()` returns `Object[]` and needs a cast.
- Hint: prefer `toArray(T[]::new)` (Java 11+) when you want a typed array: `list.toArray(String[]::new)`.

12) Map.computeIfAbsent race conditions (multi-threaded)
- Problem: `computeIfAbsent` can race in concurrent contexts if the mapping function has side-effects.
- Hint: use `ConcurrentHashMap` and be careful with side-effects in mapping functions.

13) Iteration order differences
- Problem: `HashSet` has no stable order, `LinkedHashSet` preserves insertion order, `TreeSet` sorts elements.
- Hint: choose the set implementation that matches required ordering semantics.

14) Stream ordering and short-circuiting
- Problem: operations like `limit()` or `findFirst()` depend on stream encounter order; unordered streams may change results.
- Hint: be explicit about ordering; use `parallel()` with care.

15) Non-transitive or inconsistent Comparator
- Problem: a comparator that is not transitive (A<B and B<C but A>C) or inconsistent with equals can break sorting algorithms and lead to IllegalArgumentException in `Arrays.sort` or unpredictable results in `Collections.sort`.
- Hint: write unit tests that assert comparator transitivity for representative triplets; prefer using Comparator factory methods which tend to produce consistent comparators.

16) Comparator throwing exceptions or returning inconsistent signs
- Problem: comparator code can throw exceptions (NPE, ClassCastException) if it assumes non-null or wrong types.
- Hint: validate inputs or use `Comparator.nullsFirst` and avoid casting inside comparators.

17) Using identity equality vs equals (IdentityHashMap / IdentityHashSet)
- Problem: some examples or students may think identity == equals; choosing the wrong semantics can cause bugs.
- Hint: explain the difference and show when IdentityHashMap is useful (caches keyed by object identity).

18) Weak/soft references and cache semantics (WeakHashMap)
- Problem: using WeakHashMap for caches can lead to entries disappearing when GC runs, surprising students.
- Hint: explain lifetime semantics and that WeakHashMap keys are garbage-collected when no strong refs exist.

19) Spliterator characteristics and parallel stream pitfalls
- Problem: certain collection spliterators report size or characteristics that affect parallel division; using custom spliterators incorrectly can break parallel performance or correctness.
- Hint: rely on standard collections; when implementing custom spliterators, carefully set characteristics and test parallel behavior.

20) Removing while streaming
- Problem: modifying the underlying collection while it is being streamed can cause CME or inconsistent results.
- Hint: avoid side-effects while streaming; collect first then modify, or use safe concurrent collections.

21) Collector characteristics (CONCURRENT, UNORDERED, IDENTITY_FINISH)
- Problem: custom collectors must correctly declare characteristics; otherwise they may be unsafe for parallel execution.
- Hint: prefer built-in collectors or follow the Collector contract when implementing your own.

22) HashCode collisions and performance traps
- Problem: many objects with the same hashCode degrade HashMap/HashSet performance to O(n) in worst-case scenarios.
- Hint: use good hashCode implementations (records help), and for security-sensitive contexts consider defenses.

23) Locale-sensitive comparisons and sorting
- Problem: String ordering may depend on locale (case, accents); `String.compareTo` is not locale-aware.
- Hint: use `Collator` or `Locale`-aware comparators when sorting human-readable text.

24) Serialization compatibility for records
- Problem: serializing records across versions can be brittle if fields change.
- Hint: avoid relying on Java serialization for long-lived persisted data; prefer explicit DTO/versioning.

25) Resource-heavy collectors and memory blow-up
- Problem: collecting very large streams into lists or maps may OOM the JVM.
- Hint: prefer streaming processing, use limits, or external storage when processing large datasets.

Small tips
- Use `List.of` / `Set.of` for small immutable collections in examples.
- When in doubt, write a unit test that expresses the expected behavior.
