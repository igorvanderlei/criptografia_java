package br.edu.ufrpe.uag.bcc.criptojava.util;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class RSA {

	public static KeyPair gerarChave() throws NoSuchAlgorithmException {
		 final int keySize = 2048;
	     KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
	     keyPairGenerator.initialize(keySize);      
	     return keyPairGenerator.genKeyPair();
	}
	
	public static String encriptar(Object o, PublicKey chave) {
		byte [] textoPleno = Conversor.objetoParaByteArray(o);
		byte [] textoCifrado = encrypt(textoPleno, chave);
		return Conversor.byteArrayParaString(textoCifrado);
	}
	
	public static Object decriptar(String textoCifrado, PrivateKey chave)  {
		byte [] b = Conversor.stringParaByteArray(textoCifrado);
		byte[] textoPleno = decrypt(b, chave);
		return Conversor.byteArrayParaObjeto(textoPleno);
	}
	
	public static byte[] encrypt(byte [] message, PublicKey publicKey) {
        Cipher cipher;
		try {
			cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);  
	        return cipher.doFinal(message);  
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}  
		return null;
    }
	
	
	
	public static byte[] decrypt(byte [] encrypted, PrivateKey privateKey){
        Cipher cipher;
		try {
			cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
	        cipher.init(Cipher.DECRYPT_MODE, privateKey);
	        return cipher.doFinal(encrypted);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
    }
	
	
	public static String assinar(Object o, PrivateKey privateKey) {
		byte [] mensagem = Conversor.objetoParaByteArray(o);
		byte [] assinatura = sign(mensagem, privateKey);
		return Conversor.byteArrayParaString(assinatura);
	}
	
	public static boolean verificarAssinatura(Object o, String assinatura, PublicKey publicKey) {
		byte [] mensagem = Conversor.objetoParaByteArray(o);
		byte [] sigBytes = Conversor.stringParaByteArray(assinatura);
		return verify(mensagem, sigBytes, publicKey);
	}
	
	
	public static byte[] sign(byte [] message, PrivateKey privateKey) {
		Signature signature;
		try {
			signature = Signature.getInstance("SHA1withRSA");
			signature.initSign(privateKey);
			signature.update(message);
	        byte[] sigBytes = signature.sign();
	        return sigBytes;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (SignatureException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static boolean verify(byte [] message, byte [] sigBytes, PublicKey publicKey) {
		Signature signature;
		try {
			signature = Signature.getInstance("SHA1withRSA");
			signature.initVerify(publicKey);
			signature.update(message);
			return signature.verify(sigBytes);
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SignatureException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	
}
