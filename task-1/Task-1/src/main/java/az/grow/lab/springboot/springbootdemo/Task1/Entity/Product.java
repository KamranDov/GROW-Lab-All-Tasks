package az.grow.lab.springboot.springbootdemo.Task1.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@Entity
//@Table(name = "Mehsul")
public class Product {
    private Integer id;
    private String brand;
    private String model;
    private String operationSystem;
    private String connectivity;
    private Double price;
    private Integer stock;
    private Integer productCode;
    private LocalDate createDate;

}
