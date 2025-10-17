package com.app.comidarapida.persistence.entities.Pedido;

import com.app.comidarapida.enums.EstadoPedido;
import com.app.comidarapida.persistence.entities.DetallePedido.DetallePedidoEntity;
import com.app.comidarapida.persistence.entities.Pagos.PagosEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "pedido")
public class PedidoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate fecha;

    // Relación con la tabla de usuarios (FK → usuarios)
    //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "id_usuario", nullable = false)
    //private UsuarioEntity usuario;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetallePedidoEntity> pedido;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PagosEntity> pagos;

    @Column(length = 100)
    private String cliente;

    @Column(precision = 10, scale = 2)
    private BigDecimal total;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private EstadoPedido estado;
    private LocalDateTime log_create;
    private LocalDateTime log_update;
}
