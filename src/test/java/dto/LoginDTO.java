package dto;

import lombok.*;
import org.checkerframework.checker.units.qual.Length;

import javax.validation.constraints.NotBlank;


@AllArgsConstructor
@NoArgsConstructor
@Builder


public class LoginDTO {

    @NotBlank(message = "email cannot be blank")
    private String email;
    @NotBlank(message = "Password cannot be blank")
    @Length(min = 8, max = 16, message = "Invalid Password")
    private String password;
}
