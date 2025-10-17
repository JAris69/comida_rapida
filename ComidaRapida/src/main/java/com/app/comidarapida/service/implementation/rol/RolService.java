package com.app.comidarapida.service.implementation.rol;

import com.app.comidarapida.enums.Estado;
import com.app.comidarapida.persistence.entities.permisos.PermisoEntity;
import com.app.comidarapida.persistence.entities.rol.RolEntity;
import com.app.comidarapida.persistence.entities.rol.RolPermisoEntity;
import com.app.comidarapida.persistence.repositories.permisos.PermisoRepository;
import com.app.comidarapida.persistence.repositories.rol.RolPermisoRepository;
import com.app.comidarapida.persistence.repositories.rol.RoleRepository;
import com.app.comidarapida.web.dtos.roles.PermisoDTO;
import com.app.comidarapida.web.dtos.roles.RolRequestDTO;
import com.app.comidarapida.web.dtos.roles.RolResponseDTO;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RolService {

    private final RoleRepository roleRepository;
    private final PermisoRepository permisoRepository;
    private final RolPermisoRepository rolPermisoRepository;

    public RolService(RoleRepository roleRepository,
                      PermisoRepository permisoRepository,
                      RolPermisoRepository rolPermisoRepository) {
        this.roleRepository = roleRepository;
        this.permisoRepository = permisoRepository;
        this.rolPermisoRepository = rolPermisoRepository;
    }

    @Transactional
    public RolResponseDTO crearRol(RolRequestDTO request) {
        RolEntity rolEntity = RolEntity.builder()
                .nombre(request.getNombre())
                .descripcion(request.getDescripcion())
                .estado(Estado.ACTIVO)
                .build();
        roleRepository.save(rolEntity);

        if (request.getPermisosIds() != null && !request.getPermisosIds().isEmpty()) {
            List<PermisoEntity> permisoEntities = permisoRepository.findAllById(request.getPermisosIds());
            List<RolPermisoEntity> rolPermisoEntities = permisoEntities.stream().map(permiso -> RolPermisoEntity.builder()
                    .rol(rolEntity)
                    .permiso(permiso)
                    .estado(Estado.ACTIVO)
                    .fechaCreacion(LocalDateTime.now())
                    .build()
            ).toList();
            rolPermisoRepository.saveAll(rolPermisoEntities);
        }

        return mapToResponse(rolEntity);
    }


    @Transactional
    public RolResponseDTO actualizarRol(Integer id, RolRequestDTO request) {
        RolEntity rolEntity = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

        rolEntity.setNombre(request.getNombre());
        rolEntity.setDescripcion(request.getDescripcion());
        roleRepository.save(rolEntity);

        // Obtener los permisos actuales del rol
        List<RolPermisoEntity> actuales = rolPermisoRepository.findByRol(rolEntity);
        List<Integer> nuevosIds = request.getPermisosIds() != null ? request.getPermisosIds() : List.of();

        // Eliminar los permisos que ya no están
        List<RolPermisoEntity> aEliminar = actuales.stream()
                .filter(rp -> !nuevosIds.contains(rp.getPermiso().getId()))
                .toList();
        rolPermisoRepository.deleteAll(aEliminar);

        // Agregar permisos nuevos
        List<Integer> actualesIds = actuales.stream()
                .map(rp -> rp.getPermiso().getId())
                .toList();
        List<Integer> idsParaAgregar = nuevosIds.stream()
                .filter(idPermiso -> !actualesIds.contains(idPermiso))
                .toList();

        if (!idsParaAgregar.isEmpty()) {
            List<PermisoEntity> permisosParaAgregar = permisoRepository.findAllById(idsParaAgregar);
            List<RolPermisoEntity> nuevos = permisosParaAgregar.stream().map(permiso -> RolPermisoEntity.builder()
                    .rol(rolEntity)
                    .permiso(permiso)
                    .estado(Estado.ACTIVO)
                    .fechaCreacion(LocalDateTime.now())
                    .build()).toList();
            rolPermisoRepository.saveAll(nuevos);
        }

        return mapToResponse(rolEntity);
    }



    public List<RolResponseDTO> listarRoles() {
        return roleRepository.findAll().stream().map(rol -> {
            List<PermisoDTO> permisos = rolPermisoRepository.findByRol(rol).stream().map(rp -> {
                PermisoEntity p = rp.getPermiso();
                return PermisoDTO.builder()
                        .id(p.getId())
                        .moduloId(p.getModuloEntity().getId())
                        .moduloNombre(p.getModuloEntity().getNombre())
                        .submoduloId(p.getSubmoduloEntity() != null ? p.getSubmoduloEntity().getId() : null)
                        .submoduloNombre(p.getSubmoduloEntity() != null ? p.getSubmoduloEntity().getNombre() : null)
                        .accionId(p.getAccionEntity() != null ? p.getAccionEntity().getId() : null)
                        .accionNombre(p.getAccionEntity() != null ? p.getAccionEntity().getNombre() : null)
                        .codigo(p.getCodigo())
                        .descripcion(p.getDescripcion())
                        .build();
            }).toList();

            return RolResponseDTO.builder()
                    .id(rol.getId())
                    .nombre(rol.getNombre())
                    .descripcion(rol.getDescripcion())
                    .estado(rol.getEstado().name())
                    .permisos(permisos)
                    .build();
        }).toList();
    }

    public RolResponseDTO obtenerRolPorId(Integer id) {
        RolEntity rolEntity = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

        List<PermisoDTO> permisos = rolEntity.getPermisos().stream()
                .map(rp -> {
                    PermisoEntity p = rp.getPermiso();
                    return PermisoDTO.builder()
                            .id(p.getId())
                            .moduloId(p.getModuloEntity().getId())
                            .moduloNombre(p.getModuloEntity().getNombre())
                            .submoduloId(p.getSubmoduloEntity() != null ? p.getSubmoduloEntity().getId() : null)
                            .submoduloNombre(p.getSubmoduloEntity() != null ? p.getSubmoduloEntity().getNombre() : null)
                            .accionId(p.getAccionEntity() != null ? p.getAccionEntity().getId() : null)
                            .accionNombre(p.getAccionEntity() != null ? p.getAccionEntity().getNombre() : null)
                            .codigo(p.getCodigo())
                            .descripcion(p.getDescripcion())
                            .build();
                }).toList();

        return RolResponseDTO.builder()
                .id(rolEntity.getId())
                .nombre(rolEntity.getNombre())
                .descripcion(rolEntity.getDescripcion())
                .permisos(permisos)
                .build();
    }


    private RolResponseDTO mapToResponse(RolEntity rolEntity) {
        List<PermisoDTO> permisos = rolPermisoRepository.findByRol(rolEntity).stream()
                .map(rp -> {
                    PermisoEntity p = rp.getPermiso();
                    return PermisoDTO.builder()
                            .id(p.getId())
                            .moduloId(p.getModuloEntity().getId())
                            .moduloNombre(p.getModuloEntity().getNombre())
                            .submoduloId(p.getSubmoduloEntity() != null ? p.getSubmoduloEntity().getId() : null)
                            .submoduloNombre(p.getSubmoduloEntity() != null ? p.getSubmoduloEntity().getNombre() : null)
                            .accionId(p.getAccionEntity() != null ? p.getAccionEntity().getId() : null)
                            .accionNombre(p.getAccionEntity() != null ? p.getAccionEntity().getNombre() : null)
                            .codigo(p.getCodigo())
                            .descripcion(p.getDescripcion())
                            .build();
                }).toList();

        return RolResponseDTO.builder()
                .id(rolEntity.getId())
                .nombre(rolEntity.getNombre())
                .descripcion(rolEntity.getDescripcion())
                .estado(rolEntity.getEstado().name())
                .permisos(permisos)
                .build();
    }

}
