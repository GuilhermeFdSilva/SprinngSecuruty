package com.spring.seccurity.Security.models.user;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 *     Classe que representa um usuário do sistema, contendo {@link #id}, {@link #login}, {@link #password} e {@link #role}.
 * </p>
 * <p>
 *     Está classe é uma entidade mapeada para o banco de dados, por isso utiliza as anotações {@link Entity} para indicar
 *     ao JPA que se trata de uma entidade e também {@link Table} para definir o nome da tabela no banco, como "users".
 * </p>
 * <p>
 *     As anotações do {@link Lombok} também são utilizadas para, gerar os Getters ({@link Getter}), Setters ({@link Setter}),
 *     Equals e HashCode ({@link EqualsAndHashCode}), um construtor sem argumentos ({@link NoArgsConstructor}) e um construtor
 *     com todos os argumentos ({@link AllArgsConstructor}).
 * </p>
 *
 * <p>
 *     Além disso essa classe implementa a Interface {@link UserDetails}, essa interface é responsavel pos gerar os métodos
 *     de autentificação para o SpringSecurity, ela for nece os métodos para altenticar o usuário como o {@link #getUsername()},
 *     o {@link #getPassword()} e o {@link #getAuthorities()}
 * </p>
 *
 * @see Getter
 * @see Setter
 * @see EqualsAndHashCode
 * @see NoArgsConstructor
 * @see AllArgsConstructor
 * @see Entity
 * @see Table
 * @see UserDetails
 */
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(nullable = false)
    private String login;
    @Column(nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    private UserRole role;

    public User(String login, String password, UserRole role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }

    /**
     * Método utilizado pelo SpringSecurity para verificar as permições do usuário.
     *
     * @return Retorna uma lista contendo todas as "roles" do usuário.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == UserRole.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
