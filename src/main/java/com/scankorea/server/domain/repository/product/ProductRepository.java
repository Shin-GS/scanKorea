package com.scankorea.server.domain.repository.product;

import com.scankorea.server.domain.entity.product.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    Optional<ProductEntity> findByGtin(String gtin);

    @Query("select p from ProductEntity p where p.gtin in :gtins")
    List<ProductEntity> findAllByGtins(@Param("gtins") Collection<String> gtins);
}
