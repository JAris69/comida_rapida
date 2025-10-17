package com.app.comidarapida.persistence.entities.users;

import com.app.comidarapida.enums.Estado;
import com.app.comidarapida.persistence.entities.rol.RolEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_role")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioRolEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UsuarioEntity usuario;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private RolEntity rol;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Estado estado;

    @Column(name = "created_at")
    private LocalDateTime fechaCreacion;
}
