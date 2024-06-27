package com.edian.edian_backend.common;

import lombok.Getter;

@Getter
public enum AccountType {
    LEASING("LES", "Leasing Account"),
    STANDARD("STD", "Standard Account");

    private final String id;
    private final String description;

    AccountType(String id, String description) {
        this.id = id;
        this.description = description;
    }

    public boolean isLeasing() {
        return this == LEASING;
    }

    public boolean isStandard() {
        return this == STANDARD;
    }

    public static AccountType fromId(String id) {
        for (AccountType type : AccountType.values()) {
            if (type.getId().equals(id)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown id for AccountType: " + id);
    }
}
