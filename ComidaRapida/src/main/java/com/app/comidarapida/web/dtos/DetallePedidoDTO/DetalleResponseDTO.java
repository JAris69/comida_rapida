package com.app.comidarapida.web.dtos.DetallePedidoDTO;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class DetalleResponseDTO {
    private Long id;
    private Integer cantidad;
    private BigDecimal precio;
    private BigDecimal precioUnitario;
    private BigDecimal subtotal;
    private LocalDateTime log_create;
    private LocalDateTime log_update;
}
