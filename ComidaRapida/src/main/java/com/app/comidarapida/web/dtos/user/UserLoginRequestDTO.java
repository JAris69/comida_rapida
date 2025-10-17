package com.app.comidarapida.web.dtos.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginRequestDTO {

    private String nombre_usuario;
    private String password;
}
