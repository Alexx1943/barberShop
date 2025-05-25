/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.AgendamentoDAO;
import Model.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author usuario
 */
public class AgendamentoController {
    
    
    private AgendamentoDAO agendamentoDAO;

    public AgendamentoController(AgendamentoDAO agendamentoDAO) {
        this.agendamentoDAO = agendamentoDAO;
    }
    
    
    /**
 * Salva um novo agendamento no banco de dados com os dados informados.
 * 
 * @param nome          Nome do cliente.
 * @param nomeServico   Nome do serviço selecionado.
 * @param valor         Valor do serviço.
 * @param data          Data do agendamento.
 * @param hora          Hora do agendamento.
 * @param observacao    Observações adicionais do agendamento.
 * @throws SQLException Caso ocorra erro ao salvar no banco de dados.
 */
    public void salvarAgendamento(String nome, String servicoNome, float valor, LocalDate data, LocalTime hora, String observacao) throws SQLException{
   
         // Cria o objeto Cliente com o nome informado
        Cliente cliente = new Cliente(nome);
        
        // Cria o objeto Agendamento com os dados fornecidos
        Servico servico = new Servico(0, servicoNome, valor);
        
        // Define a observação adicional no agendamento
        Agendamento agendamento = new Agendamento(0, cliente, servico, valor, data, hora);
        
        // Define a observação adicional no agendamento
        agendamento.setObservacao(observacao);
        
        // SALVA o agendamento no banco de dados usando o DAO
        agendamentoDAO.salvar(agendamento);
        
    }
       
}
