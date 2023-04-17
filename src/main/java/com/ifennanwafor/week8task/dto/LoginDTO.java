package com.ifennanwafor.week8task.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {
    private String email;
    @NotBlank(message = "Username can not be blank")

    private String userName;

    @NotBlank(message = "Password can not be blank")

    private String password;
}
