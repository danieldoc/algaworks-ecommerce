package com.algaworks.ecommerce.model;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ItemPedidoId implements Serializable {

    private Integer pedidoId;

    private Integer produtoId;
}
