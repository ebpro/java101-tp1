package fr.univtln.bruno.samples.java101.tp3;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class PersonTest {

  @Test
  void of_validatesArguments() {
    // null -> NullPointerException from Objects.requireNonNull
    assertThatThrownBy(() -> Person.of(null, "X", 10)).isInstanceOf(NullPointerException.class);
    assertThatThrownBy(() -> Person.of("A", null, 10)).isInstanceOf(NullPointerException.class);
    // blank -> IllegalArgumentException
    assertThatThrownBy(() -> Person.of("  ", "B", 10)).isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> Person.of("A", "\t", 10)).isInstanceOf(IllegalArgumentException.class);
    // negative age -> IllegalArgumentException
    assertThatThrownBy(() -> Person.of("A", "B", -1)).isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  void compareTo_ordersByLastThenFirstThenAge() {
    Person a = Person.of("Alice", "Smith", 30);
    Person b = Person.of("Bob", "Smith", 25);
    Person c = Person.of("Alice", "Taylor", 20);

    // Smith < Taylor
    assertThat(a.compareTo(c)).isNegative();
    // Alice < Bob when lastName equal
    assertThat(a.compareTo(b)).isNegative();
  }

  @Test
  void fullName_returnsConcatenation() {
    assertThat(Person.of("A", "B", 1).getFullName()).isEqualTo("A B");
  }
}

