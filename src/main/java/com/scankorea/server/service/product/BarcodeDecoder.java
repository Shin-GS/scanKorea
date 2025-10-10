package com.scankorea.server.service.product;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.multi.GenericMultipleBarcodeReader;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class BarcodeDecoder {
    public Set<String> decodeGtins(InputStream imageStream) {
        try {
            BufferedImage img = ImageIO.read(imageStream);
            if (img == null) {
                return Set.of();
            }

            LuminanceSource source = new BufferedImageLuminanceSource(img);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

            Map<DecodeHintType, Object> hints = new EnumMap<>(DecodeHintType.class);
            hints.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);
            hints.put(DecodeHintType.POSSIBLE_FORMATS, List.of(
                    BarcodeFormat.EAN_13, BarcodeFormat.UPC_A, BarcodeFormat.UPC_E, BarcodeFormat.EAN_8
            ));

            // 1) 단일 탐지
            try {
                Result r = new MultiFormatReader().decode(bitmap, hints);
                return normalizeToGtins(Set.of(r.getText()));
            } catch (NotFoundException ignored) {
            }

            // 2) 다중 탐지
            try {
                Result[] results = new GenericMultipleBarcodeReader(new MultiFormatReader())
                        .decodeMultiple(bitmap, hints);
                return normalizeToGtins(Arrays.stream(results).map(Result::getText).collect(Collectors.toSet()));
            } catch (NotFoundException e) {
                return Set.of();
            }
        } catch (Exception e) {
            return Set.of();
        }
    }

    private Set<String> normalizeToGtins(Set<String> rawCodes) {
        return rawCodes.stream()
                .map(s -> s == null ? "" : s.replaceAll("\\s+", ""))
                .map(this::toGtin13OrNull)
                .filter(Objects::nonNull)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    /**
     * UPC-A(12) → 앞 0 붙여 GTIN-13, EAN-13(13)은 그대로, EAN-8(8)은 MVP에선 스킵
     */
    private String toGtin13OrNull(String code) {
        if (code.matches("\\d{13}")) {
            return code;
        }

        if (code.matches("\\d{12}")) {
            return "0" + code;
        }

        if (code.matches("\\d{8}")) {
            return null; // 필요 시 변환 로직 후처리
        }

        return null;
    }
}
