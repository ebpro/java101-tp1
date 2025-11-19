package fr.univtln.bruno.samples.java101.tp3;

import fr.univtln.bruno.samples.java101.tp3.list.ListExamples;
import fr.univtln.bruno.samples.java101.tp3.set.SetExamples;
import fr.univtln.bruno.samples.java101.tp3.map.MapExamples;
import fr.univtln.bruno.samples.java101.tp3.queue.QueueExamples;
import fr.univtln.bruno.samples.java101.tp3.comparable.ComparatorExamples;
import fr.univtln.bruno.samples.java101.tp3.functionnal.StreamBasicsExamples;
import fr.univtln.bruno.samples.java101.tp3.functionnal.CollectorsExamples;
import fr.univtln.bruno.samples.java101.tp3.bestpractices.CollectionBestPractices;

/**
 * Small demo runner that executes representative examples from TP3 (Collections & Streams).
 *
 * <p>This class prints labeled sections and invokes selected example methods from the
 * subpackages so students can run a compact demonstration of the module.</p>
 */
public class Demo {
    public static void main(String[] args) {
        System.out.println("==== TP3 Demo (Collections & Streams) ====");

        // Each block invokes a representative subset to keep output readable.
        System.out.println("\n-- ListExamples --");
        ListExamples.arrayListExample();
        ListExamples.sortingExample();

        System.out.println("\n-- SetExamples --");
        SetExamples.hashSetExample();
        SetExamples.setOperationsExample();

        System.out.println("\n-- MapExamples --");
        MapExamples.hashMapExample();
        MapExamples.modernMapMethodsExample();

        System.out.println("\n-- QueueExamples --");
        QueueExamples.priorityQueueExample();
        QueueExamples.taskSchedulingExample();

        System.out.println("\n-- ComparatorExamples --");
        ComparatorExamples.customComparatorExample();
        ComparatorExamples.comparatorChainingExample();

        System.out.println("\n-- StreamBasicsExamples --");
        StreamBasicsExamples.streamCreationExample();
        StreamBasicsExamples.distinctSortedLimitExample();

        System.out.println("\n-- StreamCollectorsExamples (CollectorsExamples) --");
        CollectorsExamples.groupingAndCountingExample();
        CollectorsExamples.terminalOperationsExample();

        System.out.println("\n-- CollectionBestPractices --");
        CollectionBestPractices.defensiveCopyingExample();
        CollectionBestPractices.commonPitfallsExample();

        System.out.println("\n==== End of TP3 demonstration ====");
    }
}
