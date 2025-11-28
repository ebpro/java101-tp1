package fr.univtln.bruno.samples.java101.tp5junit6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Example of a simple integration test (naming *IT so failsafe can run it in the verify phase).
 * It demonstrates:
 * - realistic setup that may touch filesystem/network
 * - using @Tag("integration") to allow selective runs
 */
@Tag("integration")
@DisplayName("TP5 Integration example (IT)")
public class IntegrationExampleIT {

  @Test
  void integrationWritesFile(@org.junit.jupiter.api.io.TempDir Path tempDir) throws Exception {
    Path p = tempDir.resolve("it-sample.txt");
    Files.writeString(p, "integration");

    assertThat(Files.exists(p)).isTrue();
    assertThat(Files.readString(p)).isEqualTo("integration");
  }
}

