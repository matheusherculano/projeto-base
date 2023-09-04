package com.exemplo.projetobase.service.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.exemplo.projetobase.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService{
	
	private BCryptPasswordEncoder passowrdEncoder() {
		return new BCryptPasswordEncoder();
	}

}
