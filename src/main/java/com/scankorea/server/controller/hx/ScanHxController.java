package com.scankorea.server.controller.hx;

import com.scankorea.server.common.util.GtinUtils;
import com.scankorea.server.service.product.BarcodeDecoder;
import io.github.wimdeblauwe.htmx.spring.boot.mvc.HxRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ScanHxController {

    private final BarcodeDecoder barcodeDecoder;

    @HxRequest
    @PostMapping(value = "/scan", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String scanPhoto(@RequestParam("file") MultipartFile file,
                            Model model) {
        if (file == null || file.isEmpty()) {
            model.addAttribute("error", "이미지를 선택해 주세요.");
            return "/views/scan/scan";
        }

        String contentType = file.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            model.addAttribute("error", "이미지 파일만 업로드할 수 있어요.");
            return "/views/scan/scan";
        }

        try (InputStream in = file.getInputStream()) {
            Optional<String> optionalGtin = barcodeDecoder.decodeGtins(in).stream().findFirst();
            if (optionalGtin.isEmpty()) {
                model.addAttribute("error", "이미지에서 바코드를 찾지 못했어요.");
                return "/views/scan/scan";
            }

            String gtin = optionalGtin.get();
            if (!GtinUtils.isValidGtin(gtin)) {
                log.warn("Unsupported GTIN pattern detected: {}", gtin);
                model.addAttribute("error", "지원하지 않는 바코드 형식입니다.");
                return "/views/scan/scan";
            }

            String encoded = UriUtils.encodePathSegment(gtin, StandardCharsets.UTF_8);
            return "redirect:htmx:/scan/result/" + encoded;

        } catch (Exception e) {
            log.warn("Scan processing failed", e);
            model.addAttribute("error", "이미지를 처리하는 중 문제가 발생했어요. 잠시 후 다시 시도해 주세요.");
            return "/views/scan/scan";
        }
    }
}
