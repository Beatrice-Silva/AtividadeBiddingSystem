/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.biding.system.biding.repository;

import com.biding.system.biding.model.LanceDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Aluno
 */
@Repository
public class LanceDAO {
    
    public int criarLance(LanceDTO lance){
        try{
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = null;
            stmt = conn.prepareStatement("INSERT INTO lances (valor, data_lance, id_edital, id_usuario) VALUES (?,?,?,?)");
            
            stmt.setDouble(1, lance.getValor());
            stmt.setDate(2, lance.getData_lance());
            stmt.setDouble(3, lance.getId_edital());
            stmt.setDouble(4, lance.getId_usuario());
            
            stmt.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return 0;
    }
    
    
    
}
