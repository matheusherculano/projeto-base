package com.exemplo.projetobase.service;

import com.exemplo.projetobase.dto.UsuarioDTO;
import com.exemplo.projetobase.model.Usuario;

public interface UsuarioService {

	 void cadastrarUsuarioMaster(UsuarioDTO dto);
	 
	 Usuario getUsuarioByLogin(String login);
	 
	 void atualizarDataHoraUltimoAcesso(Usuario usuario);
}
