package com.app.comidarapida.persistence.entities.Promociones;

import com.app.comidarapida.enums.TipoPromocion;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name ="promociones")
public class PromocionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private TipoPromocion tipo;

    @Column(precision = 10, scale = 2)
    private BigDecimal valor;

    @Column(nullable = false)
    private Boolean activo = true;

    private LocalDateTime log_create;
    private LocalDateTime log_update;
}
