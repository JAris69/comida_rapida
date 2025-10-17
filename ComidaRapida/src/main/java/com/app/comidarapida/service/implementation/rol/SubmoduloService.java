package com.app.comidarapida.service.implementation.rol;

import com.app.comidarapida.persistence.entities.permisos.PermisoEntity;
import com.app.comidarapida.persistence.repositories.permisos.PermisoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubmoduloService {

    private final PermisoRepository permisoRepository;

    public SubmoduloService(PermisoRepository permisoRepository) {
        this.permisoRepository = permisoRepository;
    }

    public List<PermisoDTO> listarPermisosPorSubmodulo(Integer submoduloId) {
        List<PermisoEntity> permisoEntities = permisoRepository.findBySubmoduloEntity_Id(submoduloId);

        return permisoEntities.stream().map(p -> PermisoDTO.builder()
                .id(p.getId())
                .moduloId(p.getModuloEntity().getId())
                .moduloNombre(p.getModuloEntity().getNombre())
                .submoduloId(p.getSubmoduloEntity() != null ? p.getSubmoduloEntity().getId() : null)
                .submoduloNombre(p.getSubmoduloEntity() != null ? p.getSubmoduloEntity().getNombre() : null)
                .accionId(p.getAccionEntity() != null ? p.getAccionEntity().getId() : null)
                .accionNombre(p.getAccionEntity() != null ? p.getAccionEntity().getNombre() : null)
                .codigo(p.getCodigo())
                .descripcion(p.getDescripcion())
                .build()).toList();
    }
}
