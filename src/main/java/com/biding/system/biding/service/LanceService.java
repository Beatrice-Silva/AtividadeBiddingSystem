/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.biding.system.biding.service;

import com.biding.system.biding.model.LanceDTO;
import com.biding.system.biding.model.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author Aluno
 */
@Service
public class LanceService {
    
     @Autowired
    private TokenService tokenService;
       
    public void registrarLance(Long id, LanceDTO lance,String token){
    
        if(tokenService.validarToken(token)){
    UserDTO userLogado = tokenService.extrairClaims(token);       
    
    if(!userLogado.getRole().equals("FORNECEDOR")){
        throw new ResponseStatusException(HttpStatusCode.valueOf(403), "É preciso ser fornecedor para cadastrar um lance!" );
        
       }else if()(!userLogado.getStatus().equals("ABERTO")){
        throw new ResponseStatusException(HttpStatusCode.valueOf(403), "É preciso ser fornecedor para cadastrar um lance!" );
    }else if(){
        throw new ResponseStatusException(HttpStatusCode.valueOf(403), "É preciso ser fornecedor para cadastrar um lance!" );
    }
    } else{
        throw new ResponseStatusException(HttpStatusCode.valueOf(401), "Token inválido!" );
    } 
    

    
            
        
    }
    
    
}
