package az.ibrahimshirinov.jdbctemplatesample.service;

import az.ibrahimshirinov.jdbctemplatesample.dto.ProductRequest;
import az.ibrahimshirinov.jdbctemplatesample.dto.ProductResponse;
import az.ibrahimshirinov.jdbctemplatesample.exception.NotFoundException;
import az.ibrahimshirinov.jdbctemplatesample.mapper.DomainMapper;
import az.ibrahimshirinov.jdbctemplatesample.mapper.DtoMapper;
import az.ibrahimshirinov.jdbctemplatesample.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;
    private final DomainMapper domainMapper;
    private final DtoMapper dtoMapper;


    public void save(ProductRequest dto) {
        var product = domainMapper.toDomain(dto);
        repository.insert(product);
    }

    public ProductResponse get(Integer id) {
        var product = repository.find(id)
                .orElseThrow(() -> new NotFoundException(String.format("Product not found with id: %s", id)));
        return dtoMapper.toDto(product);
    }

    public List<ProductResponse> get() {
        return repository.find()
                .stream().map(dtoMapper::toDto)
                .collect(Collectors.toList());
    }

    public void update(Integer id, ProductRequest dto) {
        var product = domainMapper.toDomain(dto);
        product.setModifiedAt(LocalDateTime.now());
        repository.update(id, product);
    }

    public void delete(Integer id) {
        repository.delete(id);
    }
}
