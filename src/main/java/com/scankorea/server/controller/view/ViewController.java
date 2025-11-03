package com.scankorea.server.controller.view;

import com.scankorea.server.common.annotation.Lang;
import com.scankorea.server.common.constant.LanguageCode;
import com.scankorea.server.service.product.ProductService;
import com.scankorea.server.service.product.response.ProductViewResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ViewController {
    private final ProductService productService;

    @GetMapping({"/", "/scan"})
    public String scanPage(@Lang LanguageCode lang,
                           Model model) {
        model.addAttribute("lang", lang);
        return "/views/scan/scan";
    }

    @GetMapping("/scan/{gtin}")
    public String scanResult(@PathVariable(name = "gtin") String gtin,
                             @Lang LanguageCode lang,
                             Model model) {
        List<ProductViewResponse> products = productService.findSimilarProducts(gtin, lang);
        model.addAttribute("products", products);
        model.addAttribute("lang", lang);
        return "/views/scan/result";
    }

    @GetMapping("/products/{gtin}")
    public String productDetail(@PathVariable(name = "gtin") String gtin,
                                @Lang LanguageCode lang,
                                Model model) {
        ProductViewResponse view = productService.findView(gtin, lang);
        model.addAttribute("product", view);
        model.addAttribute("lang", lang);
        return "/views/product/detail";
    }
}
