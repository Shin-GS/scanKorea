package com.scankorea.server.controller.view;

import com.scankorea.server.common.annotation.Lang;
import com.scankorea.server.common.constant.LanguageCode;
import com.scankorea.server.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class ViewController {
    private final ProductService productService;

    @GetMapping({"/", "/scan"})
    public String scanPage(@Lang LanguageCode lang,
                           Model model) {
        model.addAttribute("languages", LanguageCode.values());
        model.addAttribute("lang", lang);
        return "/views/scan/scan";
    }

    @GetMapping("/scan/{gtin}")
    public String scanResult(@PathVariable(name = "gtin") String gtin,
                             @Lang LanguageCode lang,
                             Model model) {
        model.addAttribute("languages", LanguageCode.values());
        model.addAttribute("lang", lang);
        model.addAttribute("products", productService.findSimilarProducts(gtin, lang));
        return "/views/scan/result";
    }

    @GetMapping("/products/{gtin}")
    public String productDetail(@PathVariable(name = "gtin") String gtin,
                                @Lang LanguageCode lang,
                                Model model) {
        model.addAttribute("languages", LanguageCode.values());
        model.addAttribute("lang", lang);
        model.addAttribute("product", productService.findView(gtin, lang));
        return "/views/product/detail";
    }
}
