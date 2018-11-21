package br.edu.ufrpe.uag.bcc.criptojava.testes;

import static org.junit.Assert.*;

import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import org.junit.Test;

import br.edu.ufrpe.uag.bcc.criptojava.model.Cliente;
import br.edu.ufrpe.uag.bcc.criptojava.model.factory.ClienteFactory;
import br.edu.ufrpe.uag.bcc.criptojava.util.RSA;

public class RSATest {

	@Test
	public void testeRSA() throws NoSuchAlgorithmException {
		Cliente c1 = ClienteFactory.create();
		KeyPair chave = RSA.gerarChave();
		String textoCifrado = RSA.encriptar(c1, chave.getPublic());
		Cliente c2 = (Cliente) RSA.decriptar(textoCifrado, chave.getPrivate());
		System.out.println(c2);
		assertEquals(c1, c2);
	}
	

}
