package br.edu.ufrpe.uag.bcc.criptojava.model.factory;

import java.util.Locale;

import com.github.javafaker.Faker;

import br.edu.ufrpe.uag.bcc.criptojava.model.Cliente;

public class ClienteFactory {
	
	public static Cliente create() {
		Faker faker = new Faker(new Locale("pt-BR"));
		Cliente c = new Cliente();
		c.setId(faker.number().numberBetween(1, 100));
		c.setNome(faker.name().fullName());
		c.setEndereco(faker.address().fullAddress());
		return c;
	}

}
