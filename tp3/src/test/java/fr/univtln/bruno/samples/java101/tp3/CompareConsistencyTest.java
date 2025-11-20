package fr.univtln.bruno.samples.java101.tp3;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class CompareConsistencyTest {

  @Test
  void person_compareTo_consistentWithEquals() {
    Person p1 = Person.of("X", "Y", 10);
    Person p2 = Person.of("X", "Y", 10);
    assertThat(p1.compareTo(p2)).isZero();
    assertThat(p1).isEqualTo(p2);
  }

  @Test
  void book_compareTo_consistentWithEquals() {
    Book b1 = Book.of("111", "A", "Auth", java.time.LocalDate.now(), 1.0);
    Book b2 = Book.of("111", "A", "Auth", java.time.LocalDate.now(), 1.0);
    assertThat(b1.compareTo(b2)).isZero();
    assertThat(b1).isEqualTo(b2);
  }
}

