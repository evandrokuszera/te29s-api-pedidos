package br.edu.utfpr.api_pedidos.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String customerName;
    private String status;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    List<ItemPedido> items;
}
