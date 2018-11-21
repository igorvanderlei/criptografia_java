package br.edu.ufrpe.uag.bcc.criptojava.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash {
	public static String hash(Object o) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			digest.update(Conversor.objetoParaByteArray(o));
			byte [] h = digest.digest();
			return Conversor.byteArrayParaString(h);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static boolean verify(Object o, String h) {
		String h2 = hash(o);
		return h2.equals(h);
	}

}
