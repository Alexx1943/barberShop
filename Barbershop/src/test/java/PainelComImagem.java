/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import View.*;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class PainelComImagem extends JPanel {

    private Image imagem;

    public PainelComImagem(String caminhoImagem) {
        URL imagemURL = getClass().getResource(caminhoImagem);
        System.out.println("Caminho imagem carregado: " + imagemURL);
        if (imagemURL != null) {
            this.imagem = new ImageIcon(imagemURL).getImage();
        } else {
            System.out.println("ERRO: Imagem não encontrada em: " + caminhoImagem);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imagem != null) {
            g.drawImage(imagem, 0, 0, getWidth(), getHeight(), this);
        }
    }
}


