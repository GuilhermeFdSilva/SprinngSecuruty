package com.spring.seccurity.Security.controllers.privates;

import com.spring.seccurity.Security.models.product.Product;
import com.spring.seccurity.Security.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *     Classe contendo os métodos para os endpoints privados relacionados aos produtos. Essa classe conta com metodos para
 *     criação, atualização e exclusão dos produtos.
 * </p>
 * <p>
 *     Essa classe conta com as anotações {@link RestController} para indicar ao Spring que essa classe se trata de um
 *     controller, e a anotação {@link RequestMapping} para definir o path padrão como "/products/admin".
 * </p>
 *
 * @see RestController
 * @see RequestMapping
 */
@RestController
@RequestMapping("/products/admin")
public class ProductPrivateController {
    @Autowired
    private ProductService service;

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return new ResponseEntity<>(service.createProduct(product), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        return new ResponseEntity<>(service.updateProduct(product), HttpStatus.OK);
    }

    @DeleteMapping(path = "/:id")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
        service.deleteProduct(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
