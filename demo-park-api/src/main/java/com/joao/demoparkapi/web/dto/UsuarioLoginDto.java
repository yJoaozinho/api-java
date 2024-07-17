package com.joao.demoparkapi.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UsuarioLoginDto {

    @Email(message = "formato do email invalido")
    @NotBlank
    private String username;
    @NotBlank
    @Size(min = 6,  max = 22)
    private String password;

}
