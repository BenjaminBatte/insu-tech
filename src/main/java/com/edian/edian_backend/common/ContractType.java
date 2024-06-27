package com.edian.edian_backend.common;

import lombok.Getter;

@Getter
public enum ContractType {
    LIFE("LIF", "Life"),
    HEALTH("HLT", "Health"),
    AUTO("AUT", "Auto"),
    HOME("HOM", "Home"),
    OTHER("OTH", "Other");

    private final String id;
    private final String description;

    ContractType(String id, String description) {
        this.id = id;
        this.description = description;
    }

    public boolean isLife() {
        return this == LIFE;
    }

    public boolean isHealth() {
        return this == HEALTH;
    }

    public boolean isAuto() {
        return this == AUTO;
    }

    public boolean isHome() {
        return this == HOME;
    }

    public boolean isOther() {
        return this == OTHER;
    }

    public static ContractType fromId(String id) {
        for (ContractType type : ContractType.values()) {
            if (type.getId().equals(id)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown id for ContractType: " + id);
    }
}
