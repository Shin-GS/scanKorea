package com.scankorea.server.domain.repository.product;

import com.scankorea.server.common.constant.LanguageCode;
import com.scankorea.server.domain.entity.product.ProductLocaleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductLocaleRepository extends JpaRepository<ProductLocaleEntity, Long> {
    Optional<ProductLocaleEntity> findFirstByProductIdAndLangAndReviewedTrue(Long productId, LanguageCode lang);
}
