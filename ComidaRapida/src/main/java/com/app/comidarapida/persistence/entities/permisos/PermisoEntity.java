package com.app.comidarapida.persistence.entities.permisos;

import com.app.comidarapida.enums.Estado;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "permisos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PermisoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "modulo_id", nullable = false)
    private ModuloEntity moduloEntity;

    @ManyToOne
    @JoinColumn(name = "submodulo_id")
    private SubmoduloEntity submoduloEntity;

    @ManyToOne
    @JoinColumn(name = "accion_id")
    private AccionEntity accionEntity;

    @Column(name = "codigo")
    private String codigo;

    @Column(name = "descripcion")
    private String descripcion;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private Estado estado;
}
