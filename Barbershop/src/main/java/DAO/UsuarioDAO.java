/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Usuario;
import View.CadastroView;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class UsuarioDAO {
    
    
    // Conexão com o banco de dados
    private final Connection conexao;
    
    
    // Construtor que recebe a conexão e inicializa o atributo "conexao"
    public UsuarioDAO(Connection conexao) {
        this.conexao = conexao;
    }
    
    
    // Método para inserir um novo usuário no banco de dados, tendo como parametro um objeto Usuario.
    public Usuario insert(Usuario usuario) throws SQLException{
           
        // SQL com placeholders (?) para evitar SQL Injection    
        String sql = "insert into usuario(usuario, senha) values (?,?); ";

        PreparedStatement statement = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        
        statement.setString(1, usuario.getNome());
        statement.setString(2, usuario.getSenha());
        statement.execute();
        
        ResultSet resultSet = statement.getGeneratedKeys();
        if(resultSet.next()){
            int id = resultSet.getInt("id");
            usuario.setId(id);
        }
        return usuario;
           
            
 
    }
    
    public void update(Usuario usuario) throws SQLException{
        
        String  sql = "update usuario set usuario = ?, senha = ? where = ?";
        
        PreparedStatement statement = conexao.prepareStatement(sql);
        statement.setString(1, usuario.getNome());
        statement.setString(2, usuario.getSenha());
        statement.setInt(3, usuario.getId());
        
        statement.execute();
        
        
    }
    
    // Método que decide automaticamente se deve inserir ou atualizar um usuário.
    public void insertUpdate(Usuario usuario) throws SQLException{
        
        // Verifica se o usuário já possui um ID definido (> 0).
        if(usuario.getId() > 0){
            // Se já existir (tem ID), atualiza os dados no banco.
            update(usuario);
        }else{
            // Se não tiver ID (usuário novo), insere no banco de dados.
            insert(usuario);
        }
    }
    
     // Método responsável por excluir um usuário do banco de dados.
    public void delete(Usuario usuario) throws SQLException{
        
        // Comando SQL que deleta o registro da tabela "usuario" com base no ID.
        String sql = "delete from usuario where id = ?";
        
        PreparedStatement statement = conexao.prepareStatement(sql);
        statement.setInt(1, usuario.getId());
        statement.execute();
        
    }
    
    // Método que retorna uma lista com todos os usuários cadastrados no banco de dados.
    public ArrayList<Usuario> verListaUsuarios() throws SQLException{
        
        // Comando SQL que seleciona todos os registros da tabela "usuario".
        String sql = "select * from usuario";
        
        PreparedStatement statement = conexao.prepareStatement(sql);
        
       return pesquisa(statement);
         
         
    }

    private ArrayList<Usuario> pesquisa(PreparedStatement statement) throws SQLException {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        
        statement.execute();
        ResultSet resultSet = statement.getResultSet();
        
        while(resultSet.next()){
            int id = resultSet.getInt("id");
            String usuario = resultSet.getString("usuario");
            String senha = resultSet.getString("senha");
            
            Usuario usuarioDoBancoDeDados = new Usuario(id, usuario, senha);
            usuarios.add(usuarioDoBancoDeDados);
            
        }
        return usuarios;
    }
    
    // Método que busca e retorna um usuário do banco de dados com base no ID fornecido.
    public Usuario verUsuarioPorId(Usuario usuario) throws SQLException {
    

       String sql = "select * from usuario where id =?";
       
        PreparedStatement statement = conexao.prepareStatement(sql);
        statement.setInt(1, usuario.getId());
        
        ArrayList<Usuario> usuarios = pesquisa(statement);
        
        return pesquisa(statement).get(0);
        
        
        
    
    
    }

    // Método que verifica se o usuário e a senha fornecidos existem no banco de dados.
    public Usuario autenticarUsuario(Usuario usuario) throws SQLException {
        
        // Consulta SQL que busca por um usuário com nome e senha iguais aos informados.
        String sql = "select * from usuario  where usuario = ? and senha = ? ";
        
        PreparedStatement statement = conexao.prepareStatement(sql);
        statement.setString(1, usuario.getNome());
        statement.setString(2, usuario.getSenha());
        
        ResultSet resultSet = statement.executeQuery();
        
        if(resultSet.next()){
            Usuario usuarioAutenticado = new Usuario();
            usuarioAutenticado.setId(resultSet.getInt("id"));
            usuarioAutenticado.setNome(resultSet.getString("usuario"));
            usuarioAutenticado.setSenha(resultSet.getString("senha"));
            return usuarioAutenticado;
        }else{
            return null;
        }   
        
    }
    
    

    


    


    
    
    
}   
    
    
    
    

