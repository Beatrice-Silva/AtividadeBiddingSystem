/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.biding.system.biding.controller;


import com.biding.system.biding.model.EditalCriarDTO;
import com.biding.system.biding.service.EditalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    
    @PostMapping("/criar")
    private String criar(@RequestBody EditalCriarDTO edital){
        service.criar(edital);
        return "Edital criado com sucesso!";
    }
    
    //COMPRADOR
    @GetMapping("/auth/listar")
    private
    
}
