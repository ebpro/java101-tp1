package fr.univtln.bruno.samples.java101.tp3.functional;

import fr.univtln.bruno.samples.java101.tp3.Person;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Examples demonstrating groupingBy / partitioningBy and how to display grouped results.
 */
@Slf4j
public class GroupingDisplayExamples {
  /**
   * Private constructor to prevent instantiation.
   */
  private GroupingDisplayExamples() {
  }

  /**
   * Display grouping by last name.
   *
   * @param people list of persons to group
   */
  public static void showGroupingByLastName(List<Person> people) {
    Map<String, List<Person>> byLast = people.stream().collect(Collectors.groupingBy(Person::lastName));
    byLast.forEach((ln, ps) -> log.info("[functional] {} -> {}", ln, ps.stream().map(Person::getFullName).toList()));
  }

  /**
   * Display partitioning of persons by age threshold.
   *
   * @param people    list of persons to partition
   * @param threshold inclusive age threshold for the partition
   */
  public static void showPartitionByAge(List<Person> people, int threshold) {
    var part = people.stream().collect(Collectors.partitioningBy(p -> p.age() >= threshold));
    log.info("[functional] >={} {} <{} {}", threshold, part.get(true).stream().map(Person::getFullName).toList(), threshold, part.get(false).stream().map(Person::getFullName).toList());
  }
}
