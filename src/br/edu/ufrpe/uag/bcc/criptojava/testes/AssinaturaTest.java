package br.edu.ufrpe.uag.bcc.criptojava.testes;

import static org.junit.Assert.*;

import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;

import org.junit.Test;

import br.edu.ufrpe.uag.bcc.criptojava.model.Cliente;
import br.edu.ufrpe.uag.bcc.criptojava.model.factory.ClienteFactory;
import br.edu.ufrpe.uag.bcc.criptojava.util.RSA;

public class AssinaturaTest {

	@Test
	public void testeAssinaturaRSA() throws NoSuchAlgorithmException {
		Cliente c1 = ClienteFactory.create();
		KeyPair chave = RSA.gerarChave();
		String assinatura = RSA.assinar(c1, chave.getPrivate());
		assertTrue(RSA.verificarAssinatura(c1, assinatura, chave.getPublic()));
		c1.setId(c1.getId() + 1);
		assertFalse(RSA.verificarAssinatura(c1, assinatura, chave.getPublic()));
	}

}
