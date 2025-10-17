package com.app.comidarapida.web.dtos.roles;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PermisoDTO {

    private Integer id;

    private Integer moduloId;
    private String moduloNombre;

    private Integer submoduloId;
    private String submoduloNombre;

    private Integer accionId;
    private String accionNombre;

    private String codigo;
    private String descripcion;
}
