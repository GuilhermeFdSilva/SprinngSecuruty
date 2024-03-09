package com.spring.seccurity.Security.services;

import com.spring.seccurity.Security.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * <p>
 *     Service para localização do uzuario a ser altenticado, esta classe é utilizada pelo SpringSecurity para obter o usuário.
 * </p>
 * <p>
 *     Aclasse utiliza a anotação {@link Service} para indicar ao Spring que se trata de um serviço.
 * </p>
 * <p>
 *     Além disso a classe implementa {@link UserDetailsService} para sobreescrever o método utilizado pelo Spring poara
 *     localizar o usuário a ser autenticado.
 * </p>
 *
 * @see Service
 * @see UserDetailsService
 */
@Service
public class AuthorizationService implements UserDetailsService {
    @Autowired
    private UserRepository repository;

    /**
     * Para esse método é necessario retornar um {@link UserDetails}, pois atraves das funções dele que o Spring fará a
     * autenticaçãoo do Usuário
     *
     * @param username Nome do usuario (login)
     * @return retorna o UserDetails para o Spring fazer a autenticação.
     * @throws UsernameNotFoundException Caso não encontre o usuário.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByLogin(username);
    }
}
