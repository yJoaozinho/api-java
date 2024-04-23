package com.joao.demoparkapi.web.dto;

import lombok.*;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString
public class UsuarioResponseDto {

    private Long id;
    private String username;
    private String role;

}
