package com.app.comidarapida.web.dtos.ProductoDTO;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductoRequestDTO {
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private String categoria;
    private Boolean activo;
    private String imagenUrl;
}
