package com.app.comidarapida.persistence.entities.rol;

import com.app.comidarapida.enums.Estado;
import com.app.comidarapida.persistence.entities.permisos.PermisoEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "rol_permisos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RolPermisoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "rol_id")
    private RolEntity rol;

    @ManyToOne
    @JoinColumn(name = "permiso_id")
    private PermisoEntity permiso;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private Estado estado;

    @Column(name = "created_at")
    private LocalDateTime fechaCreacion = LocalDateTime.now();
}
