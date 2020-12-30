package com.algaworks.ecommerce.mapeamentoavancado;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.NotaFiscal;
import com.algaworks.ecommerce.model.Pedido;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

public class SalvaArquivosTest extends EntityManagerTest {

    @Test
    public void salvarXmlNota() {
        final Pedido pedido = entityManager.find(Pedido.class, 1);

        final NotaFiscal notaFiscal = new NotaFiscal();
        notaFiscal.setPedido(pedido);
        notaFiscal.setDataEmissao(new Date());
        notaFiscal.setXml(carregarXml());

        entityManager.getTransaction().begin();
        entityManager.persist(notaFiscal);
        entityManager.getTransaction().commit();

        entityManager.clear();

        final NotaFiscal notaFiscalVerificacao = entityManager.find(NotaFiscal.class, 1);
        Assert.assertNotNull(notaFiscalVerificacao);
        Assert.assertNotNull(notaFiscalVerificacao.getXml());
        Assert.assertTrue(notaFiscalVerificacao.getXml().length > 0);

//        try {
//            final OutputStream out = new FileOutputStream(Files.createFile(Paths.get(System.getProperty("user.home") + "/nota-fiscal.xml")).toFile());
//            out.write(notaFiscalVerificacao.getXml());
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
    }

    public byte[] carregarXml() {
        try {
            return SalvaArquivosTest.class.getResourceAsStream("/nota-fiscal.xml").readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
