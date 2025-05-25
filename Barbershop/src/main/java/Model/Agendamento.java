package Model;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author usuario
 */
public class Agendamento {
    
    private int id;
    private Cliente cliente;
    private Servico servico;
    private double valor;
    private LocalDate data;
    private LocalTime hora;
    private String observacao;

    public Agendamento(int id, Cliente cliente, Servico servico, double valor, LocalDate data, LocalTime hora) {
        this.id = id;
        this.cliente = cliente;
        this.servico = servico;
        this.valor = valor;
        setData(data); // Usa o método com validação
        this.hora = hora;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        if (data.isBefore(LocalDate.now())) {
            // Aqui você pode escolher lançar uma exceção OU apenas mostrar uma mensagem
            throw new IllegalArgumentException("Data inválida: não é possível agendar para uma data passada.");
            // ou: System.out.println("Data inválida.");
        }
        this.data = data;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}