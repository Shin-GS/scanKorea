package com.scankorea.server.service.product;

import com.scankorea.server.common.constant.Constant;
import com.scankorea.server.common.constant.LanguageCode;
import com.scankorea.server.domain.entity.product.ProductEntity;
import com.scankorea.server.domain.entity.product.ProductLocaleEntity;
import com.scankorea.server.domain.repository.product.ProductLocaleRepository;
import com.scankorea.server.domain.repository.product.ProductRepository;
import com.scankorea.server.service.product.response.ProductViewResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductLocaleRepository productLocaleRepository;

    public ProductViewResponse findView(String gtin,
                                        LanguageCode lang) {
        ProductEntity product = productRepository.findByGtin(gtin)
                .orElseThrow(() -> new RuntimeException("entity not found"));
        ProductLocaleEntity productLocale = productLocaleRepository.findFirstByProductIdAndLang(product.getId(), lang)
                .orElse(productLocaleRepository.findFirstByProductIdAndLang(product.getId(), Constant.DEFAULT_LANGUAGE)
                        .orElseThrow(() -> new RuntimeException("entity not found")));
        return ProductViewResponse.of(product, productLocale);
    }
}
