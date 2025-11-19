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
    public static void printFullNames(List<Person> people) {
        log.info("[functional] Full names: {}", people.stream().map(Person::getFullName).toList());
    }

    /** Print names together with ages using stream mapping. */
    public static void printNamesWithAges(List<Person> people) {
        log.info("[functional] Names with ages: {}", people.stream().map(p -> p.getFullName() + "(" + p.getAge() + ")").toList());
    }

    /** Print book descriptions from a list of books. */
    public static void printBookDescriptions(List<Book> books) {
        log.info("[functional] Books: {}", books.stream().map(Book::getDescription).toList());
    }

    /** Show min/max by age using streams. */
    public static void showMinMaxByAge(List<Person> people) {
        people.stream().min(Comparator.comparing(Person::getAge)).ifPresent(p -> log.info("[functional] Youngest {}", p.getFullName()));
        people.stream().max(Comparator.comparing(Person::getAge)).ifPresent(p -> log.info("[functional] Oldest {}", p.getFullName()));
    }

    /** Show book sort examples using stream sorted with comparators. */
    public static void showBookSortExamples(List<Book> books) {
        List<Book> byPrice = books.stream().sorted(Comparator.comparing(Book::getPrice)).toList();
        List<Book> byDateDesc = books.stream().sorted(Comparator.comparing(Book::getPublishedDate).reversed()).toList();
        List<Book> byAuthorPrice = books.stream().sorted(Comparator.comparing(Book::getAuthor).thenComparing(Book::getPrice)).toList();
        log.info("[functional] ByPrice {}", byPrice.stream().map(Book::getDescription).toList());
        log.info("[functional] ByDateDesc {}", byDateDesc.stream().map(Book::getDescription).toList());
        log.info("[functional] ByAuthor+Price {}", byAuthorPrice.stream().map(Book::getDescription).toList());
    }
}
