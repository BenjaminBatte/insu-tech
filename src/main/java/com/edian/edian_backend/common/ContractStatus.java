package com.edian.edian_backend.common;

import lombok.Getter;

@Getter
public enum ContractStatus {
        IN_PROGRESS("INP", "In Progress"),
        UNDERWRITER_ANALYSIS("UWA", "Underwriter Analysis"),
        PROPOSED("PRO", "Proposed"),
        BOUND("BND", "Bound"),
        AWAITING_VERIFICATION("AVV", "Awaiting Verification"),
        ACTIVE("ACT", "Active"),
        EXPIRED("EXP", "Expired"),
        RENEWED("REN", "Renewed"),
        VOID("VOD", "Void"),
        CLOSED("CLD", "Closed");

        private final String id;
        private final String description;

        ContractStatus(String id, String description) {
                this.id = id;
                this.description = description;
        }

        public boolean isInProgress() {
                return this == IN_PROGRESS;
        }

        public boolean isUnderwriterAnalysis() {
                return this == UNDERWRITER_ANALYSIS;
        }

        public boolean isProposed() {
                return this == PROPOSED;
        }

        public boolean isBound() {
                return this == BOUND;
        }

        public boolean isAwaitingVerification() {
                return this == AWAITING_VERIFICATION;
        }

        public boolean isActive() {
                return this == ACTIVE;
        }

        public boolean isExpired() {
                return this == EXPIRED;
        }

        public boolean isRenewed() {
                return this == RENEWED;
        }

        public boolean isVoid() {
                return this == VOID;
        }

        public boolean isClosed() {
                return this == CLOSED;
        }

        public static ContractStatus fromId(String id) {
                for (ContractStatus status : ContractStatus.values()) {
                        if (status.getId().equals(id)) {
                                return status;
                        }
                }
                throw new IllegalArgumentException("Unknown id for ContractStatus: " + id);
        }
}
