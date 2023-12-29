package az.ibrahimshirinov.restapiexample.controller;

import az.ibrahimshirinov.restapiexample.dto.ProductDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
public class HelloController {

    @GetMapping("/{id}")
    public String getData(@PathVariable Integer id) {
        return "hello";
    }

    @GetMapping("/store/{id}")
    public String getByStore(@PathVariable Integer id) {
        return "hello";
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto save(@RequestBody ProductDto dto) {
        log.info("Product saved. id: {}", dto.getId());
        return dto;
    }

    @DeleteMapping("/{id}")
    public void deleteRecord(@PathVariable Integer id) {
        log.info("Product deleted. id: {}", id);
    }

    @PutMapping
    public ProductDto update(@RequestBody ProductDto dto) {
        var product = new ProductDto();
        product.setId(3);
        product.setMake("Samsung");
        product.setModel("S23");
        product.setPrice(BigDecimal.valueOf(2000));
        product.setModel(dto.getModel());

        return product;
    }

}
