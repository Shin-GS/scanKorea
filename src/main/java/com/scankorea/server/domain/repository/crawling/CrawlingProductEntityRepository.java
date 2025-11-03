package com.scankorea.server.domain.repository.crawling;

import com.scankorea.server.domain.entity.crawling.CrawlingProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CrawlingProductEntityRepository extends JpaRepository<CrawlingProductEntity, Long> {
    Optional<CrawlingProductEntity> findByGtinAndProductName(String gtin, String productName);
}
