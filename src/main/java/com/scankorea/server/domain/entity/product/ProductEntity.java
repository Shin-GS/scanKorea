package com.scankorea.server.domain.entity.product;

import com.scankorea.server.common.constant.CategoryCode;
import com.scankorea.server.domain.entity.BaseTimeEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "PRODUCT")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_ID")
    private Long id; // 제품 기본 키

    @NotBlank
    @Size(max = 32)
    @Column(name = "GTIN", nullable = false, unique = true, length = 32)
    private String gtin; // 국제 바코드 번호

    @Size(max = 128)
    @Column(name = "BRAND_NAME", length = 128)
    private String brandName; // 브랜드명

    @Size(max = 64)
    @Column(name = "LINE_NAME", length = 64)
    private String lineName; // 제품 라인명

    @Enumerated(EnumType.STRING)
    @Column(name = "CATEGORY_CODE", length = 64)
    private CategoryCode categoryCode; // 제품 카테고리

    @Size(max = 512)
    @Column(name = "DEFAULT_IMAGE_URL", length = 512)
    private String defaultImageUrl; // 기본 이미지

    @Column(name = "IS_VERIFIED", nullable = false)
    private boolean verified; // 검증 여부

    @Size(max = 32)
    @Column(name = "ORIGIN_COUNTRY", length = 32)
    private String originCountry; // 원산지

    @Column(name = "LAST_SOURCED_AT")
    private LocalDateTime lastSourcedAt; // 마지막 수집 시각
}
