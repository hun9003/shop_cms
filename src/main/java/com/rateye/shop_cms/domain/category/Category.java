package com.rateye.shop_cms.domain.category;

import com.rateye.shop_cms.common.exception.InvalidParamException;
import com.rateye.shop_cms.domain.AbstractEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "categorys")
public class Category extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "orders")
    private int order;

    @ManyToOne
    @JoinColumn(name = "parent", referencedColumnName = "id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    @OrderBy("order ASC")
    private List<Category> categoryList = new ArrayList<>();

    @Builder
    public Category(Long id, String name, Category parent, int order) {
        // 카테고리 이름 필수 입력 사항
        if(StringUtils.isBlank(name)) throw new InvalidParamException("Category.name");
        this.id = id;
        this.name = name;
        this.parent = parent;
        this.order = order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

}