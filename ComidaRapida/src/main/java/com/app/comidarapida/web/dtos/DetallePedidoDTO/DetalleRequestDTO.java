package com.app.comidarapida.web.dtos.DetallePedidoDTO;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DetalleRequestDTO {
    private Integer cantidad;
    private BigDecimal precio;
    private BigDecimal precioUnitario;
    private BigDecimal subtotal;
    private Long idPedido;
    private Long idProducto;
}
