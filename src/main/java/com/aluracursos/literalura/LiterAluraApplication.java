package com.aluracursos.literalura;

import com.aluracursos.literalura.main.Main;
import com.aluracursos.literalura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiterAluraApplication implements CommandLineRunner
{

	public static void main(String[] args)
	{
		SpringApplication.run(LiterAluraApplication.class, args);
	}

	@Autowired
	private LibroRepository repository;
	public void run(String[] args) {
		Main principal = new Main(repository);
		principal.showMenu();
	}
}
