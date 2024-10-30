package br.edu.utfpr.api_pedidos.dtos;

public record ProdutoDTO(
        Long id,
        String description,
        Integer quantity,
        Double price,
        String category
) {}
