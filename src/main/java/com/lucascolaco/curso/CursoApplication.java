package com.lucascolaco.curso;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.lucascolaco.curso.domain.Categoria;
import com.lucascolaco.curso.repositories.CategoriaRepository;

@SpringBootApplication
public class CursoApplication implements CommandLineRunner{ // esse comando vai permitir implementar o metodo auxiliar, para executar alguma acao

	@Autowired // gerar automaticamente
	private CategoriaRepository categoriaRepository; // dependencia de repositorio
	
	
	public static void main(String[] args) {
		SpringApplication.run(CursoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2)); // salvando os objetos no banco de dados
		
		
		

	
}
	
	

}
