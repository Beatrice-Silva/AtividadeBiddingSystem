/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.biding.system.biding.controller;


import com.biding.system.biding.model.EditalDTO;
import com.biding.system.biding.model.LanceDTO;
import com.biding.system.biding.service.EditalService;
import com.biding.system.biding.service.LanceService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Aluno
 */
@RestController
@RequestMapping("/api/editais")
public class EditalController {
    
    @Autowired
    private EditalService service;
    
    @Autowired
    private LanceService service;
    
    @PostMapping
    public String cadastrarEdital(
            @RequestHeader("Authorization") String auth,
            @RequestBody EditalDTO edital
    ){
        String token = auth.replace("Bearer ", "");
        service.criarEdital(edital, token);      
        return "Edital cadastrado com sucesso!";
    }
    
    @GetMapping
    public List<EditalDTO> listarEditais( 
            @RequestHeader("Authorization") String auth
    ){
        String token = auth.replace("Bearer ", "");
        List<EditalDTO> lista = service.listarEditais(token));
        return lista;
    }
    
    @PostMapping("{id}/lances")
    public String registrarLance(
    @RequestHeader("Authorization") String auth,
    @RequestBody LanceDTO lance,
    @PathVariable Long id
    ){
        String token = auth.replace("Bearer", "");
        
        service.registrarLance(id, lance, token);
        return "Lance registrado com sucesso!";
    }
    
    
    /*
    @PostMapping("/api/editais{id}/lances")
    public String novoLance(
            @RequestHeader() String auth,
            @RequestBody LanceDTO lance
    ){
        
        
    }
    */
}
