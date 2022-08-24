package com.rateye.shop_cms.domain.products;

import com.google.common.collect.Lists;
import com.rateye.shop_cms.domain.products.gallerys.Gallery;
import com.rateye.shop_cms.domain.products.images.Image;
import com.rateye.shop_cms.domain.products.tags.Tag;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.List;

@Slf4j
@Getter
@Entity
@NoArgsConstructor
@Table(name="products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String slug;

    @Column
    private String description;

    @Column
    private int quantity;

    @Column
    private float price;

    @Column
    private float salePrice;

    @Column
    private String unit;

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "product", cascade = CascadeType.ALL)
    private Image image;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.PERSIST)
    private List<Gallery> galleryList = Lists.newArrayList();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.PERSIST)
    private List<Tag> tagList = Lists.newArrayList();

    @Builder
    public Product(String name, String slug, String description, int quantity, float price, float salePrice, String unit, Image image, List<Gallery> galleryList, List<Tag> tagList) {
        this.name = name;
        this.slug = slug;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.salePrice = salePrice;
        this.unit = unit;
        this.image = image;
        this.galleryList = galleryList;
        this.tagList = tagList;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
