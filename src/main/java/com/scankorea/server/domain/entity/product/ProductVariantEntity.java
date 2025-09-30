package com.scankorea.server.domain.entity.product;

import com.scankorea.server.common.constant.VariantType;
import com.scankorea.server.domain.entity.BaseTimeEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "PRODUCT_VARIANT")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductVariantEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VARIANT_ID")
    private Long id; // 변형 기본 키

    @Enumerated(EnumType.STRING)
    @Column(name = "VARIANT_TYPE", length = 32)
    private VariantType variantType; // 변형 유형 (색상/용량 등)

    @Size(max = 64)
    @Column(name = "SHADE_CODE", length = 64)
    private String shadeCode; // 색상 코드

    @Size(max = 64)
    @Column(name = "SHADE_NAME", length = 64)
    private String shadeName; // 색상명

    @Size(max = 12)
    @Column(name = "COLOR_HEX", length = 12)
    private String colorHex; // HEX 색상

    @Column(name = "SIZE_ML")
    private Integer sizeMl; // 용량

    @Size(max = 32)
    @Column(name = "LABEL_COUNTRY", length = 32)
    private String labelCountry; // 국가 라벨

    @Size(max = 512)
    @Column(name = "IMAGE_URL", length = 512)
    private String imageUrl; // 변형 이미지

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "PRODUCT_ID", nullable = false)
    private ProductEntity product; // 참조 제품
}
