package com.app.comidarapida.web.dtos.roles;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RolRequestDTO {

    private String nombre;
    private String descripcion;
    private List<Integer> permisosIds;
}
