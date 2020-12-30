package com.algaworks.ecommerce.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@DiscriminatorValue("cartao")
public class PagamentoCartao extends Pagamento {

    @Column(name = "numero_cartao")
    private String numeroCartao;
}
