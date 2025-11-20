package fr.univtln.bruno.samples.java101.tp3;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

class BookTest {

  @Test
  void of_validatesArguments() {
    assertThatThrownBy(() -> Book.of(null, "Title", "Author", LocalDate.now(), 10.0)).isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> Book.of("978", "  ", "Author", LocalDate.now(), 10.0)).isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> Book.of("978", "Title", "", LocalDate.now(), 10.0)).isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> Book.of("978", "Title", "Author", null, 10.0)).isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> Book.of("978", "Title", "Author", LocalDate.now(), -1)).isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  void compareTo_consistent_ordering() {
    Book b1 = Book.of("111", "A", "Auth", LocalDate.of(2020,1,1), 10.0);
    Book b2 = Book.of("111", "A", "Auth", LocalDate.of(2020,1,1), 10.0);
    Book b3 = Book.of("112", "B", "Auth2", LocalDate.of(2019,1,1), 12.0);

    assertThat(b1.compareTo(b2)).isZero();
    assertThat(b1.compareTo(b3)).isNegative();
  }
}

