package com.scankorea.server.controller.view;

import com.scankorea.server.common.constant.LanguageCode;
import com.scankorea.server.service.product.ProductService;
import com.scankorea.server.service.product.response.ProductViewResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class ViewController {
    private final ProductService productService;

    @GetMapping({"/", "/scan"})
    public String scanPage() {
        return "/views/scan/scan";
    }

    @GetMapping("/p/{gtin}")
    public String productDetail(@PathVariable(name = "gtin") String gtin,
                                @RequestParam(name = "lang") LanguageCode lang,
                                Model model) {
        ProductViewResponse view = productService.findView(gtin, lang);
        model.addAttribute("product", view);
        return "/views/product/detail";
    }
}
