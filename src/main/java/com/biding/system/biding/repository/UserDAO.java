/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.biding.system.biding.repository;

import com.biding.system.biding.model.UserDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Aluno
 */
@Repository
public class UserDAO {
    
    public void register(UserDTO user){
        
        try{
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = null;
            stmt = conn.prepareStatement("INSERT INTO usuarios VALUES (nome, email, senha, role,)(?,?,?,?)");
            
            stmt.setString(1, user.getNome());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getSenha());
            stmt.setString(4, user.getRole());
            
            int linhasAfetadas = stmt.executeUpdate();
            if(linhasAfetadas == 0){
                throw new SQLException("Falha! Nenhuma linha foi afetada");
            }
            
        }catch(SQLException e){
            e.printStackTrace();
        }
        
    }
    
    public UserDTO logar( String email, String senha){
    UserDTO user = new UserDTO();
    
        try{
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = null;
            ResultSet rs = null;
            stmt = conn.prepareStatement("SELECT * FROM usuario WHERE email = ? AND senha = ?;");
     
            stmt.setString(1, email);
            stmt.setString(2, senha);
            
            
            rs = stmt.executeQuery();
            if(rs.next()){
                user.setId(rs.getLong("id"));
                user.setNome(rs.getString("nome"));
                user.setEmail(rs.getString("email"));
                user.setRole(rs.getString("role"));
            }
            
                    
        }catch(SQLException e){
            e.printStackTrace();
        }
        return user;
    }
    
    
    
    
    
}
