package com.exemplo.projetobase.security;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.exemplo.projetobase.dto.LoginDTO;
import com.exemplo.projetobase.model.Usuario;
import com.exemplo.projetobase.service.UsuarioService;


@Service
public class TokenService {
	
	private static final Long EXPIRATION_TIME = 240l;
	
	@Autowired
	private UsuarioService usuarioService;
	
	public String login(LoginDTO loginDTO) throws Exception {
		Usuario usuario = usuarioService.getUsuarioByLogin(loginDTO.getLogin());
		if(usuario == null) {
			throw new Exception("Usuário não existente ou senha inválida"); 
		}
		
		Boolean senhaOk = passwordEncoder().matches(loginDTO.getSenha(), usuario.getSenha());
		
		if(senhaOk) {
			usuarioService.atualizarDataHoraUltimoAcesso(usuario);
			return gerarToken(loginDTO);
		}else {
			throw new Exception("Usuário não existente ou senha inválida"); 
		}
		
	}
	
	private BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	private String gerarToken(LoginDTO loginDTO) {
		//subject usuario que esta logando
		return JWT.create().withIssuer("MatProjetoBase").withSubject(loginDTO.getLogin())
				.withExpiresAt(LocalDateTime.now().plusMinutes(EXPIRATION_TIME).toInstant(ZoneOffset.of("-03:00")))
				.sign(Algorithm.HMAC256("!#Xpt119@WiN7$#Cbm135$#@12"));
	}
	
	public String getSubject(String token) {
		return JWT.require(Algorithm.HMAC256("!#Xpt119@WiN7$#Cbm135$#@12"))
				.withIssuer("MatProjetoBase")
				.build().verify(token).getSubject();
	}

}
