/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.biding.system.biding.service;

import com.biding.system.biding.model.EditalDTO;
import com.biding.system.biding.model.LanceDTO;
import com.biding.system.biding.model.UserDTO;
import com.biding.system.biding.repository.EditalDAO;
import com.biding.system.biding.repository.LanceDAO;
import java.util.Date;
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
     
    @Autowired
    private LanceDAO lanceDAO;
     
    @Autowired
    private EditalDAO editalDAO;
     
    public void registrarLance(Long editalId, LanceDTO lance,String token){
        UserDTO userLogado = tokenService.extrairClaims(token);      
        if(!tokenService.validarToken(token)){
              throw new ResponseStatusException(HttpStatusCode.valueOf(401), "Necessita de conta válida!" );
    }
        
        //Role
        EditalDTO edital = editalDAO.getById(editalId);
    if(!userLogado.getRole().equals("FORNECEDOR")){
        throw new ResponseStatusException(HttpStatusCode.valueOf(403), "É preciso ser fornecedor para cadastrar um lance!" );
       }
    //Edital alvo
    if(edital == null){
        throw new ResponseStatusException(HttpStatusCode.valueOf(403), "Edital correspondente não encontrado!" );
    }
    
    //Edital Aberto
    if(!edital.getStatus().equals("ABERTO")){
        throw new ResponseStatusException(HttpStatusCode.valueOf(403), "Este edital se encontra `ENCERRADO`! " );
    }
    //preenche automaticamente os dados a seguuir
    lance.setId_edital(editalId);
    lance.setId_usuario(userLogado.getId());
    lance.getData_lance(new Date());
    
    lanceDAO.criarLance(lance);
        
    }
    
    
}
