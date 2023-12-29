package az.grow.lab.springboot.springbootdemo.Task1.Controller;

import az.grow.lab.springboot.springbootdemo.Task1.DTO.ProductDto;
import az.grow.lab.springboot.springbootdemo.Task1.Entity.Product;
import az.grow.lab.springboot.springbootdemo.Task1.Service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/controller")
public class ProductController {

    private ProductService productService;

    @GetMapping("/getAll")
    public List<ProductDto> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/getById/{id}")
    public ProductDto getProductById(@PathVariable Integer id) {
        for (ProductDto loopDTO : productService.getAllProducts()) {
            if (loopDTO.getId().equals(id)) {
                return loopDTO;
            }
        }
        return null;
    }

    @PostMapping("/newProduct")
    public ProductDto insertProduct(@RequestBody ProductDto insert) {
        return productService.saveProduct(insert);
    }

}


//get list seklinde
//get id-ye gore
//post id-ye gore