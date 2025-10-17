package com.app.comidarapida.web.dtos.InventarioDTO;

import com.app.comidarapida.enums.TipoMovimiento;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class InventarioResponseDTO {
    private Long id;
    private BigDecimal cantidad;
    private TipoMovimiento tipoMovimiento;
    private String motivo;
    private LocalDate fechaMov;
    private LocalDateTime log_create;
    private LocalDateTime log_update;
}
