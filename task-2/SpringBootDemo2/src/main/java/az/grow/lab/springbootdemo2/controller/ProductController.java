package az.grow.lab.springbootdemo2.controller;

import az.grow.lab.springbootdemo2.domain.Product;
import az.grow.lab.springbootdemo2.dto.ProductRequest;
import az.grow.lab.springbootdemo2.dto.ProductResponse;
import az.grow.lab.springbootdemo2.exception.ProductNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/products")
public class ProductController {

    private final ModelMapper mapper;
    private final Map<Integer, Product> productMap = new HashMap<>();
    private UUID nextProductId = UUID.randomUUID();

    @GetMapping()
    public List<Product> getAllProducts() {
        return productMap.entrySet()
                .stream()
                .map(entry -> {
                    Product product = entry.getValue();
                    product.setId(entry.getKey());
                    return product;
                })
                .toList();
    }

    @GetMapping("/{id}")
    public ProductResponse getProductById(@PathVariable(name = "id") Integer id) {
        if (!productMap.containsKey(id)) throw new ProductNotFoundException("Product not found!");
        Product product = productMap.get(id);
        return mapper.map(product, ProductResponse.class);
    }


    @PostMapping()
    public ProductResponse saveProduct(@RequestBody ProductRequest requestDto) {
        Product newProduct = mapper.map(requestDto, Product.class);
        newProduct.setId(Integer.valueOf(nextProductId.toString()));
        newProduct.setCreatedAt(LocalDate.now());
        productMap.put(newProduct.getId(), newProduct);
        nextProductId = UUID.randomUUID();
        return mapper.map(newProduct, ProductResponse.class);
    }


    @PutMapping("/{id}")
    public ProductResponse updateProduct(@PathVariable(name = "id") Integer id, @RequestBody ProductRequest updatedProductDto) {
        Product existingProduct = productMap.get(id);
        if (existingProduct != null) {
            existingProduct.setName(updatedProductDto.getName());
            existingProduct.setPrice(updatedProductDto.getPrice());
            existingProduct.setCreatedAt(LocalDate.now());
            productMap.put(id, existingProduct);
            return mapper.map(existingProduct, ProductResponse.class);
        } else throw new ProductNotFoundException("No product found to update.");
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable(name = "id") Integer id) {
        if (productMap.containsKey(id)) {
            productMap.remove(id);
        } else throw new ProductNotFoundException("No product found to delete.");
    }
}
