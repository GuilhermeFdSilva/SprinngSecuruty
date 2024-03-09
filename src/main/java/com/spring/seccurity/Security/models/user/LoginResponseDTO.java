package com.spring.seccurity.Security.models.user;

/**
 * DTO para envio do token de autenticação ao usuário.
 *
 * @param token token que será enviado.
 */
public record LoginResponseDTO(String token) {
}
