package br.edu.ufrpe.uag.bcc.criptojava.testes;

import java.security.NoSuchAlgorithmException;

import br.edu.ufrpe.uag.bcc.criptojava.util.AESCBC;
import br.edu.ufrpe.uag.bcc.criptojava.util.Conversor;

public class TesteChave {
	public static void main(String[] args) {
		try {
			Conversor.imprimirByteArray(AESCBC.gerarChave().getEncoded());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

	}
}
