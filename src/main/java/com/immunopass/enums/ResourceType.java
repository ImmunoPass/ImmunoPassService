package com.immunopass.enums;

public enum ResourceType {
    CURRENT("current");
    String type;

    ResourceType(String type) {
        this.type = type;
    }

    @Override public String toString() {
        return type;
    }
}
