package com.scankorea.server.controller;

import com.scankorea.server.common.constant.LanguageCode;
import com.scankorea.server.service.product.ProductService;
import com.scankorea.server.service.product.response.ProductViewResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LookupApi {
    private final ProductService productService;

    @GetMapping("/lookup")
    public ResponseEntity<?> byGtin(@RequestParam String gtin,
                                    @RequestParam(name = "lang") LanguageCode lang) {
        ProductViewResponse productView = productService.findView(gtin, lang);
        if (productView == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "NOT_FOUND"));
        }

        return ResponseEntity.ok(Map.of(
                "gtin", productView.gtin(),
                "brandName", productView.brandName(),
                "verified", productView.verified(),
                "originCountry", productView.originCountry(),
                "image", productView.imageUrl()
        ));
    }
}
