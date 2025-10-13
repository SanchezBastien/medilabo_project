package com.medilabo.patient.model;

/**
 * Enumeration for patient sex.  Using a strongly typed enum rather than a
 * simple character makes the domain model self‑documenting and restricts
 * persistence to the permitted values.
 */
public enum Gender {
    MALE("M"),
    FEMALE("F");

    private final String code;

    Gender(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    /**
     * Convert a database code to a Gender enum.  If the code does not match
     * any value this method throws an IllegalArgumentException.  The
     * conversion is case‑insensitive.
     */
    public static Gender fromCode(String code) {
        for (Gender gender : values()) {
            if (gender.code.equalsIgnoreCase(code)) {
                return gender;
            }
        }
        throw new IllegalArgumentException("Unknown gender code: " + code);
    }
}