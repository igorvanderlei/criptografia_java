package br.edu.ufrpe.uag.bcc.criptojava.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;


public class CertificadoDigital {

	
	public static X509Certificate abrirCertificado(String arquivo) throws FileNotFoundException, CertificateException {
		CertificateFactory factory;
		factory = CertificateFactory.getInstance("X509");
		FileInputStream in = new FileInputStream(new File(arquivo));
		X509Certificate cert= (X509Certificate) factory.generateCertificate(in);
		return cert;
	}
	
	public static PrivateKey abrirChavePrivada(String arquivo, String password) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
	     String encrypted = new String(Files.readAllBytes(Paths.get(arquivo)));
	     encrypted = encrypted.replace("-----BEGIN PRIVATE KEY-----", "");  
	     encrypted = encrypted.replace("-----END PRIVATE KEY-----", "");
	     encrypted = encrypted.replace("\n", "");
	     byte [] enc = Base64.getDecoder().decode(encrypted.trim());
	     
	     KeyFactory keyFactory = KeyFactory.getInstance("RSA");  
	     PKCS8EncodedKeySpec privSpec = new PKCS8EncodedKeySpec(enc);
	     PrivateKey privateKey = keyFactory.generatePrivate(privSpec); 
	     return privateKey;
	}


}
