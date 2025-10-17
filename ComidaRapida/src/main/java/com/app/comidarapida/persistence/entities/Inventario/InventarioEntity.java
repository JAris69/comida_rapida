package com.app.comidarapida.persistence.entities.Inventario;

import com.app.comidarapida.enums.TipoMovimiento;
import com.app.comidarapida.persistence.entities.Producto.ProductoEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "inventario")
public class InventarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 🔗 Relación con Producto
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_producto")
    private ProductoEntity producto;

    @Column(precision = 10, scale = 2)
    private BigDecimal cantidad;

    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = false)
    private TipoMovimiento tipoMovimiento;

    @Column(length = 100)
    private String motivo;

    @Column(name = "fecha_mov", nullable = false)
    private LocalDate fechaMov;

    private LocalDateTime log_create;
    private LocalDateTime log_update;

}
