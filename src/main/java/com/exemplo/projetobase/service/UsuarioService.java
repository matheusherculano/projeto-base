package com.exemplo.projetobase.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.exemplo.projetobase.dto.UsuarioDTO;
import com.exemplo.projetobase.exceptions.ExceptionPersonalizada;
import com.exemplo.projetobase.model.Usuario;
import com.exemplo.projetobase.repository.UsuarioRepository;

@Service
public class UsuarioService{
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	private BCryptPasswordEncoder passowrdEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	public Usuario getUsuarioByLogin(String login) {
		return usuarioRepository.findByLogin(login);
	}
	
	public void cadastrarUsuario(UsuarioDTO dto) throws ExceptionPersonalizada {
		boolean usuarioExiste = usuarioRepository.findByLogin(dto.getLogin()) == null ? false : true;
		boolean emailExiste = usuarioRepository.findByEmail(dto.getEmail()) == null ? false : true;
		
		if(usuarioExiste) {
//			throw new Exception("Usuário já cadastrado");
			throw new ExceptionPersonalizada(HttpStatus.BAD_REQUEST, "Usuário já cadastrado", "");
		}else if(emailExiste){
//			throw new Exception("Email já cadastrado");
			throw new ExceptionPersonalizada(HttpStatus.BAD_REQUEST, "Email já cadastrado", "");
		}else{
			Usuario usuario = new Usuario(dto);
			usuario.setSenha(passowrdEncoder().encode(usuario.getSenha()));
			usuario.setDataCriacao(LocalDateTime.now());
			
			usuarioRepository.save(usuario);
		}
	}
	
	public void cadastrarUsuarioMaster(UsuarioDTO dto) {
		dto.setSenha(passowrdEncoder().encode(dto.getSenha()));
		dto.setDataCriacao(LocalDateTime.now());
		dto.setEmail(dto.getEmail().toUpperCase());
		dto.setLogin(dto.getLogin().toUpperCase());
		
		usuarioRepository.save(usuarioToModel(dto));
	}
	
	public void atualizarDataHoraUltimoAcesso(Usuario usuario) {
		usuario.setDataUltimoLogin(LocalDateTime.now());
	}
	
	private Usuario usuarioToModel(UsuarioDTO dto) {
		Usuario u = new Usuario();
		
			u.setId(dto.getId());
			u.setNome(dto.getNome());
			u.setLogin(dto.getLogin());
			u.setSenha(dto.getSenha());
			u.setEmail(dto.getEmail());
			u.setDataCriacao(dto.getDataCriacao());
			u.setDataUltimoLogin(dto.getDataUltimoLogin());
//			u.setRoles(dto.getRoles());
			
			return u;
	}

}
