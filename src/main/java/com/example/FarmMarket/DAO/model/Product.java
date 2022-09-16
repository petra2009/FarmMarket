package com.example.FarmMarket.DAO.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class Product extends BaseEntity {

    @Column(name="title")
    private String title;

    @Column(name="price")
    private BigDecimal price;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "products_categories",
                joinColumns = @JoinColumn(name="product_id"),
                inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories;

}
