package fr.univtln.bruno.samples.java101.tp3.eclipse;

import org.eclipse.collections.impl.list.mutable.primitive.IntArrayList;
import org.eclipse.collections.api.list.primitive.IntList;
import lombok.extern.slf4j.Slf4j;

/**
 * Minimal example showing usage of Eclipse Collections primitives (IntList).
 */
@Slf4j
public class EclipseCollectionsExamples {
    public static void main(String[] args) {
        IntList list = IntArrayList.newListWith(1,2,3,4,5);
        long sum = list.sum();
        log.info("sum={}", sum);
    }
}
