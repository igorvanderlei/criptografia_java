package br.edu.ufrpe.uag.bcc.criptojava.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Base64;

public class Conversor {
	public static byte[] objetoParaByteArray(Object o) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(o);
			byte[] objeto = baos.toByteArray();
			oos.close();
			return objeto;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String byteArrayParaString(byte [] b) {
		String str = Base64.getEncoder().encodeToString(b);
		return str;
	}
	
	public static byte[] stringParaByteArray(String str) {
		byte []  b = Base64.getDecoder().decode(str);
		return b;
	}
	
	public static Object byteArrayParaObjeto(byte[] b) {
		try {
			ByteArrayInputStream bais = new ByteArrayInputStream(b);
			ObjectInputStream ois = new ObjectInputStream(bais);
			return  ois.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
		
	}

	public static void imprimirByteArray(byte [] arr) {
		for(byte b: arr) {
			System.out.print(b);
			System.out.print(" ");
		}
		System.out.println("\n");
	}
}
