package az.grow.lab.springbootdemo3.service;

import az.grow.lab.springbootdemo3.domain.Product;
import az.grow.lab.springbootdemo3.dto.ProductRequest;
import az.grow.lab.springbootdemo3.dto.ProductResponse;
import az.grow.lab.springbootdemo3.exception.ProductNotFoundException;
import az.grow.lab.springbootdemo3.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class ProductService implements ProductRepository {

    private final ModelMapper mapper;
    private final Map<Integer, Product> productMap = new HashMap<>();
private final AtomicInteger nextProductId = new AtomicInteger(1);
    @Override
    public List<Product> findAll() {
        return productMap.entrySet()
                .stream()
                .map(e -> {
                    Product product = e.getValue();
                    product.setId(e.getKey());
                    return product;
                })
                .toList();
    }

    @Override
    public ProductResponse findById(Integer id) {
        if (!productMap.containsKey(id)) throw new ProductNotFoundException("Product not found!");
        Product product = productMap.get(id);
        return mapper.map(product, ProductResponse.class);
    }

    @Override
    public ProductResponse persist(ProductRequest productRequest) {
        Product newProduct = mapper.map(productRequest, Product.class);
        int productId = nextProductId.incrementAndGet();
        newProduct.setId(productId);
        newProduct.setCreatedAt(LocalDate.now());
        productMap.put(productId, newProduct);
        return mapper.map(newProduct, ProductResponse.class);
    }

    @Override
    public ProductResponse saveById(Integer id, ProductRequest productRequest) {
        Product existingProduct = productMap.get(id);
        if (existingProduct != null){
            existingProduct.setName(productRequest.getName());
            existingProduct.setPrice(productRequest.getPrice());
            existingProduct.setCreatedAt(LocalDate.now());
            productMap.put(id,existingProduct);
            return mapper.map(existingProduct,ProductResponse.class);
        }else throw new ProductNotFoundException("No product found to update");
    }

    @Override
    public void deleteById(Integer id) {
        if (productMap.containsKey(id)){
            productMap.remove(id);
        } else throw new ProductNotFoundException("No product found to delete.");
    }
}
