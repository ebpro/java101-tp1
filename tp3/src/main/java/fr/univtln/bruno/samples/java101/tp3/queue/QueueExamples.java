package fr.univtln.bruno.samples.java101.tp3.queue;

import fr.univtln.bruno.samples.java101.tp3.Person;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * Examples showing different Queue/Deque implementations and usage patterns.
 */
@Slf4j
public class QueueExamples {
    /** Simple FIFO queue demonstration using LinkedList. */
    public static void queueExample() {
        log.info("=== Queue (LinkedList) ===");
        Queue<String> q = new LinkedList<>();
        q.offer("First"); q.offer("Second"); q.offer("Third");
        log.info("Peek: {}", q.peek());
        log.info("Poll: {}", q.poll());
        log.info("After poll: {}", q);
    }
    /** ArrayDeque as a performant double-ended queue implementation. */
    public static void arrayDequeExample() {
        log.info("=== ArrayDeque ===");
        Deque<String> d = new ArrayDeque<>();
        d.addFirst("Middle"); d.addFirst("First"); d.addLast("Last");
        log.info("Deque: {}", d);
        log.info("RemoveFirst: {} RemoveLast: {}", d.removeFirst(), d.removeLast());
    }
    /** Using Deque as a stack replacement (avoid legacy Stack). */
    public static void stackExample() {
        log.info("=== Stack via Deque ===");
        Deque<String> stack = new ArrayDeque<>();
        stack.push("Bottom"); stack.push("Middle"); stack.push("Top");
        log.info("Pop: {}", stack.pop());
    }
    /** PriorityQueue examples: natural and custom ordering. */
    public static void priorityQueueExample() {
        log.info("=== PriorityQueue Natural (min-heap) ===");
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(5); pq.offer(1); pq.offer(3); pq.offer(2);
        while(!pq.isEmpty()) log.info("Poll: {}", pq.poll());
        log.info("=== PriorityQueue Reverse (max-heap) ===");
        PriorityQueue<Integer> max = new PriorityQueue<>(Comparator.reverseOrder());
        max.addAll(List.of(5,1,3,2));
        while(!max.isEmpty()) log.info("Poll: {}", max.poll());
    }
    /** PriorityQueue with complex objects using natural order or comparator. */
    public static void priorityQueueWithObjectsExample() {
        log.info("=== PriorityQueue<Person> by natural order ===");
        PriorityQueue<Person> byName = new PriorityQueue<>();
        byName.offer(Person.of("Charlie","Brown",35));
        byName.offer(Person.of("Alice","Smith",30));
        byName.offer(Person.of("Bob","Jones",25));
        while(!byName.isEmpty()) log.info("Person: {}", byName.poll().getFullName());
        log.info("=== PriorityQueue<Person> by age ===");
        PriorityQueue<Person> byAge = new PriorityQueue<>(Comparator.comparing(Person::getAge));
        byAge.addAll(List.of(Person.of("Charlie","Brown",35),Person.of("Alice","Smith",30),Person.of("Bob","Jones",25)));
        while(!byAge.isEmpty()) { Person p = byAge.poll(); log.info("{} age {}", p.getFullName(), p.getAge()); }
    }
    /** Simple task scheduling example using a priority queue. */
    public static void taskSchedulingExample() {
        log.info("=== Task Scheduling ===");
        record Task(String name,int priority) implements Comparable<Task>{ public int compareTo(Task o){return Integer.compare(priority,o.priority);} }
        PriorityQueue<Task> tasks = new PriorityQueue<>();
        tasks.offer(new Task("Email",3));
        tasks.offer(new Task("Critical Bug",1));
        tasks.offer(new Task("Docs",5));
        tasks.offer(new Task("Deploy",2));
        while(!tasks.isEmpty()) { Task t = tasks.poll(); log.info("[P{}] {}", t.priority(), t.name()); }
    }
    /** Sliding window example implemented with ArrayDeque. */
    public static void slidingWindowExample() {
        log.info("=== Sliding Window (ArrayDeque) ===");
        Deque<Integer> window = new ArrayDeque<>(); int size=3;
        for (int v: List.of(1,2,3,4,5)) { if (window.size()==size) window.removeFirst(); window.addLast(v); log.info("Window: {}", window); }
    }
    public static void main(String[] args) {
        queueExample();
        arrayDequeExample();
        stackExample();
        priorityQueueExample();
        priorityQueueWithObjectsExample();
        taskSchedulingExample();
        slidingWindowExample();
    }
}
