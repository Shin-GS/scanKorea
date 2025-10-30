package com.scankorea.server.controller.api;

import com.scankorea.server.common.code.SuccessCode;
import com.scankorea.server.common.constant.LanguageCode;
import com.scankorea.server.common.response.CustomResponseBuilder;
import com.scankorea.server.common.response.Response;
import com.scankorea.server.service.product.ProductService;
import com.scankorea.server.service.product.response.ProductViewResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ProductApi {
    private final CustomResponseBuilder responseBuilder;
    private final ProductService productService;

    @GetMapping("/products/{gtin}")
    public Response<ProductViewResponse> productDetail(@PathVariable(name = "gtin") String gtin,
                                                       @RequestParam(name = "lang") LanguageCode lang) {
        ProductViewResponse productView = productService.findView(gtin, lang);
        return responseBuilder.of(SuccessCode.SUC, productView);
    }
}
