package com.exemplo.projetobase.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.exemplo.projetobase.dto.UsuarioDTO;
import com.exemplo.projetobase.model.Usuario;
import com.exemplo.projetobase.repository.UsuarioRepository;
import com.exemplo.projetobase.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService{
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	private BCryptPasswordEncoder passowrdEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	public Usuario getUsuarioByLogin(String login) {
		return usuarioRepository.findByLogin(login);
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
