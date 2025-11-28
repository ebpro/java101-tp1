package fr.univtln.bruno.samples.java101.tp4.misc;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Compact Optional examples and integration with streams.
 */
@Slf4j
public class OptionalExamples {
  private OptionalExamples() {}

  public static void optionalDemo() {
    log.info("=== Optional Examples ===");

    Optional<String> maybeName = Optional.ofNullable(System.getProperty("user.name"));

    String name = maybeName.orElse("guest");
    log.info("Name: {}", name);

    maybeName.ifPresentOrElse(s -> log.info("Hello {}", s), () -> log.info("No user"));

    Optional<Integer> parsed = Optional.of("42").map(Integer::parseInt);
    log.info("Parsed: {}", parsed);

    // Bridge Optional to Stream
    List<String> list = List.of(null, "a", null, "b");
    List<String> nonNull = list.stream()
        .flatMap(s -> Optional.ofNullable(s).stream())
        .collect(Collectors.toList());
    log.info("Non-null: {}", nonNull);
  }
}

