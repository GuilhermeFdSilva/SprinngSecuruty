package com.spring.seccurity.Security.helpers.security;

import com.spring.seccurity.Security.repositories.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * <p>
 *     Classe de filtro para as requisições que chegarem aos endpoints.
 * </p>
 * <p>
 *     Para indicar ao Spring que essa classe deve ser gerenciada e utilizada por ele, utiliza-se a anotação {@link Component}.
 * </p>
 * <p>
 *     Alem disso utiliza-se como extenção a classe {@link OncePerRequestFilter} para indicar que essa checagem deve ser
 *     feita uma vez por requisição, e o método utilizado na checagem é {@link #doFilterInternal(HttpServletRequest, HttpServletResponse, FilterChain)}.
 * </p>
 *
 * @see Component
 * @see OncePerRequestFilter
 */
@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    private TokenService service;
    @Autowired
    private UserRepository repository;

    /**
     * Método de filtro pelo qual as requisições terão que passar para validar se o usuário ja esta logado ou não, atraves
     * dos tokens.
     *
     * @param request A requisição feita a API
     * @param response A resposta para a requisição.
     * @param filterChain A corrente de filtros plicados às requisições.
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = recoverToken(request);

        // Apos obter o token é verificado se o mesmo não é nulo, caso seja a corrente de filtros da continuidade com usuário deslogado
        if (token != null) {
            String login = service.validateToken(token);
            UserDetails user = repository.findByLogin(login);

            // É chamado para obter as credenciais do usuários referenciado no token
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

            // Após obter as credenciais, é passado ao contexto as informações do usuário e suas roles
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    /**
     * Função para extração do token do header da requisição.
     *
     * @param request requisição.
     * @return O token para contido na requisição.
     */
    private String recoverToken(HttpServletRequest request){
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null) return null;

        return authHeader.replace("Bearer ", "");
    }
}
