package br.com.cod3r.calc.visao;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
@SuppressWarnings("serial")
public class Botao extends JButton{

	public Botao(String texto, Color cor) {
		this.setText(texto);
		///Voltar aui novamente.
		this.setOpaque(true); //Para mostrar a cor Laranja.
		this.setBackground(cor);
		this.setFont(new Font("courier", Font.PLAIN, 15)); //Depois vou dar uma pesquisada sobre a classe Font.
		this.setForeground(Color.BLACK); //Também dar uma pesquisada na classe Color.
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK)); //E dar uma pesquisada na classe Border.
		
	}
}
