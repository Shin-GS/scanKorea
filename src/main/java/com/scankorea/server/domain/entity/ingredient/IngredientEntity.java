package com.scankorea.server.domain.entity.ingredient;

import com.scankorea.server.domain.entity.BaseTimeEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "INGREDIENT",
        indexes = @Index(name = "IDX_INGREDIENT_INCI", columnList = "INCI_NAME", unique = true))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IngredientEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "INGREDIENT_ID")
    private Long id; // 성분 기본 키

    @NotBlank
    @Size(max = 256)
    @Column(name = "INCI_NAME", nullable = false, unique = true, length = 256)
    private String inciName; // INCI 성분명

    @Size(max = 512)
    @Column(name = "FUNCTION_TAGS", length = 512)
    private String functionTags; // 기능 태그

    @Size(max = 512)
    @Column(name = "ALLERGEN_TAGS", length = 512)
    private String allergenTags; // 알러지 태그
}
