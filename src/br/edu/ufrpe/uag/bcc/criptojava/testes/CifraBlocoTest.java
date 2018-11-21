package br.edu.ufrpe.uag.bcc.criptojava.testes;

import static org.junit.Assert.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.SecretKey;

import org.junit.Test;

import br.edu.ufrpe.uag.bcc.criptojava.model.Cliente;
import br.edu.ufrpe.uag.bcc.criptojava.model.factory.ClienteFactory;
import br.edu.ufrpe.uag.bcc.criptojava.util.AESCBC;

public class CifraBlocoTest {

	@Test
	public void testeCifraBloco() throws NoSuchAlgorithmException, BadPaddingException {
		Cliente c1 = ClienteFactory.create();
		SecretKey chave = AESCBC.gerarChave();
		String textoCifrado = AESCBC.encriptar(c1, chave);
		Cliente c2 = (Cliente) AESCBC.decriptar(textoCifrado, chave);
		
		assertEquals(c1, c2);
	}
	
	@Test
	public void testeCifraBlocoPBE() throws NoSuchAlgorithmException, InvalidKeySpecException, BadPaddingException{
		Cliente c1 = ClienteFactory.create();
		SecretKey chave = AESCBC.gerarChave("senha");
		String textoCifrado = AESCBC.encriptar(c1, chave);
		SecretKey chave2 = AESCBC.gerarChave("senha");
		Cliente c2 = (Cliente) AESCBC.decriptar(textoCifrado, chave2);
		
		assertEquals(c1, c2);
	}
	
	@Test
	public void testeCifraBlocoPBEErro() throws NoSuchAlgorithmException {
			try {
				Cliente c1 = ClienteFactory.create();
				SecretKey chave = AESCBC.gerarChave("senha");
				String textoCifrado = AESCBC.encriptar(c1, chave);
				SecretKey chave2 = AESCBC.gerarChave("senha1");
				Cliente c2 = (Cliente) AESCBC.decriptar(textoCifrado, chave2);
				assertEquals(c1, c2);
				assertTrue(false);
			} catch (BadPaddingException e) {
				assertTrue(true);
			}
	}
	
}
