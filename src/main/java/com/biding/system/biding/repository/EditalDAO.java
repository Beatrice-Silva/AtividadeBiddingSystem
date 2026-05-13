/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.biding.system.biding.repository;

import com.biding.system.biding.model.EditalAuthDTO;
import com.biding.system.biding.model.EditalCriarDTO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Aluno
 */

@Repository
public class EditalDAO {
    
    public void criar(EditalCriarDTO edital){
        try{
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = null;
            stmt = conn.prepareStatement("INSERT INTO editais (titulo, descricao, data_fechamento, status) VALUES (?,?,?,?)");
            
            stmt.setString(1, edital.getTitulo());
            stmt.setString(2, edital.getDescricao());
            stmt.setDate(3, (Date) edital.getData_fechamento());
            stmt.setString(4, edital.getStatus());
            
            stmt.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    //Verificar se  role = "COMPRADOR"
    //POST
    
    public class EditalDAO{
        
        public List<EditalAuthDTO> listar(){
        List<> editais = new ArrayList();
            
        }
        
    }
    
    
    
    
    
}
