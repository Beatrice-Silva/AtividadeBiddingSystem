/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.biding.system.biding.service;

import com.biding.system.biding.model.EditalCriarDTO;
import com.biding.system.biding.repository.EditalDAO;
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
    
    public void criar(EditalCriarDTO edital){
        String mensagem = "";
        
        if(edital.getTitulo().equals("")){
            mensagem = "Título não preenchido";
        }else if(edital.getDescricao().equals("")){
            mensagem = "Descrição não preenchido";
        }else if(edital.getData_fechamento().equals("")){
            mensagem = "Data de fechamento não preenchido";
        }else if(edital.getStatus().equals("")){
            mensagem = "Status não preenchido";
        }
              
        if(!mensagem.equals("")){
            throw new ResponseStatusException(HttpStatusCode.valueOf(400), mensagem );
        }
        
        //COMPRADOR
            repository.criar(edital);
        
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    



}
    
