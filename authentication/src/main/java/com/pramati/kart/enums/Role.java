package com.pramati.kart.enums;

public enum Role {
    ROLE_USER("User"), ROLE_ADMIN("Admin"),ROLE_SELLER("Seller");
    private String name;

    public String getName() {
        return name;
    }

    Role(String name) {
        this.name = name;
    }
}
