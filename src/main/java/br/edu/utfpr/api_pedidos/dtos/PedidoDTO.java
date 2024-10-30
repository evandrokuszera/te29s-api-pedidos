package br.edu.utfpr.api_pedidos.dtos;

import java.util.List;

public record PedidoDTO(String customerName, List<ItemPedidoDTO> items) {
}
