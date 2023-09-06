package com.exemplo.projetobase.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.exemplo.projetobase.dto.UsuarioDTO;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	@RequestMapping(value = "/cadastro", method = RequestMethod.POST)
	public ResponseEntity<String> cadastro(@RequestBody UsuarioDTO dto) {

		try {
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

}
