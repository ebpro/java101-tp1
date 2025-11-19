package fr.univtln.bruno.samples.java101.tp3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Book tests")
class BookTest {
    @Test
    void shouldCreateValidBook() {
        Book b = Book.of("ISBN","Title","Author", LocalDate.of(2020,1,1), 10.5);
        assertThat(b.getIsbn()).isEqualTo("ISBN");
        assertThat(b.getTitle()).isEqualTo("Title");
        assertThat(b.getAuthor()).isEqualTo("Author");
        assertThat(b.getPrice()).isEqualTo(10.5);
    }
    @Test
    void shouldRejectNegativePrice() {
        assertThatThrownBy(() -> Book.of("ISBN","T","A", LocalDate.now(), -1)).isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    void shouldCompareByTitle() {
        Book a = Book.of("1","Alpha","Auth", LocalDate.now(), 1);
        Book b = Book.of("2","Beta","Auth", LocalDate.now(), 1);
        assertThat(a.compareTo(b)).isLessThan(0);
    }
}

