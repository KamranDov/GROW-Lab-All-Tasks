package az.grow.lab.springbootdemo3.controller;

import az.grow.lab.springbootdemo3.domain.Product;
import az.grow.lab.springbootdemo3.dto.ProductRequest;
import az.grow.lab.springbootdemo3.dto.ProductResponse;
import az.grow.lab.springbootdemo3.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping()
    public List<Product> gelAllProducts() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public ProductResponse getProduct(@PathVariable(name = "id") Integer id) {
        return productService.findById(id);
    }

    @PostMapping()
    public ProductResponse insertProduct(@RequestBody ProductRequest productRequest) {
        return productService.persist(productRequest);
    }

    @PutMapping("/{id}")
    public ProductResponse updateProduct(@PathVariable(name = "id") Integer id, ProductRequest productRequest) {
        return productService.saveById(id, productRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable(name = "id") Integer id) {
        productService.deleteById(id);
    }
}
