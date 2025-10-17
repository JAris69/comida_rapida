package com.app.comidarapida.service.interfaces.DetallePedido;

import com.app.comidarapida.web.dtos.DetallePedidoDTO.DetalleRequestDTO;
import com.app.comidarapida.web.dtos.DetallePedidoDTO.DetalleResponseDTO;

public interface DetallePedidoInterface {
    DetalleResponseDTO saveDetalle(DetalleRequestDTO dto);
}
