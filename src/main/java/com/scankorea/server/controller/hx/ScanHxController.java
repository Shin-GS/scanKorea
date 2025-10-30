package com.scankorea.server.controller.hx;

import com.scankorea.server.service.product.BarcodeDecoder;
import io.github.wimdeblauwe.htmx.spring.boot.mvc.HxRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ScanHxController {
    private final BarcodeDecoder barcodeDecoder;

    @HxRequest
    @PostMapping(value = "/scan", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String scanPhoto(@RequestParam("file") MultipartFile file,
                            Model model) {
        if (file.isEmpty()) {
            model.addAttribute("error", "이미지를 업로드해 주세요.");
            return "/views/scan/scan";
        }

        try (InputStream in = file.getInputStream()) {
            Optional<String> optionalGtin = barcodeDecoder.decodeGtins(in).stream().findFirst();
            if (optionalGtin.isPresent()) {
                String gtin = optionalGtin.get();
                if (!gtin.matches("^[0-9]{8,14}$")) {
                    model.addAttribute("error", "지원하지 않는 바코드 형식입니다.");
                    return "/views/scan/scan";
                }

                return "redirect:htmx:/scan/result/" + gtin;
            }

            model.addAttribute("error", "바코드가 없는 이미지입니다.");
            return "/views/scan/scan";

        } catch (Exception e) {
            model.addAttribute("error", "이미지를 처리할 수 없어요.");
            return "/views/scan/scan";
        }
    }
}
