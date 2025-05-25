package Controller;

import DAO.Conexao;
import DAO.UsuarioDAO;
import Model.Usuario;
import View.BarbeiroView;
import View.Login;
import View.MenuPrincipalView;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class LoginController {

    private final Login view;

    public LoginController(Login view) {
        this.view = view;
    }

    public void autenticar() {

        // Obtém o texto dos campos
        String nomeUsuario = view.getTextUsuario().getText();
        String senha = new String(view.getTextSenha().getPassword()); // pegar senha do JPasswordField corretamente

        // Verifica se os campos ainda estão com o placeholder
        if (nomeUsuario.equals("Nome do usuário")) {
            nomeUsuario = "";
        }
        if (senha.equals("Senha")) {
            senha = "";
        }

        // Se algum campo estiver vazio, mostra mensagem e não tenta autenticar
        if (nomeUsuario.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, preencha usuário e senha.");
            return; // Sai do método aqui
        }

        Usuario usuarioLogin = new Usuario(nomeUsuario, senha);

        try {
            Connection conexao = new Conexao().getConeConnection();
            UsuarioDAO usuarioDAO = new UsuarioDAO(conexao);
            Usuario usuarioAutenticado = usuarioDAO.autenticarUsuario(usuarioLogin);

            if (usuarioAutenticado != null) {
                if ("admin".equalsIgnoreCase(usuarioAutenticado.getNome())) {
                    BarbeiroView barbeiro = new BarbeiroView();
                    barbeiro.setVisible(true);
                } else {
                    MenuPrincipalView menu = new MenuPrincipalView();
                    menu.setVisible(true);
                }
                view.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Usuário ou senha inválidos.");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro na conexão com o banco de dados.");
        }

        // Limpa os campos após a tentativa de login
        view.getTextUsuario().setText("");
        view.getTextSenha().setText("");
    }
}
