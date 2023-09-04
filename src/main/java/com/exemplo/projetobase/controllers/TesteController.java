package com.exemplo.projetobase.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.exemplo.projetobase.dto.UsuarioDTO;
import com.exemplo.projetobase.model.Usuario;
import com.exemplo.projetobase.service.UsuarioService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/testes")
public class TesteController {
	
	@Autowired
	private UsuarioService usuarioService;
	
    @RequestMapping(value = "/start", method = RequestMethod.GET)
    public ResponseEntity testes(HttpServletResponse response) {
    	UsuarioDTO dto = new UsuarioDTO();
    	
    	dto.setNome("Administrador");
    	dto.setLogin("admin");
    	dto.setSenha("1234");
    	dto.setEmail("email@email.com");
    	
    	usuarioService.cadastrarUsuarioMaster(dto);

        return new ResponseEntity("Deu tudo certo! servidor em pé ", HttpStatus.OK);
    }
}
	