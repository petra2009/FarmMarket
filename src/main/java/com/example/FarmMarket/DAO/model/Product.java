package com.example.FarmMarket.DAO.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class Product extends BaseEntity {

    @Column(name="product")
    private String product;

    @Column(name="price")
    private BigDecimal price;

    //    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "products_categories",
//                joinColumns = @JoinColumn(name="product_id"),
//                inverseJoinColumns = @JoinColumn(name = "category_id"))
//    private List<Category> categories;

}
