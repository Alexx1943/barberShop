    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author usuario
 */
public class Conexao {
    
    // Método responsável por retornar uma conexão com o banco de dados
    public Connection getConeConnection() throws SQLException{
        
        // Cria uma conexão com o banco PostgreSQL usando os dados fornecidos
        // URL: jdbc:postgresql://localhost:5432/bancobarbearia
        // Usuário: postgres
        // Senha: 123456
        Connection conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/bancobarbearia", "postgres", "123456");

        return conexao;
    }
    
    
}
