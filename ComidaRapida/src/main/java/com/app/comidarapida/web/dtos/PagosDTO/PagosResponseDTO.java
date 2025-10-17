package com.app.comidarapida.web.dtos.PagosDTO;

import com.app.comidarapida.enums.MetodoPagos;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class PagosResponseDTO {
    private Long id;
    private MetodoPagos metodo;
    private BigDecimal monto;
    private LocalDate fechaPago;
    private LocalDateTime log_create;
    private LocalDateTime log_update;
}
