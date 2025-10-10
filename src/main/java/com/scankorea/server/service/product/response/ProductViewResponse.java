package com.scankorea.server.service.product.response;

import com.scankorea.server.domain.entity.product.ProductEntity;
import com.scankorea.server.domain.entity.product.ProductLocaleEntity;

public record ProductViewResponse(String gtin,
                                  String brandName,
                                  String displayName,
                                  String description,
                                  boolean verified,
                                  String originCountry,
                                  String imageUrl) {
    public static ProductViewResponse of(ProductEntity product,
                                         ProductLocaleEntity productLocale) {
        return new ProductViewResponse(product.getGtin(),
                product.getBrandName(),
                productLocale.getProductName(),
                productLocale.getDescriptionText(),
                product.isVerified(),
                product.getOriginCountry(),
                product.getDefaultImageUrl());
    }
}
