package com.edian.edian_backend.common;

import lombok.Getter;

@Getter
public enum ClaimStatus {
    PENDING("PND", "Pending"),
    APPROVED("APV", "Approved"),
    REJECTED("REJ", "Rejected");

    private final String id;
    private final String description;

    ClaimStatus(String id, String description) {
        this.id = id;
        this.description = description;
    }

    public boolean isPending() {
        return this == PENDING;
    }

    public boolean isApproved() {
        return this == APPROVED;
    }

    public boolean isRejected() {
        return this == REJECTED;
    }

    public static ClaimStatus fromId(String id) {
        for (ClaimStatus status : ClaimStatus.values()) {
            if (status.getId().equals(id)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown id for ClaimStatus: " + id);
    }
}
