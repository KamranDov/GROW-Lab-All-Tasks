package az.grow.lab.springboot.springbootdemo.Task1.Service;

import az.grow.lab.springboot.springbootdemo.Task1.DTO.ProductDto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    public List<ProductDto> getAllProducts() {
        List<ProductDto> dtoList = new ArrayList<>();
        dtoList.add(new ProductDto(1, "Samsung", "S20", "Android", "Gray", 2.340, LocalDate.now()));
        dtoList.add(new ProductDto(2, "Nokia", "3310", "Nokia Series 30+", "Purple", 110.0, LocalDate.now()));
        dtoList.add(new ProductDto(3, "Huawei", "P30 Pro", "Android", "Pink", 1689.99, LocalDate.now()));
        dtoList.add(new ProductDto(4, "Iphone", "14 Plus", "IOS", "Yellow", 3189.99, LocalDate.now()));

        return dtoList;
    }

    public ProductDto saveProduct(ProductDto paramProduct) {

        ProductDto productDto = new ProductDto();
        productDto.setId(paramProduct.getId());
        productDto.setBrand(paramProduct.getBrand());
        productDto.setModel(paramProduct.getModel());
        productDto.setOperationSystem(paramProduct.getOperationSystem());
        productDto.setColor(paramProduct.getColor());
        productDto.setPrice(paramProduct.getPrice());
        productDto.setCreateDate(paramProduct.getCreateDate());

        return productDto;
    }

}
