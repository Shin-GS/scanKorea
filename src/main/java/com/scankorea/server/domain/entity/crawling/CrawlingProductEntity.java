package com.scankorea.server.domain.entity.crawling;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(
        name = "CRAWLING_PRODUCT",
        uniqueConstraints = {
                @UniqueConstraint(name = "UK_PRODUCT_INFO_GTIN_NAME", columnNames = {"GTIN", "PRODUCT_NAME"})
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CrawlingProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CRAWLING_PRODUCT_ID")
    private Long id; // 기본 키

    @NotBlank
    @Size(max = 32)
    @Column(name = "GTIN", nullable = false, length = 32)
    private String gtin; // 유통표준코드(바코드)

    @NotBlank
    @Size(max = 256)
    @Column(name = "PRODUCT_NAME", nullable = false, length = 256)
    private String productName; // 제품명

    @Size(max = 64)
    @Column(name = "TARGET_CATEGORY", length = 64)
    private String targetCategory; // 대상구분

    @Size(max = 128)
    @Column(name = "PRODUCT_CLASS", length = 128)
    private String productClass; // 분류명

    @Size(max = 256)
    @Column(name = "COMPANY_NAME", length = 256)
    private String companyName; // 사업자명

    @Size(max = 128)
    @Column(name = "BRAND_NAME", length = 128)
    private String brandName; // 브랜드명

    @Lob
    @Column(name = "RAW_JSON", nullable = false, columnDefinition = "TEXT")
    private String rawJson; // 전체 JSON 데이터 (수집 원문)

    public static CrawlingProductEntity of(String gtin,
                                           String productName,
                                           String targetCategory,
                                           String productClass,
                                           String companyName,
                                           String brandName,
                                           String rawJson) {
        CrawlingProductEntity product = new CrawlingProductEntity();
        product.gtin = gtin;
        product.productName = productName;
        product.targetCategory = targetCategory;
        product.productClass = productClass;
        product.companyName = companyName;
        product.brandName = brandName;
        product.rawJson = rawJson;
        return product;
    }
}
