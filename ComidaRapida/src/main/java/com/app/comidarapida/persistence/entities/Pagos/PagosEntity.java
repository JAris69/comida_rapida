package com.app.comidarapida.persistence.entities.Pagos;

import com.app.comidarapida.enums.MetodoPagos;
import com.app.comidarapida.persistence.entities.Pedido.PedidoEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "pagos")
public class PagosEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 🔗 Relación con Pedido (FK)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pedido")
    private PedidoEntity pedido;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private MetodoPagos metodo;

    @Column(precision = 10, scale = 2)
    private BigDecimal monto;

    private LocalDate fechaPago;
    private LocalDateTime log_create;
    private LocalDateTime log_update;
}
