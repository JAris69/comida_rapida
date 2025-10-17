package com.app.comidarapida.service.interfaces.Pagos;

import com.app.comidarapida.web.dtos.PagosDTO.PagosRequestDTO;
import com.app.comidarapida.web.dtos.PagosDTO.PagosResponseDTO;

public interface PagosInterface {
    PagosResponseDTO savePago(PagosRequestDTO dto);
}
