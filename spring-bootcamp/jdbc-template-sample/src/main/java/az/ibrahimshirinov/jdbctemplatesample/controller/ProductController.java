package az.ibrahimshirinov.jdbctemplatesample.controller;

import az.ibrahimshirinov.jdbctemplatesample.dto.ProductRequest;
import az.ibrahimshirinov.jdbctemplatesample.dto.ProductResponse;
import az.ibrahimshirinov.jdbctemplatesample.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {


    private final ProductService service;


    @PostMapping
    public void create(@Valid @RequestBody ProductRequest dto) {
        service.save(dto);
    }


    @GetMapping("/{id}")
    public ProductResponse get(@PathVariable Integer id) {
        return service.get(id);
    }

    @GetMapping
    public List<ProductResponse> get() {
        return service.get();
    }


    @PutMapping("/{id}")
    public void update(@PathVariable Integer id, @RequestBody ProductRequest dto) {
        service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }


}
