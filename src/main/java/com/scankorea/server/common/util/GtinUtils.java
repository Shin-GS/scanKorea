package com.scankorea.server.common.util;

public final class GtinUtils {
    private GtinUtils() {
    }

    /**
     * Validate GTIN (8, 12, 13, or 14 digits) including check digit.
     *
     * @param gtin numeric string
     * @return true if valid structure and correct check digit
     */
    public static boolean isValidGtin(String gtin) {
        if (gtin == null || !gtin.matches("\\d+")) {
            return false;
        }

        int length = gtin.length();
        if (length != 8 && length != 12 && length != 13 && length != 14) {
            return false;
        }

        int expectedCheckDigit = calculateCheckDigit(gtin.substring(0, length - 1));
        int actualCheckDigit = Character.getNumericValue(gtin.charAt(length - 1));
        return expectedCheckDigit == actualCheckDigit;
    }

    /**
     * Calculate the GTIN check digit for a given numeric sequence.
     *
     * @param body numeric string without the check digit
     * @return check digit (0–9)
     */
    public static int calculateCheckDigit(String body) {
        if (body == null || !body.matches("\\d+")) {
            throw new IllegalArgumentException("GTIN body must be numeric");
        }

        int sum = 0;
        int length = body.length();

        // Traverse digits from right to left
        for (int i = 0; i < length; i++) {
            int digit = Character.getNumericValue(body.charAt(length - 1 - i));
            // Odd positions (from right, 1-based): weight = 3
            sum += (i % 2 == 0) ? digit * 3 : digit;
        }

        int mod = sum % 10;
        return (mod == 0) ? 0 : 10 - mod;
    }
}
