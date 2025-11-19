package fr.univtln.bruno.samples.java101.tp3;

import lombok.Value;
import lombok.With;
import java.time.LocalDate;

/**
 * Immutable value object representing a book used in TP3 sorting and grouping examples.
 *
 * <p>Provides a static factory {@link #of(...)} which validates inputs. Natural ordering
 * is by title.</p>
 */
@Value
public class Book implements Comparable<Book> {
    @With String isbn;
    @With String title;
    @With String author;
    @With LocalDate publishedDate;
    @With double price;

    /**
     * Create a validated Book instance.
     *
     * @param isbn non-null, non-blank ISBN
     * @param title non-null, non-blank title
     * @param author non-null, non-blank author
     * @param publishedDate non-null publication date
     * @param price non-negative price
     * @return a new Book instance
     */
    public static Book of(String isbn, String title, String author, LocalDate publishedDate, double price) {
        if (isbn == null || isbn.isBlank()) throw new IllegalArgumentException("ISBN cannot be null or blank");
        if (title == null || title.isBlank()) throw new IllegalArgumentException("Title cannot be null or blank");
        if (author == null || author.isBlank()) throw new IllegalArgumentException("Author cannot be null or blank");
        if (publishedDate == null) throw new IllegalArgumentException("Published date cannot be null");
        if (price < 0) throw new IllegalArgumentException("Price cannot be negative");
        return new Book(isbn, title, author, publishedDate, price);
    }

    /**
     * Natural ordering by book title.
     */
    @Override
    public int compareTo(Book other) { return title.compareTo(other.title); }

    /**
     * Return a short description of the book (title, author and price).
     *
     * @return human readable description
     */
    public String getDescription() { return String.format("%s by %s (%.2f€)", title, author, price); }
}
