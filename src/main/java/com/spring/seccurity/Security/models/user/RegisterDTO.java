package com.spring.seccurity.Security.models.user;

/**
 * DTO utilizada no recebimento de dados para cadastro de novos usuários no sistema.
 *
 * @param login login do usuário.
 * @param password senha do usuário.
 * @param role permições do usuário.
 */
public record RegisterDTO(String login, String password, UserRole role) {
}
