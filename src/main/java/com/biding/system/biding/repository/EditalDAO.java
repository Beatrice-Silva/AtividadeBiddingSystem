/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.biding.system.biding.repository;

import com.biding.system.biding.model.EditalDTO;
import com.biding.system.biding.model.EditalDTO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Aluno
 */

@Repository
public class EditalDAO {
    
    public int criar(EditalDTO edital){
        try{
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = null;
            stmt = conn.prepareStatement("INSERT INTO editais (titulo, descricao, data_fechamento, status) VALUES (?,?,?,?)");
            
            stmt.setString(1, edital.getTitulo());
            stmt.setString(2, edital.getDescricao());
            stmt.setDate(3, edital.getData_fechamento());
            stmt.setString(4, edital.getStatus());
            
            
            return stmt.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return 0;
    }
    
    public List<EditalDTO> listarEditais(){
        List<EditalDTO> editais = new ArrayList();
        try{
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = null;
            ResultSet rs =null;
            
            stmt = conn.prepareStatement("SELECT * FROM editais;");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                EditalDTO edital = new EditalDTO();
                
                edital.setId(rs.getLong("id"));
                edital.setTitulo(rs.getString("titulo"));
                edital.setDescricao(rs.getString("descricao"));
                edital.setData_fechamento(rs.getDate("data_fechamento"));
                edital.setStatus(rs.getString("status"));
                
                editais.add(edital);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return editais;   
    }
    
       public EditalDTO getById(Long id){
              EditalDTO edital = new EditalDTO();
              try{
                    Connection conn = Conexao.conectar();
                    PreparedStatement stmt = null;
                    ResultSet rs =null;
                    
                             
                    stmt = conn.prepareStatement("SELECT * FROM editais WHERE id = ?;");
                    
                    stmt.setLong(1, id);
                    rs = stmt.executeQuery();
            
              if(rs.next()){
                edital.setId(rs.getLong("id"));
                edital.setData_fechamento(rs.getDate("data_fechamento"));
                edital.setStatus(rs.getString("status"));
                  
              }
                    
        }catch(SQLException e){
            e.printStackTrace();
        }
              return edital;
       }
    
   
}
