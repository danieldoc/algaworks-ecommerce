package com.algaworks.ecommerce.iniciandocomjpa;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import org.junit.Assert;
import org.junit.Test;

public class PrimeiroCrudTest extends EntityManagerTest {

    @Test
    public void inserirRegistro() {
        Cliente cliente = new Cliente();
        cliente.setNome("Mirella Moreira");

        entityManager.getTransaction().begin();
        entityManager.persist(cliente);
        entityManager.getTransaction().commit();

        entityManager.clear();

        final Cliente clienteVerificacao = entityManager.find(Cliente.class, cliente.getId());
        Assert.assertNotNull(clienteVerificacao);
    }

    @Test
    public void buscarPorIdentificador() {
        final Cliente cliente = entityManager.find(Cliente.class, 1);
        Assert.assertNotNull(cliente);
    }

    @Test
    public void atualizarRegistro() {
        Cliente cliente = new Cliente();
        cliente.setNome("Kamilly Brito");

        entityManager.getTransaction().begin();
        cliente = entityManager.merge(cliente);
        entityManager.getTransaction().commit();

        entityManager.clear();

        final Cliente clienteVerificacao = entityManager.find(Cliente.class, cliente.getId());
        Assert.assertNotNull(clienteVerificacao);
        Assert.assertEquals("Kamilly Brito", clienteVerificacao.getNome());
    }

    @Test
    public void removerRegistro() {
        final Cliente cliente = entityManager.find(Cliente.class, 1);

        entityManager.getTransaction().begin();
        entityManager.remove(cliente);
        entityManager.getTransaction().commit();

        final Cliente clienteVerificacao = entityManager.find(Cliente.class, 1);
        Assert.assertNull(clienteVerificacao);
    }
}
