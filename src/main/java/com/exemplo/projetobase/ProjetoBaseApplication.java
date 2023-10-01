package com.exemplo.projetobase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import util.Message;

@SpringBootApplication
public class ProjetoBaseApplication {

	public static void main(String[] args) {
	    System.out.println(Message.get("hello"));
		SpringApplication.run(ProjetoBaseApplication.class, args);
	}

}
