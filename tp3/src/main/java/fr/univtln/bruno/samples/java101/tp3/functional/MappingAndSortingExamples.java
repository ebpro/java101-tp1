package fr.univtln.bruno.samples.java101.tp3.functional;

import fr.univtln.bruno.samples.java101.tp3.Book;
import fr.univtln.bruno.samples.java101.tp3.Person;
import lombok.extern.slf4j.Slf4j;

import java.util.Comparator;
import java.util.List;

/**
 * Combined mapping and sorting examples for stream pipelines applied to collections.
 */
@Slf4j
public class MappingAndSortingExamples {
  /** Print full names from a list of persons using stream map. */
  /**
   * Private constructor to prevent instantiation.
   */
  private MappingAndSortingExamples() {
  }

  /** Print names together with ages using stream mapping. */

  /**
   * Print full names from the provided list of people.
   *
   * @param people source list of Person
   */
  public static void printFullNames(List<Person> people) {
    log.debug("printFullNames input: {}", people);
    log.info("[functional] Full names: {}", people.stream().map(Person::getFullName).toList());
  }

  /** Print book descriptions from a list of books. */

  /**
   * Print annotated names (name(age)) for each person.
   *
   * @param people source list of Person
   */
  public static void printNamesWithAges(List<Person> people) {
    log.debug("printNamesWithAges input: {}", people);
    log.info("[functional] Names with ages: {}", people.stream().map(p -> p.getFullName() + "(" + p.age() + ")").toList());
  }

  /** Show min/max by age using streams. */

  /**
   * Print book descriptions from the given list.
   *
   * @param books source list of Book
   */
  public static void printBookDescriptions(List<Book> books) {
    log.debug("printBookDescriptions input: {}", books);
    log.info("[functional] Books: {}", books.stream().map(Book::getDescription).toList());
  }

  /** Show book sort examples using stream sorted with comparators. */

  /**
   * Show the youngest and oldest person by age.
   *
   * @param people source list of Person
   */
  public static void showMinMaxByAge(List<Person> people) {
    people.stream().min(Comparator.comparing(Person::age)).ifPresent(p -> log.info("[functional] Youngest {}", p.getFullName()));
    people.stream().max(Comparator.comparing(Person::age)).ifPresent(p -> log.info("[functional] Oldest {}", p.getFullName()));
  }

  /**
   * Demonstrate different sorting strategies for books.
   *
   * @param books source list of Book
   */
  public static void showBookSortExamples(List<Book> books) {
    log.debug("showBookSortExamples input: {}", books);
    List<Book> byPrice = books.stream().sorted(Comparator.comparing(Book::price)).toList();
    List<Book> byDateDesc = books.stream().sorted(Comparator.comparing(Book::publishedDate).reversed()).toList();
    List<Book> byAuthorPrice = books.stream().sorted(Comparator.comparing(Book::author).thenComparing(Book::price)).toList();
    log.debug("ByPrice: {} ByDateDesc: {} ByAuthorPrice: {}", byPrice, byDateDesc, byAuthorPrice);
    log.info("[functional] ByPrice {}", byPrice.stream().map(Book::getDescription).toList());
    log.info("[functional] ByDateDesc {}", byDateDesc.stream().map(Book::getDescription).toList());
    log.info("[functional] ByAuthor+Price {}", byAuthorPrice.stream().map(Book::getDescription).toList());
  }
}
