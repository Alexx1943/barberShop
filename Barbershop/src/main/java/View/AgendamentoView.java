/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Controller.AgendamentoController;
import DAO.AgendamentoDAO;
import DAO.Conexao;
import Model.Agendamento;
import Model.Cliente;
import Model.Servico;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;


/**
 *
 * @author usuario
 */
public class AgendamentoView extends javax.swing.JFrame {

    /**
     * Creates new form Agenda
     */
public AgendamentoView() {
    initComponents();
    aplicarMascaras();

    // Limpa os JComboBox para evitar duplicações
    campoTextoServico.removeAllItems();
    campoTextoValor.removeAllItems();

    // Mapa de serviços e valores
    Map<String, String> mapaServicos = new HashMap<>();
    mapaServicos.put("Corte simples", "R$ 40,00");
    mapaServicos.put("Barba", "R$ 30,00");
    mapaServicos.put("Barba e Corte", "R$ 70,00");

    Map<String, String> mapaValores = new HashMap<>();
    for (Map.Entry<String, String> entry : mapaServicos.entrySet()) {
        campoTextoServico.addItem(entry.getKey());
        campoTextoValor.addItem(entry.getValue());
        mapaValores.put(entry.getValue(), entry.getKey()); // valor -> serviço
    }

    final boolean[] atualizandoCampos = {false};

    campoTextoServico.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (atualizandoCampos[0]) return;
            atualizandoCampos[0] = true;

            String servicoSelecionado = (String) campoTextoServico.getSelectedItem();
            String valor = mapaServicos.get(servicoSelecionado);
            if (valor != null) {
                campoTextoValor.setSelectedItem(valor);
            }

            atualizandoCampos[0] = false;
        }
    });

    campoTextoValor.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (atualizandoCampos[0]) return;
            atualizandoCampos[0] = true;

            String valorSelecionado = (String) campoTextoValor.getSelectedItem();
            String servico = mapaValores.get(valorSelecionado);
            if (servico != null) {
                campoTextoServico.setSelectedItem(servico);
            }

            atualizandoCampos[0] = false;
        }
    });
}







    
    private void aplicarMascaras() {
    try {
        // Máscara para Data: dd/MM/yyyy
        MaskFormatter mascaraData = new MaskFormatter("##/##/####");
        mascaraData.setPlaceholderCharacter('_');
        campoTextoData.setFormatterFactory(new DefaultFormatterFactory(mascaraData));

        // Máscara para Hora: HH:mm
        MaskFormatter mascaraHora = new MaskFormatter("##:##");
        mascaraHora.setPlaceholderCharacter('_');
        campoTextoHora.setFormatterFactory(new DefaultFormatterFactory(mascaraHora));

    } catch (ParseException ex) {
        ex.printStackTrace();
    }
}

    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        campoTextoHora = new javax.swing.JFormattedTextField();
        campoTextoData = new javax.swing.JFormattedTextField();
        campoTextoNome = new javax.swing.JTextField();
        campoTextoServico = new javax.swing.JComboBox<>();
        campoTextoValor = new javax.swing.JComboBox<>();
        jButton1AgendarBotao = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        campoTextoObs = new javax.swing.JTextArea();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        campoTextoHora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoTextoHoraActionPerformed(evt);
            }
        });
        getContentPane().add(campoTextoHora, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 440, 540, 40));
        getContentPane().add(campoTextoData, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 350, 540, 40));

        campoTextoNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoTextoNomeActionPerformed(evt);
            }
        });
        getContentPane().add(campoTextoNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 350, 540, 40));

        campoTextoServico.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Corte simples", "Barba  ", "Barba e Corte ", " " }));
        campoTextoServico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoTextoServicoActionPerformed(evt);
            }
        });
        getContentPane().add(campoTextoServico, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 440, 540, 40));

        campoTextoValor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "R$ 40,00", "R$ 30,00", "R$ 70,00", " " }));
        campoTextoValor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoTextoValorActionPerformed(evt);
            }
        });
        getContentPane().add(campoTextoValor, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 530, 540, 40));

        jButton1AgendarBotao.setFont(new java.awt.Font("Cantarell", 0, 24)); // NOI18N
        jButton1AgendarBotao.setText("Agendar");
        jButton1AgendarBotao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1AgendarBotaoActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1AgendarBotao, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 710, 620, 50));

        jButton1.setFont(new java.awt.Font("Cantarell", 0, 24)); // NOI18N
        jButton1.setText("Voltar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 820, 620, 50));

        campoTextoObs.setColumns(20);
        campoTextoObs.setRows(5);
        jScrollPane2.setViewportView(campoTextoObs);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 510, 540, 60));

        jLabel9.setFont(new java.awt.Font("Cantarell", 0, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Hora:");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 440, -1, 40));

        jLabel8.setFont(new java.awt.Font("Cantarell", 0, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Data: ");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 360, -1, 20));

        jLabel7.setFont(new java.awt.Font("Cantarell", 0, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Valor:");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 540, -1, -1));

        jLabel6.setFont(new java.awt.Font("Cantarell", 0, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Observação:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 530, -1, -1));

        jLabel5.setFont(new java.awt.Font("Cantarell", 0, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Serviço:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 450, -1, -1));

        jLabel4.setFont(new java.awt.Font("Cantarell", 0, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Agendamento");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 50, -1, -1));

        jLabel3.setFont(new java.awt.Font("Cantarell", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Nome:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon("/home/usuario/Projeto/NetBeansProjects/Barbershop/src/main/java/View/Imagens/caixa-login.png")); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, -240, 600, 1410));

        jLabel10.setIcon(new javax.swing.ImageIcon("/home/usuario/Projeto/NetBeansProjects/Barbershop/src/main/java/View/Imagens/caixa-login.png")); // NOI18N
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, -240, 600, 1410));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon("/home/usuario/Projeto/NetBeansProjects/Barbershop/src/main/java/View/Imagens/11111.png")); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -240, 1510, 1540));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void campoTextoNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoTextoNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoTextoNomeActionPerformed

    private void campoTextoServicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoTextoServicoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoTextoServicoActionPerformed

    private void jButton1AgendarBotaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1AgendarBotaoActionPerformed
   
        try {
    // 1. Captura os dados dos campos
    String nome = campoTextoNome.getText();
    String servico = campoTextoServico.getSelectedItem().toString();
    double valor = Double.parseDouble(
        campoTextoValor.getSelectedItem().toString().replace("R$", "").replace(",", ".").trim()
    );

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm");
    LocalDate data = LocalDate.parse(campoTextoData.getText(), formatter); 
    LocalTime hora = LocalTime.parse(campoTextoHora.getText(), formatterHora);
    String observacao = campoTextoObs.getText();

    // 2. Valida data no passado
    if (data.isBefore(LocalDate.now())) {
        JOptionPane.showMessageDialog(this, "Não é possível agendar para uma data no passado.");

        campoTextoNome.setText("");
        campoTextoData.setText("");
        campoTextoHora.setText("");
        campoTextoObs.setText("");
        return;
    }

    // 3. Valida horário fora do intervalo permitido
    LocalTime horaMinima = LocalTime.of(8, 0);
    LocalTime horaMaxima = LocalTime.of(22, 0);
    if (hora.isBefore(horaMinima) || hora.isAfter(horaMaxima)) {
        JOptionPane.showMessageDialog(this, "Horário inválido! Agendamentos são permitidos entre 08:00 e 22:00.");

        campoTextoNome.setText("");
        campoTextoData.setText("");
        campoTextoHora.setText("");
        campoTextoObs.setText("");
        return;
    }

    // 4. Verifica se já existe agendamento na data e hora informadas
    AgendamentoDAO agendamentoDAO = new AgendamentoDAO(new Conexao().getConeConnection());
    if (agendamentoDAO.existeAgendamento(data, hora)) {
        JOptionPane.showMessageDialog(this, "Já existe um agendamento para essa data e horário.");

        campoTextoNome.setText("");
        campoTextoData.setText("");
        campoTextoHora.setText("");
        campoTextoObs.setText("");
        return;
    }
    
    if (nome.isEmpty() || campoTextoData.getText().contains("_") || campoTextoHora.getText().contains("_")) {
    JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos obrigatórios.");
    
            campoTextoNome.setText("");
        campoTextoData.setText("");
        campoTextoHora.setText("");
        campoTextoObs.setText("");
    return;
}


    // 5. Cria o controller e chama o método
    AgendamentoController agendamentoController = new AgendamentoController(agendamentoDAO);
    agendamentoController.salvarAgendamento(nome, servico, (float) valor, data, hora, observacao);

    // 6. Limpa os campos e informa sucesso
    JOptionPane.showMessageDialog(null, "Horário agendado com sucesso!!!");
    campoTextoNome.setText("");
    campoTextoData.setText("");
    campoTextoHora.setText("");
    campoTextoObs.setText("");

    this.dispose();

} catch (Exception e) {
    JOptionPane.showMessageDialog(this, "Erro ao agendar: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
}

                
        
        
        
        
    }//GEN-LAST:event_jButton1AgendarBotaoActionPerformed

    private void campoTextoValorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoTextoValorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoTextoValorActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        MenuPrincipalView menu = new MenuPrincipalView();
        menu.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void campoTextoHoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoTextoHoraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoTextoHoraActionPerformed



    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AgendamentoView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AgendamentoView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AgendamentoView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AgendamentoView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AgendamentoView().setVisible(true);
            }
        });
    }
    
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFormattedTextField campoTextoData;
    private javax.swing.JFormattedTextField campoTextoHora;
    private javax.swing.JTextField campoTextoNome;
    private javax.swing.JTextArea campoTextoObs;
    private javax.swing.JComboBox<String> campoTextoServico;
    private javax.swing.JComboBox<String> campoTextoValor;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton1AgendarBotao;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
