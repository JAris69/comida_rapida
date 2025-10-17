package com.app.comidarapida.web.dtos.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegisterRequestDTO {

    private String correo;
    private String nombreUsuario;
    private String password;
    private Integer rolId;

    private Long personalId;
}
