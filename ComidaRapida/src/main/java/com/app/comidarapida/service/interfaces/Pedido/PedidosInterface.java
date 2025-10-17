package com.app.comidarapida.service.interfaces.Pedido;

import com.app.comidarapida.enums.EstadoPedido;
import com.app.comidarapida.web.dtos.PedidoDTO.PedidoRequestDTO;
import com.app.comidarapida.web.dtos.PedidoDTO.PedidoResponseDTO;

import java.util.List;
import java.util.Optional;

public interface PedidosInterface {
    PedidoResponseDTO savePedido(PedidoRequestDTO dto);
    List<PedidoResponseDTO> listaPedidos();
    PedidoResponseDTO cambiarEstado(Long id, EstadoPedido estadoPedido);
    Optional<PedidoResponseDTO> updatePedido(Long id, PedidoRequestDTO dto);
    Optional<PedidoResponseDTO> getIdPedido(Long id);

}
