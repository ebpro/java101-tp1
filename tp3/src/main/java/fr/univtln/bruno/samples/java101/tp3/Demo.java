package fr.univtln.bruno.samples.java101.tp3;

import fr.univtln.bruno.samples.java101.tp3.bestpractices.CollectionBestPractices;
import fr.univtln.bruno.samples.java101.tp3.comparable.ComparatorExamples;
import fr.univtln.bruno.samples.java101.tp3.functional.CollectorsExamples;
import fr.univtln.bruno.samples.java101.tp3.functional.StreamBasicsExamples;
import fr.univtln.bruno.samples.java101.tp3.list.ListExamples;
import fr.univtln.bruno.samples.java101.tp3.map.MapExamples;
import fr.univtln.bruno.samples.java101.tp3.queue.QueueExamples;
import fr.univtln.bruno.samples.java101.tp3.set.SetExamples;
import lombok.extern.slf4j.Slf4j;

/**
 * Small demo runner that executes representative examples from TP3 (Collections & Streams).
 *
 * <p>This class logs labeled sections and invokes selected example methods from the
 * subpackages so students can run a compact demonstration of the module.</p>
 */
@Slf4j
public class Demo {
  /**
   * Private constructor to prevent instantiation.
   */
  private Demo() {
  }

  /**
   * Demo runner for TP3 examples (logs output via SLF4J).
   *
   * @param args ignored
   */
  public static void main(String[] args) {
    log.info("==== TP3 Demo (Collections & Streams) ====");

    // Each block invokes a representative subset to keep output readable.
    log.info("-- ListExamples --");
    ListExamples.arrayListExample();
    ListExamples.sortingExample();

    log.info("-- SetExamples --");
    SetExamples.hashSetExample();
    SetExamples.setOperationsExample();

    log.info("-- MapExamples --");
    MapExamples.hashMapExample();
    MapExamples.modernMapMethodsExample();

    log.info("-- QueueExamples --");
    QueueExamples.priorityQueueExample();
    QueueExamples.taskSchedulingExample();

    log.info("-- ComparatorExamples --");
    ComparatorExamples.customComparatorExample();
    ComparatorExamples.comparatorChainingExample();

    log.info("-- StreamBasicsExamples --");
    StreamBasicsExamples.streamCreationExample();
    StreamBasicsExamples.distinctSortedLimitExample();

    log.info("-- StreamCollectorsExamples (CollectorsExamples) --");
    CollectorsExamples.groupingAndCountingExample();
    CollectorsExamples.terminalOperationsExample();

    log.info("-- CollectionBestPractices --");
    CollectionBestPractices.defensiveCopyingExample();
    CollectionBestPractices.commonPitfallsExample();

    log.info("==== End of TP3 demonstration ====");
  }
}
