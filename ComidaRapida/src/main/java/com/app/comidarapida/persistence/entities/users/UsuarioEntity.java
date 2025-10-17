package com.app.comidarapida.persistence.entities.users;

import com.app.comidarapida.enums.Estado;
import com.app.comidarapida.enums.EstadoLog;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "usuarios")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "correo", nullable = false, unique = true)
    private String correo;

    @Column(name = "nombre_usuario", nullable = false, unique = true)
    private String nombreUsuario;

    @Column(name = "contrasena", nullable = false)
    private String contrasena;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private Estado estado;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_log", nullable = false)
    private EstadoLog estadoLog;

    //@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    //private List<UsuarioRolEntity> usuarioRoleEntities;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}
