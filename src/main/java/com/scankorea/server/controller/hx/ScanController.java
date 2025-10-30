package com.scankorea.server.controller.hx;

import com.scankorea.server.common.constant.LanguageCode;
import com.scankorea.server.service.product.BarcodeDecoder;
import com.scankorea.server.service.product.ProductService;
import com.scankorea.server.service.product.response.ProductViewResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ScanController {
    private final BarcodeDecoder barcodeDecoder;
    private final ProductService productService;

    @PostMapping(value = "/scan", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String scanPhoto(@RequestParam("file") MultipartFile file,
                            @RequestParam(name = "lang") LanguageCode lang,
                            Model model) {
        if (file.isEmpty()) {
            model.addAttribute("error", "이미지를 업로드해 주세요.");
            return "/views/scan/scan";
        }

        try (InputStream in = file.getInputStream()) {
            List<ProductViewResponse> products = barcodeDecoder.decodeGtins(in).stream()
                    .map(gtin -> productService.findView(gtin, lang))
                    .toList();
            model.addAttribute("products", products);
            return "/views/scan/result";

        } catch (Exception e) {
            model.addAttribute("error", "이미지를 처리할 수 없어요.");
            return "/views/scan/scan";
        }
    }
}
