package com.rateye.shop_cms.domain.products.images;

import com.rateye.shop_cms.domain.products.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Slf4j
@Getter
@Entity
@NoArgsConstructor
@Table(name="images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String thumbnail;

    @Column
    private String original;

    @OneToOne
    @JoinColumn(name = "productId", referencedColumnName = "id")
    private Product product;

    public Image(String thumbnail, String original, Product product) {
        this.thumbnail = thumbnail;
        this.original = original;
        this.product = product;
        product.setImage(this);
    }
}
