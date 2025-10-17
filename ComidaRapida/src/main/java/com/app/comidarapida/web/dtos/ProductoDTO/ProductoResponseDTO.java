package com.app.comidarapida.web.dtos.ProductoDTO;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class ProductoResponseDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private String categoria;
    private Boolean activo;
    private String imagenUrl;
    private LocalDateTime log_create;
    private LocalDateTime log_update;
}
