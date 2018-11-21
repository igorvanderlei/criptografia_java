package br.edu.ufrpe.uag.bcc.criptojava.testes;


import java.io.FileNotFoundException;
import java.security.PrivateKey;
import java.security.cert.CertificateException;

import java.security.cert.X509Certificate;

import org.junit.Test;

import br.edu.ufrpe.uag.bcc.criptojava.util.CertificadoDigital;

public class CertificadoTest {

	@Test
	public void testeAbrirCertificado() throws FileNotFoundException, CertificateException {
		X509Certificate certificado = CertificadoDigital.abrirCertificado("certificados//semanacomp.crt");
		System.out.println(certificado);
	}
	
	@Test
	public void testeValidadeCertificadoCA() throws Exception {
		X509Certificate certificado = CertificadoDigital.abrirCertificado("certificados//semanacomp.crt");
		X509Certificate ca = CertificadoDigital.abrirCertificado("certificados//ca.crt");
		certificado.verify(ca.getPublicKey());
	}
	
	@Test
	public void testeAbrirChavePrivada() throws Exception {
		PrivateKey chave = CertificadoDigital.abrirChavePrivada("certificados//semanacomp.pem", "semanacomp");
		System.out.println(chave);
	}
	
	

}
