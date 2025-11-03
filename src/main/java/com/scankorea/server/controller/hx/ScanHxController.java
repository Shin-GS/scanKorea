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
            response.setHeader("HX-Alert", "Please select an image to upload.");
            return null;
        }

        String contentType = file.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            response.setHeader("HX-Client-Redirect", "/scan");
            response.setHeader("HX-Alert", "Only image files are supported. Please upload a valid photo.");
            return null;
        }

        try (InputStream in = file.getInputStream()) {
            Optional<String> optionalGtin = barcodeDecoder.decodeGtins(in).stream().findFirst();
            if (optionalGtin.isEmpty()) {
                response.setHeader("HX-Client-Redirect", "/scan");
                response.setHeader("HX-Alert", "No barcode detected. Please try again with a clearer photo.");
                return null;
            }

            String gtin = optionalGtin.get();
            if (!GtinUtils.isValidGtin(gtin)) {
                response.setHeader("HX-Client-Redirect", "/scan");
                response.setHeader("HX-Alert", "The scanned code format is not supported.");
                return null;
            }

            response.setHeader("HX-Client-Redirect", "/scan/" + gtin);
            return null;

        } catch (Exception e) {
            log.error(e.getMessage(), e);

            response.setHeader("HX-Client-Redirect", "/scan");
            response.setHeader("HX-Alert", "An unexpected error occurred. Please try again later.");
            return null;
        }
    }
}
