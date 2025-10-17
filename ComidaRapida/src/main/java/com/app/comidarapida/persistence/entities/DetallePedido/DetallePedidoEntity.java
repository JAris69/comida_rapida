package com.app.comidarapida.persistence.entities.DetallePedido;

import com.app.comidarapida.persistence.entities.Pedido.PedidoEntity;
import com.app.comidarapida.persistence.entities.Producto.ProductoEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "detalle_pedido")
public class DetallePedidoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pedido")
    private PedidoEntity pedido;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_producto")
    private ProductoEntity producto;

    private Integer cantidad;

    @Column(name = "precio_unitario", precision = 10, scale = 2)
    private BigDecimal precioUnitario;

    private LocalDateTime log_create;
    private LocalDateTime log_update;

    @Column(precision = 10, scale = 2)
    private BigDecimal subtotal;
}
