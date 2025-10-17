package com.app.comidarapida.util.mappers.Pedido;

import com.app.comidarapida.enums.EstadoPedido;
import com.app.comidarapida.persistence.entities.Pedido.PedidoEntity;
import com.app.comidarapida.web.dtos.PedidoDTO.PedidoRequestDTO;
import com.app.comidarapida.web.dtos.PedidoDTO.PedidoResponseDTO;

import java.time.LocalDateTime;

public class PedidoMapper {

    public static PedidoEntity mapToEntity(PedidoRequestDTO dto){
        PedidoEntity pedido=new PedidoEntity();

        pedido.setFecha(dto.getFecha());
        pedido.setCliente(dto.getCliente());
        pedido.setTotal(dto.getTotal());
        pedido.setEstado(EstadoPedido.EN_PREPARACION);
        pedido.setLog_create(LocalDateTime.now());
        return pedido;
    }

    public static PedidoResponseDTO mapToResponse(PedidoEntity pedido){
        PedidoResponseDTO dto= new PedidoResponseDTO();

        dto.setFecha(pedido.getFecha());
        dto.setCliente(pedido.getCliente());
        dto.setTotal(pedido.getTotal());
        dto.setEstado(pedido.getEstado());
        return dto;
    }
}
