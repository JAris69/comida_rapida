package com.app.comidarapida.persistence.entities.Producto;

import com.app.comidarapida.enums.Estado;
import com.app.comidarapida.enums.EstadoLog;
import com.app.comidarapida.persistence.entities.DetallePedido.DetallePedidoEntity;
import com.app.comidarapida.persistence.entities.Inventario.InventarioEntity;
import com.app.comidarapida.persistence.entities.Pedido.PedidoEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "productos")
public class ProductoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(precision = 10, scale = 2)
    private BigDecimal precio;

    @Column(length = 50)
    private String categoria;

    @Enumerated(EnumType.STRING)
    private Estado estado;

    @Column(name = "imagen_url", length = 255)
    private String imagenUrl;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetallePedidoEntity> producto;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InventarioEntity> invetario;
    private LocalDateTime log_create;
    private LocalDateTime log_update;
}
