package fr.univtln.bruno.samples.java101.tp4.streams;

import fr.univtln.bruno.samples.java101.tp4.Person;
import fr.univtln.bruno.samples.java101.tp4.Product;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Small demos of common Collectors: groupingBy, partitioningBy, joining, toMap, summarizing
 */
@Slf4j
public class CollectorsExamples {

  private CollectorsExamples() {
    // utility
  }

  public static void collectorsDemo() {
    log.info("=== Collectors Examples ===");

    List<Product> products = List.of(
        new Product("p1", "Pen", "stationery", BigDecimal.valueOf(1.20)),
        new Product("p2", "Pencil", "stationery", BigDecimal.valueOf(0.80)),
        new Product("p3", "Notebook", "stationery", BigDecimal.valueOf(3.50)),
        new Product("p4", "Chair", "furniture", BigDecimal.valueOf(45.00)),
        new Product("p5", "Table", "furniture", BigDecimal.valueOf(120.00))
    );

    // groupingBy category -> List<Product>
    Map<String, List<Product>> byCategory = products.stream()
        .collect(Collectors.groupingBy(Product::category));
    log.info("Grouped by category: {}", byCategory.keySet());

    // toMap: id -> product
    Map<String, Product> byId = products.stream()
        .collect(Collectors.toMap(Product::id, p -> p));
    log.info("Products by id: {}", byId.keySet());

    // joining product names
    String joinedNames = products.stream()
        .map(Product::name)
        .collect(Collectors.joining(", "));
    log.info("Joined names: {}", joinedNames);

    // summarizing prices
    DoubleSummaryStatistics stats = products.stream()
        .collect(Collectors.summarizingDouble(p -> p.price().doubleValue()));
    log.info("Price stats: count={}, sum={}, min={}, avg={}, max={}",
        stats.getCount(), stats.getSum(), stats.getMin(), stats.getAverage(), stats.getMax());

    // partitioningBy: adults vs minors
    List<Person> people = List.of(
        new Person("Alice", 25),
        new Person("Bob", 30),
        new Person("Charlie", 17),
        new Person("Dana", 16)
    );

    Map<Boolean, List<Person>> partitioned = people.stream()
        .collect(Collectors.partitioningBy(Person::isAdult));
    log.info("Adults: {} minors: {}", partitioned.get(true).size(), partitioned.get(false).size());
  }
}

