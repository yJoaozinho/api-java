package com.joao.demoparkapi.service;

import com.joao.demoparkapi.entity.Usuario;
import com.joao.demoparkapi.exception.EntityNotFoundExcepiton;
import com.joao.demoparkapi.exception.PasswordInvalidException;
import com.joao.demoparkapi.exception.UsernameUniqueViolationExeption;
import com.joao.demoparkapi.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Transactional
    public Usuario salvar (Usuario usuario){
        try {
            return usuarioRepository.save(usuario);
        }catch (org.springframework.dao.DataIntegrityViolationException ex){
            throw new UsernameUniqueViolationExeption(String.format("Username {%s} ja cadastrado", usuario.getUsername()));
        }

    }

    @Transactional(readOnly = true)
    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundExcepiton(String.format("Usuario id=%s nao encontrado", id))
        );
    }

    @Transactional
    public Usuario editarSenha(Long id, String senhaAtual, String novaSenha, String confirmaSenha){
        if(!novaSenha.equals(confirmaSenha)){
            throw new PasswordInvalidException("As senhas nao estao iguais");
        }
        Usuario user = buscarPorId(id);
        if (!user.getPassword().equals(senhaAtual)){
            throw new PasswordInvalidException("A sua senha esta incorreta");
        }
        user.setPassword(novaSenha);
        return user;
    }

    @Transactional(readOnly = true)
    public List buscarTodosUsuarios() {
        return usuarioRepository.findAll();
    }
}
