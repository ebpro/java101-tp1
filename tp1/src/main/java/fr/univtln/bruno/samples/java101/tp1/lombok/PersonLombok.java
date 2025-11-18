package fr.univtln.bruno.samples.java101.tp1.lombok;

import lombok.*;

/**
 * Compact Lombok-backed Person example without validation.
 *
 * <p>Behavior and generation:
 * <ul>
 *   <li>{@code @AllArgsConstructor(access = AccessLevel.PRIVATE, staticName = "of")} generates
 *       a private all-arguments constructor and a private static factory method
 *       named {@code of(...)}. Client code can call {@code PersonLombok.of(...)}
 *       to create an instance via that generated factory.</li>
 *   <li>{@code @Builder} generates a fluent builder class ({@code PersonLombokBuilder}) that
 *       can also be used to create instances: {@code PersonLombok.builder().firstName(...).build()}.</li>
 *   <li>{@code @Getter}, {@code @ToString}, {@code @EqualsAndHashCode} generate the usual
 *       accessors and utilities. We do not use {@code @Data} here on purpose; {@code @Data}
 *       would combine several of these annotations (getters/setters/toString/equals/hashCode)
 *       and is useful for quick prototypes, but it hides generated mutability and can be
 *       surprising for beginners.</li>
 *   <li>The field {@code age} is explicitly mutable (annotated with {@code @Setter}) while
 *       {@code firstName} and {@code lastName} are final — this shows a mixed mutability
 *       example where identity fields are immutable but a mutable attribute exists.</li>
 * </ul>
 *
 * <p>Validation: this class intentionally does <em>not</em> perform validation. Prefer
 * {@code PersonLombokSecure} (secure variant) when you need input validation at the
 * factory/builder boundary. This minimal example is useful to teach tooling (Lombok)
 * and to compare with manual POJOs and validated variants.</p>
 */

@AllArgsConstructor(access = AccessLevel.PRIVATE, staticName = "of") // Private constructor with static factory method
@Builder // Generate a builder for this class
@Getter // Generate getters for all fields
@ToString // Generate a toString method
@EqualsAndHashCode // Generate equals and hashCode methods with all fields
@Setter // Generate setters for all fields (simple mutable example)
public class PersonLombok {
    private String firstName;
    private String lastName;
    private int age;

    /**
     * Protected no-arg constructor for frameworks and Javadoc clarity.
     * Lombok will generate other constructors and builder methods; this
     * constructor is intentionally minimal and for documentation only.
     */
    protected PersonLombok() {
        // no-op: present for framework construction and to clarify Javadoc
    }
}
