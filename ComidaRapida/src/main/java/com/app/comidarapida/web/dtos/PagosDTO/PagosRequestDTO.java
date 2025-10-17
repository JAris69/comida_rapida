package com.app.comidarapida.web.dtos.PagosDTO;

import com.app.comidarapida.enums.MetodoPagos;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class PagosRequestDTO {
    private MetodoPagos metodo;
    private BigDecimal monto;
    private LocalDate fechaPago;
    private Long idPedido;
}
