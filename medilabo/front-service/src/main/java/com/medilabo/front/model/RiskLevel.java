package com.medilabo.front.model;

/**
 * Enumeration mirroring the risk levels used by the risk service.  Kept
 * separate from the back-end enum to avoid coupling the modules.
 */
public enum RiskLevel {
    NONE,
    BORDERLINE,
    IN_DANGER,
    EARLY_ONSET
}