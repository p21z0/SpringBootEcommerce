package com.ecommerce.project.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="products")
@ToString
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId;
    @NotBlank
    @Size(min=4, message = "Must have at least 4 char")
    private String productName;
    @NotBlank
    @Size(min= 8, message = "Must be longer")
    private String description;

    @NotNull(message = "Price cannot be null")
    @Positive(message = "Price must be a positive value")
    private Integer quantity;
    @NotNull(message = "Price cannot be null")
    @Positive(message = "Price must be a positive value")
    private double price;

    private double specialPrice;
    private double discount;

    @NotBlank
    private String image;

    @ManyToOne
    @JoinColumn(name="categoryId")
    private Category category;

    @ManyToOne
    @JoinColumn(name="seller_id")
    private User user;
}
