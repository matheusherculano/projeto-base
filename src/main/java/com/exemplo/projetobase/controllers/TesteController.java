package com.exemplo.projetobase.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.exemplo.projetobase.model.Usuario;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/testes")
public class TesteController {
    @RequestMapping(value = "/start", method = RequestMethod.GET)
    public ResponseEntity testes(HttpServletResponse response) {

        return new ResponseEntity("Deu tudo certo! servidor em pé ", HttpStatus.OK);
    }
}
	