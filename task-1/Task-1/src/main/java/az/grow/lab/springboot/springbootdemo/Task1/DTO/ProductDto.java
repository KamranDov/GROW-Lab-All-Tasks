package az.grow.lab.springboot.springbootdemo.Task1.DTO;

import az.grow.lab.springboot.springbootdemo.Task1.Entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private Integer id;
    private String brand;
    private String model;
    private String operationSystem;
    private String color;
    private Double price;
    private LocalDate createDate;

}

