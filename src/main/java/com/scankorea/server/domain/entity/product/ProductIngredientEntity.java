package com.scankorea.server.domain.entity.product;

import com.scankorea.server.domain.entity.BaseTimeEntity;
import com.scankorea.server.domain.entity.ingredient.IngredientEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "PRODUCT_INGREDIENT",
        uniqueConstraints = @UniqueConstraint(name = "UK_PRODUCT_ING", columnNames = {"PRODUCT_ID", "INGREDIENT_ID"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductIngredientEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_INGREDIENT_ID")
    private Long id; // 매핑 기본 키

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "PRODUCT_ID", nullable = false)
    private ProductEntity product; // 참조 제품

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "INGREDIENT_ID", nullable = false)
    private IngredientEntity ingredient; // 참조 성분

    @Column(name = "ORDER_INDEX")
    private Integer orderIndex; // 성분 순서
}
