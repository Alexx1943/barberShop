package Controller;

import DAO.Conexao;
import DAO.UsuarioDAO;
import Model.Usuario;
import View.CadastroView;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class CadastroController {
    
    private CadastroView view;

    public CadastroController(CadastroView view) {
        this.view = view;
    }

public void salvaUsuario() {
    // Acessa os campos da view
    String textoUsuario = view.getjTextField1Usuario().getText().trim();
    String senhaUsuario = new String(view.getjPasswordField1Senha().getPassword()).trim();
    String confiSenhaUsuario = new String(view.getjPasswordField2Senha().getPassword()).trim();

    // Verifica se algum campo está vazio ou contém apenas o placeholder
    if (textoUsuario.isEmpty() || textoUsuario.equals("Digite seu nome") ||
        senhaUsuario.isEmpty() || senhaUsuario.equals("Digite sua senha") ||
        confiSenhaUsuario.isEmpty() || confiSenhaUsuario.equals("Confirme sua senha")) {

        JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.");
        
            // Limpa os campos     
            view.resetCamposComPlaceholder();

        return; // Impede o cadastro
    }

    // Verifica se as senhas coincidem
    if (!senhaUsuario.equals(confiSenhaUsuario)) {
        JOptionPane.showMessageDialog(null, "As senhas não coincidem.");
        
        view.resetCamposComPlaceholder();
        return;
    }

    // Se tudo estiver certo, continua com o cadastro
    Usuario usuario = new Usuario(textoUsuario, senhaUsuario);

    try {
        Connection conexao = new Conexao().getConeConnection();
        UsuarioDAO usuarioDAO = new UsuarioDAO(conexao);
        usuarioDAO.insert(usuario);

        JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");
        view.dispose(); // Fecha a tela após sucesso
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Erro ao cadastrar usuário: " + ex.getMessage());
    }
    
 
}
}