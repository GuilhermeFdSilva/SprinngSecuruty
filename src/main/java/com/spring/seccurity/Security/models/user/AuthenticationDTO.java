package com.spring.seccurity.Security.models.user;

/**
 * DTO para recebimento de dados de login para validação.
 *
 * @param login login do usuário.
 * @param password senha do usuário.
 */
public record AuthenticationDTO(String login, String password) {
}
