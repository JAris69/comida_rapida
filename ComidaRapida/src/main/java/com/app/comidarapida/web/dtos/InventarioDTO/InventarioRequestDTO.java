package com.app.comidarapida.web.dtos.InventarioDTO;

import com.app.comidarapida.enums.TipoMovimiento;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class InventarioRequestDTO {
    private BigDecimal cantidad;
    private TipoMovimiento tipoMovimiento;
    private String motivo;
    private LocalDate fechaMov;
    private Long idProducto;
}
