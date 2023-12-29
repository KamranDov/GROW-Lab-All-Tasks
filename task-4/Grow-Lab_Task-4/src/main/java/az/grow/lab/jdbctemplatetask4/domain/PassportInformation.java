package az.grow.lab.jdbctemplatetask4.domain;

import az.grow.lab.jdbctemplatetask4.enums.ActionStatus;
import az.grow.lab.jdbctemplatetask4.enums.FinalStatus;
import az.grow.lab.jdbctemplatetask4.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
public class PassportInformation {

    private Integer id;
    private String name;
    private String surname;
    private String patronymic;
    private LocalDate birthDate;
    private Gender gender;
    private String passportNumber;
    private ActionStatus actionStatus;
    private FinalStatus finalStatus;

}
