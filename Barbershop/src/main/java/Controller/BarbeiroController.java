/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.AgendamentoDAO;
import Model.Agendamento;
import Model.Cliente;
import Model.Servico;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 *
 * @author usuario
 */
public class BarbeiroController {
    

    
    private AgendamentoDAO agendamentoDAO;

    public BarbeiroController(AgendamentoDAO agendamentoDAO) {
        this.agendamentoDAO = agendamentoDAO;
    }
    
     public void salvar(String nomeCliente, String nomeServico, float valor, LocalDate data, LocalTime hora, String observacao) throws SQLException {
         
         Cliente cliente = new Cliente(nomeServico);
         Servico servico = new Servico(0, nomeServico, valor);
         Agendamento agendamento = new Agendamento(0, cliente, servico, valor, data, hora);
         agendamento.setObservacao(observacao);
         agendamentoDAO.salvar(agendamento);
         
     }
     
     public void atualizar(int id, String nomeCliente, String nomeServico, float valor, LocalDate data, LocalTime hora, String observacao) throws SQLException {
         
        Cliente cliente = new Cliente(nomeCliente);
        Servico servico = new Servico(0, nomeServico, valor);
        Agendamento agendamento = new Agendamento(id, cliente, servico, valor, data, hora);
        agendamento.setObservacao(observacao);
        agendamentoDAO.atualizar(agendamento);
   
     }
     
     public void deletar(int id) throws SQLException{
         
         agendamentoDAO.deletar(id);
         
     }
     
     public List<Agendamento> listarTodos() throws SQLException {
        return agendamentoDAO.listarTodos();
        
    }
    
    
    
}
