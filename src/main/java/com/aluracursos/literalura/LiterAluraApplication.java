package com.aluracursos.literalura;

import com.aluracursos.literalura.main.Main;
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

	@Override
	public void run(String[] args) {
		Main principal = new Main();
		principal.showMenu();
	}
}
