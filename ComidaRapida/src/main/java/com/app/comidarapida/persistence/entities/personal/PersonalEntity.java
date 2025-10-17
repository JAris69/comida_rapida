package com.app.comidarapida.persistence.entities.personal;

import com.app.comidarapida.enums.Estado;
import com.app.comidarapida.enums.EstadoLog;
import com.app.comidarapida.persistence.entities.users.UsuarioEntity;
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
@Table(name = "personal")
public class PersonalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //datos personales
    private String nombre_completo;
    private Integer nro_identidad;
    private String cargo;
    private String direccion;
    private String zona;
    private Integer telefono_movil;
    private Integer telefono_fijo;
    private String nivel_educacion;
    private Integer nro_hijos;
    private LocalDate fecha_nacimiento;
    private BigDecimal salario_bruto;
    private LocalDate periodo_pago;
    private LocalDate fecha_ingreso_emp;//inicio de contrato
    private LocalDate fecha_retiro_emp;//finalizacion de contrato
    private String cuenta_bancaria;
    private String foto_url;
    private String cat_conduccion;
    //private Boolean libretaMilitar;

    //experiencia laboral
    private String empresa;
    private String cargo_emp;
    private String tiempo;
    private String nombre_jefe;
    private LocalDate fecha_retiro;

    //referencia personal
    private String nombre_ref;
    private String tel_movil_ref;

    @Enumerated(EnumType.STRING)
    private Estado estado;

    @Enumerated(EnumType.STRING)
    private EstadoLog estado_log;


    private LocalDateTime log_create;
    private LocalDateTime log_update;
    private Long usulog_create;
    private Long usulog_update;

    /*@OneToMany(mappedBy = "personal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GaranteEntity> garantes;

    @OneToMany(mappedBy = "personal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ContratacionEntity> contracion;

    @OneToMany(mappedBy = "conductor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ComprasEntity> compras_cond;

    @OneToMany(mappedBy = "responsable", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ComprasEntity> compras_resp;

     */

    @OneToOne
    @JoinColumn(name = "usuario_id") // FK hacia la tabla usuarios
    private UsuarioEntity usuario;
}
