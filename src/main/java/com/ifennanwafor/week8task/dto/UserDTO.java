package com.ifennanwafor.week8task.dto;

import lombok.*;
import org.hibernate.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private Long userId;

    private String userName;

    private String email;
    private String firstName;
    private String lastName;

    private String contactNo;

    private String password;
}
