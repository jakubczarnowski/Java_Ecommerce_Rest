package com.example.ecommerce.controllers;

import com.example.ecommerce.config.ApiResponse;
import com.example.ecommerce.dto.product.ProductDto;
import com.example.ecommerce.dto.product.ProductsGetDto;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductsGetDto>> getProducts(
            @RequestParam(required = false, name = "page",
                    defaultValue = "1") int page,
            @RequestParam(required = false, name = "size",
                    defaultValue = "20") int size,
            @RequestParam(required = false, name = "search", defaultValue = "") String search,
            @RequestParam(required = false, name="categoryId") Integer categoryId
    ){
        System.out.println(size);
        return new ResponseEntity<>(productService.getProducts(page, size, search, categoryId), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Integer id){
        return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Integer id, @Valid @RequestBody ProductDto product){
        return new ResponseEntity<>(productService.updateProduct(id, product), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody ProductDto product){
        return new ResponseEntity<>(productService.createProduct(product), HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse> deleteProduct(@Valid @PathVariable Integer id){
        productService.removeProductById(id);
        return new ResponseEntity<>(new ApiResponse(true, "Product deleted"), HttpStatus.OK);
    }
    @Autowired
    public ProductController(ProductService service){
        this.productService = service;
    }
}
