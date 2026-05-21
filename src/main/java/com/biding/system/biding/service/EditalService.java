/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.biding.system.biding.service;

import com.biding.system.biding.model.EditalDTO;
import com.biding.system.biding.model.UserDTO;
import com.biding.system.biding.repository.EditalDAO;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author Aluno
 */
@Service
public class EditalService {
    
    @Autowired
    private EditalDAO repository;
    
    @Autowired
    private TokenService tokenService;
    
    public void criarEdital(EditalDTO edital,String token){
        UserDTO userLogado= tokenService.extrairClaims(token);
        
        if (userLogado.getRole().equals("COMPRADOR")){ 
        String mensagem = "";
        
        if(edital.getTitulo().equals("")){
            mensagem += "Título não preenchido!";
        }
        if(edital.getDescricao().equals("")){
            mensagem += "Descrição não preenchido!";
        }
        if(edital.getData_fechamento() == null){
            mensagem += "Data de fechamento não preenchido!";
        }
        
        if(!mensagem.equals("")){            
            throw new ResponseStatusException(HttpStatusCode.valueOf(400), mensagem );
        }

        edital.setStatus("ABERTO");
        int linhas = repository.criar(edital);
        if(linhas == 0){
            throw new ResponseStatusException(HttpStatusCode.valueOf(500), "Erro ao cadastrra no banco de dados!");
        }
    } else {
        throw new ResponseStatusException(HttpStatusCode.valueOf(403), "Acesso não autorizado! Apenas compradores");
        }
        
    }

        //1 Listar Editais (Não recebeu erro e nem resposta (nulo)(+sem vef de usuario auth))\
        //2 Corrigido
    public List<EditalDTO> listarEditais(String token){
        
        UserDTO userLogado= tokenService.extrairClaims(token);
        String mensagem = "";
        
        if (tokenService.validarToken(token)){ 
        return repository.listarEditais();   
        }else{            
            throw new ResponseStatusException(HttpStatusCode.valueOf(401), "Necessita de conta válida!" );
        //   mensagem += "Token inválido! Permissão falha... "; 
        }
        }  
        
        
                   
    
  

}
    
