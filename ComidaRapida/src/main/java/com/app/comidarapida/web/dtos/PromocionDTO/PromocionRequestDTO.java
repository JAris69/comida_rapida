package com.app.comidarapida.web.dtos.PromocionDTO;

import com.app.comidarapida.enums.TipoPromocion;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter

public class PromocionRequestDTO {
    private String nombre;
    private String descripcion;
    private TipoPromocion tipo;
    private BigDecimal valor;
    private Boolean activo;
}
