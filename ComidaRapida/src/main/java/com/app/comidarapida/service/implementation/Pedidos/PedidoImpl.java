package com.app.comidarapida.service.implementation.Pedidos;

import com.app.comidarapida.enums.EstadoPedido;
import com.app.comidarapida.persistence.entities.Pedido.PedidoEntity;
import com.app.comidarapida.persistence.repositories.Pedido.PedidoRepository;
import com.app.comidarapida.service.interfaces.Pedido.PedidosInterface;
import com.app.comidarapida.util.mappers.Pedido.PedidoMapper;
import com.app.comidarapida.web.dtos.PedidoDTO.PedidoRequestDTO;
import com.app.comidarapida.web.dtos.PedidoDTO.PedidoResponseDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoImpl implements PedidosInterface {

    private final PedidoRepository pedidoRepository;

    public PedidoImpl(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    public PedidoResponseDTO savePedido(PedidoRequestDTO dto) {
        PedidoEntity entity= PedidoMapper.mapToEntity(dto);
        PedidoEntity save=pedidoRepository.save(entity);
        return PedidoMapper.mapToResponse(save);
    }

    @Override
    public List<PedidoResponseDTO> listaPedidos() {
        return pedidoRepository.findAll()
                .stream()
                .map(PedidoMapper::mapToResponse)
                .toList();
    }

    @Override
    public PedidoResponseDTO cambiarEstado(Long id, EstadoPedido estadoPedido) {
        PedidoEntity pedido=pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));

        pedido.setEstado( estadoPedido);
        pedido.setLog_update(LocalDateTime.now());

        PedidoEntity update=pedidoRepository.save(pedido);
        return PedidoMapper.mapToResponse(update);
    }

    @Override
    public Optional<PedidoResponseDTO> updatePedido(Long id, PedidoRequestDTO dto) {
        return pedidoRepository.findById(id)
                .map( pedido->{

                    pedido.setFecha(dto.getFecha());
                    pedido.setCliente(dto.getCliente());
                    pedido.setTotal(dto.getTotal());
                    pedido.setLog_update(LocalDateTime.now());

                    PedidoEntity update = pedidoRepository.save(pedido);

                    return PedidoMapper.mapToResponse(update);
                });
    }

    @Override
    public Optional<PedidoResponseDTO> getIdPedido(Long id) {
        return pedidoRepository.findById(id)
                .map(PedidoMapper::mapToResponse);
    }
}
