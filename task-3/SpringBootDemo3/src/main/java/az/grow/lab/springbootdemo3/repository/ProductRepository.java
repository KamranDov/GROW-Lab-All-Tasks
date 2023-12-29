package az.grow.lab.springbootdemo3.repository;

import az.grow.lab.springbootdemo3.domain.Product;
import az.grow.lab.springbootdemo3.dto.ProductRequest;
import az.grow.lab.springbootdemo3.dto.ProductResponse;

import java.util.List;

public interface ProductRepository {

    List<Product> findAll();
    ProductResponse findById(Integer id);
    ProductResponse persist(ProductRequest productRequest);
    ProductResponse saveById(Integer id, ProductRequest productRequest);
    void deleteById(Integer id);
}
