package com.scankorea.server.controller.hx;

import com.scankorea.server.common.util.GtinUtils;
import com.scankorea.server.service.product.BarcodeDecoder;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ScanHxController {
    private final BarcodeDecoder barcodeDecoder;

    @PostMapping(value = "/hx/scan", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String scanPhoto(@RequestParam("file") MultipartFile file,
                            HttpServletResponse response) {
        if (file == null || file.isEmpty()) {
            response.setHeader("HX-Client-Redirect", "/scan");
            response.setHeader("HX-Alert", "please select image file");
            return null;
        }

        String contentType = file.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            response.setHeader("HX-Client-Redirect", "/scan");
            response.setHeader("HX-Alert", "you can upload image file");
            return null;
        }

        try (InputStream in = file.getInputStream()) {
            Optional<String> optionalGtin = barcodeDecoder.decodeGtins(in).stream().findFirst();
            if (optionalGtin.isEmpty()) {
                response.setHeader("HX-Client-Redirect", "/scan");
                response.setHeader("HX-Alert", "Can not found Barcode from Image");
                return null;
            }

            String gtin = optionalGtin.get();
            if (!GtinUtils.isValidGtin(gtin)) {
                response.setHeader("HX-Client-Redirect", "/scan");
                response.setHeader("HX-Alert", "Unsupported GTIN pattern");
                return null;
            }

            response.setHeader("HX-Client-Redirect", "/scan/" + gtin);
            return null;

        } catch (Exception e) {
            response.setHeader("HX-Client-Redirect", "/scan");
            response.setHeader("HX-Alert", "Internal Server Error");
            return null;
        }
    }
}
