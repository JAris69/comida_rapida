package com.app.comidarapida.persistence.entities.permisos;

import com.app.comidarapida.enums.Estado;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "submodulos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubmoduloEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "modulo_id")
    private ModuloEntity moduloEntity;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private Estado estado;

}
