/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.biding.system.biding.service;

import com.biding.system.biding.model.AuthResponseDTO;
import com.biding.system.biding.model.UserDTO;
import com.biding.system.biding.model.UserRequestDTO;
import com.biding.system.biding.repository.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author Aluno
 */
@Service
public class UserService {
    
    @Autowired
    private UserDAO repository;
    
    @Autowired
    private TokenService tokenService;
    
    public void register(UserDTO user){
        String mensagem = "";
        
        if(user.getNome().equals("")){
            mensagem= "Nome não preenchido";
        }else if(user.getEmail().equals("")){
            mensagem = "Email não preenchido";
        }else if(user.getSenha().equals("")){
            mensagem = "Senha não preenchida";
        }else if(user.getRole().equals("")){
            user.setRole("FORNECEDOR");
        }
        
        if(!mensagem.equals("")){
            throw new ResponseStatusException(HttpStatusCode.valueOf(400), mensagem);
            
        }
        
        repository.register(user);
    }
    
    public String logar(UserRequestDTO user){
        if(user.getEmail() == null || user.getEmail().isEmpty() || user.getSenha() == null || user.getSenha().isEmpty()){
            throw new ResponseStatusException(HttpStatusCode.valueOf(403), "Email e Senha devem ser preenchidos"); 
        }
        
        UserDTO dadosLogado = repository.logar(user.getEmail(), user.getSenha());
        
        if(dadosLogado == null){
            throw new ResponseStatusException(HttpStatusCode.valueOf(401), "Email e Senha incorretos!"); 
        }
        
        String token = tokenService.gerarToken(dadosLogado);
        return new AuthResponseDTO(token, dadosLogado.getRole());
              
    }
    
    
}
