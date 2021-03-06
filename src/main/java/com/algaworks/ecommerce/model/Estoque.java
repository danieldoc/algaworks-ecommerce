package com.algaworks.ecommerce.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "estoque")
public class Estoque extends EntidadeBaseInteger {

    @OneToOne(optional = false)
    @JoinColumn(name = "produto_id", foreignKey = @ForeignKey(name = "fk_estoque_produto"))
    private Produto produto;

    private Integer quantidade;
}
