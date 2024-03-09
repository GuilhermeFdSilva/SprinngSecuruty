package com.spring.seccurity.Security.models.product;

import jakarta.persistence.*;
import lombok.*;

/**
 * <p>
 *     Classe para representar um produto, contendo {@link #id}, {@link #name}, {@link #price} e {@link #discount}.
 * </p>
 * <p>
 *     Está classe é uma entidade mapeada para o banco de dados, por isso utiliza as anotações {@link Entity} para indicar
 *     ao JPA que se trata de uma entidade e também {@link Table} para definir o nome da tabela no banco, como "products".
 * </p>
 * <p>
 *     As anotações do {@link Lombok} também são utilizadas para, gerar os Getters ({@link Getter}), Setters ({@link Setter}),
 *     Equals e HashCode ({@link EqualsAndHashCode}), um construtor sem argumentos ({@link NoArgsConstructor}) e um construtor
 *     com todos os argumentos ({@link AllArgsConstructor}).
 * </p>
 *
 * @see Getter
 * @see Setter
 * @see EqualsAndHashCode
 * @see NoArgsConstructor
 * @see AllArgsConstructor
 * @see Entity
 * @see Table
 */
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private Double price;
    private Double discount;
}
