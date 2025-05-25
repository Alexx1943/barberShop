/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Model.Agendamento;
import Model.Cliente;
import Model.Servico;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author usuario
 */
public class AgendamentoDAO {
    
    // Conexão com o banco de dados
    private Connection conexao;
    
    // Construtor que recebe a conexão como parâmetro
    public AgendamentoDAO(Connection conexao) {
        this.conexao = conexao;
    }
    
    // Método para salvar um novo agendamento no banco de dados
    public void salvar(Agendamento agendamento){
        
        // Comando SQL para inserir um novo agendamento com os campos relevantes
        String sql = "INSERT INTO agendamento (nome, servico, valor, data, hora, observacao) VALUES (?, ?, ?, ?, ?, ?)";
        
        try(PreparedStatement statement = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            
            // Define os valores nos placeholders da query
            statement.setString(1, agendamento.getCliente().getNome());
            statement.setString(2, agendamento.getServico().getDescricao());
            statement.setDouble(3, agendamento.getValor());
            statement.setDate(4, Date.valueOf(agendamento.getData()));
            statement.setTime(5, Time.valueOf(agendamento.getHora()));
            statement.setString(6, agendamento.getObservacao());
            
            // Executa a inserção no banco
            statement.executeUpdate();
            
            // Obtém o ID gerado automaticamente pelo banco de dados
           try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                agendamento.setId(generatedKeys.getInt(1));
            }
        }
        

            } catch (SQLException e) {
            e.printStackTrace();
        }
    
    }
    
   // Método para listar todos os agendamentos cadastrados    
    public List<Agendamento> listarTodos() throws SQLException {
           List<Agendamento> lista = new ArrayList<>();
           
           // Comando SQL para buscar todos os agendamentos
           String sql = "SELECT * FROM agendamento ORDER BY id";

           try (PreparedStatement statement = conexao.prepareStatement(sql);
           ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
            // Cliente reconstruído com apenas ID e nome (demais dados não estão na tabela agendamento)
            Cliente cliente = new Cliente(0, resultSet.getString("nome"), "", "");

            // Servico reconstruído com apenas descrição e valor lido da tabela
            Servico servico = new Servico(0, resultSet.getString("servico"), resultSet.getFloat("valor"));

            // Monta o agendamento completo
            Agendamento ag = new Agendamento(
                resultSet.getInt("id"),
                cliente,
                servico,
                resultSet.getDouble("valor"),
                resultSet.getDate("data").toLocalDate(),
                resultSet.getTime("hora").toLocalTime()
            );
            ag.setObservacao(resultSet.getString("observacao"));
            lista.add(ag);
        }
    }

    return lista;
}

public void atualizar(Agendamento agendamento) {
    String sql = "UPDATE agendamento SET nome = ?, servico = ?, valor = ?, data = ?, hora = ?, observacao = ? WHERE id = ?";

    try (PreparedStatement statement = conexao.prepareStatement(sql)) {
        statement.setString(1, agendamento.getCliente().getNome());
        statement.setString(2, agendamento.getServico().getDescricao());
        statement.setDouble(3, agendamento.getValor());
        statement.setDate(4, Date.valueOf(agendamento.getData()));
        statement.setTime(5, Time.valueOf(agendamento.getHora()));
        statement.setString(6, agendamento.getObservacao());
        statement.setInt(7, agendamento.getId());

        statement.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

public void deletar(int id) {
    String sql = "DELETE FROM agendamento WHERE id = ?";

    try (PreparedStatement statement = conexao.prepareStatement(sql)) {
        statement.setInt(1, id);
        statement.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
 }



public boolean existeAgendamento(LocalDate data, LocalTime hora) throws SQLException{
    
    String sql = "SELECT COUNT(*) FROM agendamento WHERE data = ? AND hora = ?";
    
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
        stmt.setDate(1, java.sql.Date.valueOf(data));
        stmt.setTime(2, java.sql.Time.valueOf(hora));
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return rs.getInt(1) > 0; // retorna true se houver agendamento já existente
        }
    }
    return false;
    
    
}  
    
}
