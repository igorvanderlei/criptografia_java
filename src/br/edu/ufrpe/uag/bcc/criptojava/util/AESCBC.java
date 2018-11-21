package br.edu.ufrpe.uag.bcc.criptojava.util;

import javax.crypto.Cipher;
import java.security.SecureRandom;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESCBC {

	public static SecretKey gerarChave() throws NoSuchAlgorithmException {
		return KeyGenerator.getInstance("AES").generateKey(); 
	}
	
	public static SecretKey gerarChave(String password) throws NoSuchAlgorithmException{
		String h = password;
		for(int i= 0; i < 1000000; i++) {
			h = Hash.hash(h);
		}
		byte [] k = Conversor.stringParaByteArray(h);
		
		SecretKeySpec spec = new SecretKeySpec(k, "AES");
		return spec;
	}
	
	
	public static String encriptar(Object o, SecretKey chave) {
		byte [] textoPleno = Conversor.objetoParaByteArray(o);
		byte [] textoCifrado = encrypt(textoPleno, chave);
		return Conversor.byteArrayParaString(textoCifrado);
	}
	
	public static Object decriptar(String textoCifrado, SecretKey chave) throws BadPaddingException {
		byte [] b = Conversor.stringParaByteArray(textoCifrado);
		byte[] textoPleno = decrypt(b, chave);
		return Conversor.byteArrayParaObjeto(textoPleno);
	}
	
	
	private static Cipher getCipher() throws NoSuchAlgorithmException, NoSuchPaddingException {
		return Cipher.getInstance("AES/CBC/PKCS5Padding");
	}

	
	private static byte[] encrypt(byte input[], SecretKey key) {
		try {
			Cipher cipher = getCipher();
			
			int blockSize = cipher.getBlockSize();
			int inputLength = input.length;
			int outputOffset = blockSize;
			
			byte[] iv = new byte[blockSize];
			
			SecureRandom random = new SecureRandom();
			//gera o iv aleatoriamente
			random.nextBytes(iv);
			
			IvParameterSpec param = new IvParameterSpec(iv);
			
			cipher.init(Cipher.ENCRYPT_MODE, key, param);
			
			int outputLenght = cipher.getOutputSize(inputLength) + blockSize;
			
			byte[] output = new byte[outputLenght];
			
			//Copia o iv para o array de saída
			System.arraycopy(iv, 0 , output, 0, blockSize);
			
			//Processa o restante da saída "pulando" o tamanho do bloco inicial (outputOffset) utilizado para o iv
			cipher.doFinal(input, 0, inputLength, output, outputOffset);
			
			return output;
		
		
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		} catch (ShortBufferException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
		
	}

	private static byte[] decrypt(byte input[], SecretKey key) throws BadPaddingException {
		try {
		
			Cipher cipher = getCipher();
			
			int blockSize = cipher.getBlockSize();
			int inputOffset = blockSize;
			int inputLength = input.length - blockSize;

			
			byte[] iv = new byte[blockSize];
			
			//Copia o iv a partir do array de entrada 
			System.arraycopy(input, 0 , iv, 0, blockSize);
			IvParameterSpec param = new IvParameterSpec(iv);
			
			cipher.init(Cipher.DECRYPT_MODE, key, param);
			
			int outputLenght = cipher.getOutputSize(inputLength);
			
			byte[] output = new byte[outputLenght];
			
			//Processa o restante da saída "pulando" o tamanho do bloco inicial (inputOffset) da entrada, utilizado para o iv
			cipher.doFinal(input, inputOffset, inputLength, output, 0);
			return output;
		
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		} catch (ShortBufferException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} 
		return null;
		
	}	
	
	



}
