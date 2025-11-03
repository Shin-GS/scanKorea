package com.scankorea.server.service.crawling;

import com.scankorea.server.domain.entity.crawling.CrawlingProductEntity;
import com.scankorea.server.domain.repository.crawling.CrawlingProductEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class CrawlingService {
    private final CrawlingProductEntityRepository crawlingProductEntityRepository;

    @Transactional
    public void saveIfValid(String gtin,
                            String productName,
                            String targetCategory,
                            String productClass,
                            String companyName,
                            String brandName,
                            String rawJson) {
        if (!StringUtils.hasText(gtin) || !StringUtils.hasText(productName)) {
            return;
        }

        if (crawlingProductEntityRepository.existsByGtinAndProductName(gtin, productName)) {
            return;
        }

        crawlingProductEntityRepository.save(CrawlingProductEntity.of(
                gtin,
                productName,
                targetCategory,
                productClass,
                companyName,
                brandName,
                rawJson));
    }
}
