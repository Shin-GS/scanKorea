package com.scankorea.server.domain.entity.rating;

import com.scankorea.server.common.constant.TargetType;
import com.scankorea.server.domain.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "RATING_SNAPSHOT",
        uniqueConstraints = @UniqueConstraint(name = "UK_RATING_TARGET", columnNames = {"TARGET_TYPE", "TARGET_ID"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RatingSnapshotEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RATING_ID")
    private Long id; // 별점 스냅샷 기본 키

    @Enumerated(EnumType.STRING)
    @Column(name = "TARGET_TYPE", nullable = false, length = 16)
    private TargetType targetType; // 대상 유형 (PRODUCT/VARIANT)

    @Column(name = "TARGET_ID", nullable = false)
    private Long targetId; // 대상 ID

    @Column(name = "AVG_STARS", nullable = false)
    private double avgStars; // 평균 별점

    @Column(name = "REVIEW_COUNT", nullable = false)
    private int reviewCount; // 리뷰 개수

    @Column(name = "STABLE_SCORE")
    private Double stableScore; // 안정화 점수
}
