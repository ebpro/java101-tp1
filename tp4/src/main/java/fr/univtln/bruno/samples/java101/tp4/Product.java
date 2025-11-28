package fr.univtln.bruno.samples.java101.tp4;

import java.math.BigDecimal;

/**
 * Immutable Product record for functional programming examples.
 *
 * @param id       the product identifier
 * @param name     the product name
 * @param category the product category
 * @param price    the product price
 */
public record Product(String id, String name, String category, BigDecimal price) {

  /**
   * Compact constructor with validation.
   */
  public Product {
    if (id == null || id.isBlank()) {
      throw new IllegalArgumentException("Product ID must not be null or blank");
    }
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException("Product name must not be null or blank");
    }
    if (category == null || category.isBlank()) {
      throw new IllegalArgumentException("Category must not be null or blank");
    }
    if (price == null || price.compareTo(BigDecimal.ZERO) < 0) {
      throw new IllegalArgumentException("Price must not be null or negative");
    }
  }

  /**
   * Returns a new Product with a discounted price.
   *
   * @param discountPercent the discount percentage (0-100)
   * @return a new Product with discounted price
   */
  public Product withDiscount(double discountPercent) {
    if (discountPercent < 0 || discountPercent > 100) {
      throw new IllegalArgumentException("Discount must be between 0 and 100");
    }
    var multiplier = BigDecimal.valueOf(1 - discountPercent / 100.0);
    var newPrice = price.multiply(multiplier);
    return new Product(id, name, category, newPrice);
  }

  /**
   * Checks if the product is expensive (price >= 100).
   *
   * @return true if expensive, false otherwise
   */
  public boolean isExpensive() {
    return price.compareTo(BigDecimal.valueOf(100)) >= 0;
  }
}

