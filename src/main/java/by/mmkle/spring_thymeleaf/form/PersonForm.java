package by.mmkle.spring_thymeleaf.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonForm {
    private String firstName;
    private String lastName;
}
