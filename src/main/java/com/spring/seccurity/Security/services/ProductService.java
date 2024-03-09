package com.spring.seccurity.Security.services;

import com.spring.seccurity.Security.models.product.Product;
import com.spring.seccurity.Security.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *     Classe contendo os métodos de CRUD para o objeto produto.
 * </p>
 * <p>
 *     Aclasse utiliza a anotação {@link Service} para indicar ao Spring que se trata de um serviço.
 * </p>
 *
 * @see Service
 */
@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    public Product createProduct(Product product) {
        return repository.save(product);
    }

    public List<Product> findAll() {
        return repository.findAll();
    }

    public Product updateProduct(Product product) {
        return repository.save(product);
    }

    public void deleteProduct(String id) {
        repository.deleteById(id);
    }
}
