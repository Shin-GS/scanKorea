package com.scankorea.server.domain.entity.product;

import com.scankorea.server.common.constant.LanguageCode;
import com.scankorea.server.domain.entity.BaseTimeEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "PRODUCT_LOCALE",
        uniqueConstraints = @UniqueConstraint(name = "UK_PRODUCT_LOCALE_LANG", columnNames = {"PRODUCT_ID", "LANG"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductLocaleEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_LOCALE_ID")
    private Long id; // 다국어 기본 키

    @Enumerated(EnumType.STRING)
    @Column(name = "LANG", nullable = false, length = 16)
    private LanguageCode lang; // 언어 코드 (Enum)

    @NotBlank
    @Size(max = 256)
    @Column(name = "PRODUCT_NAME", nullable = false, length = 256)
    private String productName; // 제품명 (다국어)

    @Size(max = 4000)
    @Column(name = "DESCRIPTION_TEXT", length = 4000)
    private String descriptionText; // 제품 설명 (다국어)

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "PRODUCT_ID", nullable = false)
    private ProductEntity product; // 참조 제품

    @Column(name = "IS_REVIEWED", nullable = false)
    private boolean reviewed; // 검수 여부
}
