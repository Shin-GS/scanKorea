package com.scankorea.server.domain.repository.product;

import com.scankorea.server.domain.entity.product.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    Optional<ProductEntity> findByGtin(String gtin);
}
