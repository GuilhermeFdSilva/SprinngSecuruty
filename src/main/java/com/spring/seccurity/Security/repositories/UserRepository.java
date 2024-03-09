package com.spring.seccurity.Security.repositories;

import com.spring.seccurity.Security.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    /**
     * É necessario retornar o UserDetails para a utilização pelo Spring.
     *
     * @param login Login do usuário.
     * @return O usuário salvo no sistema em formato {@link UserDetails}
     */
    UserDetails findByLogin(String login);
}
