package com.exemplo.projetobase.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.exemplo.projetobase.dto.LoginDTO;
import com.exemplo.projetobase.security.LoginService;
import com.exemplo.projetobase.security.UserPrincipal;

@RestController
@RequestMapping("/auth")
public class LoginController {

	@Autowired
	private LoginService loginService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<String> save(@RequestBody LoginDTO dto) {

		String token;
		try {
			token = loginService.login(dto);
			return new ResponseEntity<String>(token, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
	}

	@RequestMapping(value = "/userprincipal", method = RequestMethod.GET)
	public ResponseEntity<UserPrincipal> getRoles() {
			UserPrincipal usuarioContex = loginService.getUserPrincal();
			return new ResponseEntity<UserPrincipal>(usuarioContex, HttpStatus.OK);
	}

}
