package com.scankorea.server.domain.entity.product;

import com.scankorea.server.common.constant.LanguageCode;
import com.scankorea.server.domain.entity.BaseTimeEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "PRODUCT_VARIANT_LOCALE",
        uniqueConstraints = @UniqueConstraint(name = "UK_VARIANT_LOCALE_LANG", columnNames = {"VARIANT_ID", "LANG"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductVariantLocaleEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VARIANT_LOCALE_ID")
    private Long id; // 변형 다국어 기본 키

    @Enumerated(EnumType.STRING)
    @Column(name = "LANG", nullable = false, length = 16)
    private LanguageCode lang; // 언어 코드 (Enum)

    @NotBlank
    @Size(max = 128)
    @Column(name = "DISPLAY_NAME", nullable = false, length = 128)
    private String displayName; // 변형 표시명 (다국어)

    @Size(max = 64)
    @Column(name = "SHADE_NAME", length = 64)
    private String shadeName; // 색상명 (다국어)

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "VARIANT_ID", nullable = false)
    private ProductVariantEntity variant; // 참조 변형

    @Column(name = "IS_REVIEWED", nullable = false)
    private boolean reviewed; // 검수 여부
}
