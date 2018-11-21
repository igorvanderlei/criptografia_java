package br.edu.ufrpe.uag.bcc.criptojava.testes;

import static org.junit.Assert.*;

import org.junit.Test;
import br.edu.ufrpe.uag.bcc.criptojava.model.Cliente;
import br.edu.ufrpe.uag.bcc.criptojava.model.factory.ClienteFactory;
import br.edu.ufrpe.uag.bcc.criptojava.util.Hash;

public class HashTest {

	@Test
	public void hashTest() {
		Cliente c1 = ClienteFactory.create();
		String hash = Hash.hash(c1);
		assertTrue(Hash.verify(c1, hash));
	}

	@Test
	public void hashFalseTest() {
		Cliente c1 = ClienteFactory.create();
		String hash = Hash.hash(c1);
		c1.setId(c1.getId()+1);
		assertFalse(Hash.verify(c1, hash));
	}
	
	@Test
	public void hashStringTest() {
		String s1 = "teste 1";
	
		String hs1 = Hash.hash(s1);
		assertTrue(Hash.verify("teste 1", hs1));
		assertFalse(Hash.verify("teste  1", hs1));
		
	}
}
