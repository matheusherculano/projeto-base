package com.exemplo.projetobase.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.exemplo.projetobase.model.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class UsuarioDTO {

	private Long id;
	private String nome;
	private String login;
	private String senha;
	private String email;
	private LocalDateTime dataCriacao;
	private LocalDateTime dataUltimoLogin;
	private List<Role> roles;
}
