package com.spring.seccurity.Security.controllers.publics;

import com.spring.seccurity.Security.models.product.Product;
import com.spring.seccurity.Security.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *     Está classe contem os métodos para os endpoints da aplicação voltados para os produtos.
 * </p>
 * <p>
 *     A classe conta com as anotações {@link RestController} para indicar ao Spring que se trata de um controller, e
 *     {@link RequestMapping} para definir o path padrão como "products/get".
 * </p>
 *
 * @see RestController
 * @see RequestMapping
 */
@RestController
@RequestMapping("/products/get")
public class ProductPublicController {
    @Autowired
    private ProductService service;

    @GetMapping
    public ResponseEntity<List<Product>> listProducts() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }
}
