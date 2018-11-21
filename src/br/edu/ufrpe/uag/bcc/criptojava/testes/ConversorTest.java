package br.edu.ufrpe.uag.bcc.criptojava.testes;

import static org.junit.Assert.*;

import org.junit.Test;

import br.edu.ufrpe.uag.bcc.criptojava.model.Cliente;
import br.edu.ufrpe.uag.bcc.criptojava.model.factory.ClienteFactory;
import br.edu.ufrpe.uag.bcc.criptojava.util.Conversor;

public class ConversorTest {

	@Test
	public void testConversorByteArray() {
		Cliente c1 = ClienteFactory.create();
		byte[] c1ByteArray = Conversor.objetoParaByteArray(c1);
		Cliente c2 = (Cliente) Conversor.byteArrayParaObjeto(c1ByteArray);
		assertEquals(c1, c2);
	}
	
	@Test
	public void testConversorString() {
		Cliente c1 = ClienteFactory.create();
		byte[] c1ByteArray = Conversor.objetoParaByteArray(c1);
		String c1String = Conversor.byteArrayParaString(c1ByteArray);
		byte [] c2ByteArray = Conversor.stringParaByteArray(c1String);
		Cliente c2 = (Cliente) Conversor.byteArrayParaObjeto(c2ByteArray);
		assertEquals(c1, c2);
	}
	
}
