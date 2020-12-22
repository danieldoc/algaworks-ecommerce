package com.algaworks.ecommerce.listener;

import javax.persistence.PostLoad;

public class GenericoListener {

    @PostLoad
    public void logCarregamento(Object o) {
        System.out.println("Entidade " + o.getClass().getSimpleName() + " foi carregada.");
    }
}
