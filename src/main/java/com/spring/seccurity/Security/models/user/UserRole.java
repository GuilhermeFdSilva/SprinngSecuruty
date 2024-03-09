package com.spring.seccurity.Security.models.user;

/**
 * Está enum é utilizada na classe de User, e serve como referencia as permições do usuário.
 */
public enum UserRole {
    ADMIN("admin"),
    USER("user");

    private final String ROLE;

    UserRole(String role) {
        this.ROLE = role;
    }

    public String getRole() {
        return this.ROLE;
    }
}
