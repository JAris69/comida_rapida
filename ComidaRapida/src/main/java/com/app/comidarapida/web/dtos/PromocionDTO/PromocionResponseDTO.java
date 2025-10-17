package com.app.comidarapida.web.dtos.PromocionDTO;

import com.app.comidarapida.enums.TipoPromocion;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class PromocionResponseDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private TipoPromocion tipo;
    private BigDecimal valor;
    private Boolean activo;
    private LocalDateTime log_create;
    private LocalDateTime log_update;
}
