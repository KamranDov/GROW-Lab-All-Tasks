package az.grow.lab.jdbctemplatetask4.dto;

import az.grow.lab.jdbctemplatetask4.domain.PassportInformation;
import az.grow.lab.jdbctemplatetask4.enums.ActionStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonalDto {

    private Integer id;
    private String homeNumber;
    private String workNumber;
    private String mobileNumber;
    private String email;
    private String country;
    private String city;
    private String street;
    private String postalCode;
    private ActionStatus actionStatus;
}
