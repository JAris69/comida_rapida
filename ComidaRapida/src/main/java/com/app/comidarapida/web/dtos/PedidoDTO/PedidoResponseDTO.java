package com.app.comidarapida.web.dtos.PedidoDTO;

import com.app.comidarapida.enums.EstadoPedido;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class PedidoResponseDTO {
    private Long id;
    private LocalDate fecha;
    //private UsuarioEntity usuario;
    private String cliente;
    private BigDecimal total;
    private EstadoPedido estado;
    private LocalDateTime log_create;
    private LocalDateTime log_update;
}
