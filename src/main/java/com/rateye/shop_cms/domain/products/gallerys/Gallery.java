package com.rateye.shop_cms.domain.products.gallerys;

import com.rateye.shop_cms.domain.products.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Slf4j
@Getter
@Entity
@NoArgsConstructor
@Table(name="gallerys")
public class Gallery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String thumbnail;

    @Column
    private String original;

    @ManyToOne
    @JoinColumn(name = "productId", referencedColumnName = "id")
    private Product product;

    public Gallery(String thumbnail, String original, Product product) {
        this.thumbnail = thumbnail;
        this.original = original;
        this.product = product;
    }
}
