package com.app.comidarapida.web.dtos.PedidoDTO;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class PedidoRequestDTO {

    private LocalDate fecha;
    //private UsuarioEntity usuario;
    private String cliente;
    private BigDecimal total;
    //private EstadoPedido estado;
}
