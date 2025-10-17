package com.app.comidarapida.service.interfaces.Inventario;

import com.app.comidarapida.web.dtos.InventarioDTO.InventarioRequestDTO;
import com.app.comidarapida.web.dtos.InventarioDTO.InventarioResponseDTO;

public interface InventarioInterface {
    InventarioResponseDTO saveInventario(InventarioRequestDTO dto);
}
