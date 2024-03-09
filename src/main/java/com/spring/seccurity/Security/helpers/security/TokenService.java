package com.spring.seccurity.Security.helpers.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.spring.seccurity.Security.models.user.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * <p>
 *     Classe para geração e validação de Tokens
 * </p>
 * <p>
 *     Essa classe utiliza a anotação {@link Service} para indicar ao Spring que se trata de uma service.
 * </p>
 *
 * @see Service
 */
@Service
public class TokenService {
    /**
     * A anotação {@link Value} injeta o valor à variavel de acordo com seu registro em application.properties
     * <p>
     *     Nesse caso, o valor de "api.security.token.secret".
     * </p>
     */
    @Value("${api.security.token.secret}")
    private String secret;

    /**
     * Função utilizada para gerar o Token de acordo coma as regras do sistema.
     *
     * @param user Usuario que receberá o token
     * @return o Token com nossas especificações.
     */
    public String generateToken(User user) {
        try {
            /* Dessa variavel é definido nosso tipo de criptografia de acordo com a biblioteca JWT e adicionando a utilização
             * da nossa secret. Ela é utilizada na criação do token.
             */
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("auth-api") // Criador do Token.
                    .withSubject(user.getLogin()) // Usuario ao qual o token pertence.
                    .withExpiresAt(generateExpirationDate()) // Data de validade do token
                    .sign(algorithm);

            return token;
        } catch (JWTCreationException e) {
            throw new RuntimeException("Error while generating token", e);
        }
    }

    /**
     * Essa função é responsavel por validar os tokens que a aplicação recebe.
     *
     * @param token token a ser validado.
     * @return o dono to token.
     */
    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e) {
            return "";
        }
    }

    /**
     * Função para criação da data de validade do token.
     *
     * @return Retorna uma data {@link Instant} com o crecimo de 3 horas da data local.
     */
    private Instant generateExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
