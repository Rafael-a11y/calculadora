package br.com.cod3r.calc.visao;
/*Implementar a própria barra com as operações de fechar, maximizar e minimizar. */
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Dimension;
@SuppressWarnings("serial")
public class Calculadora extends JFrame{

	Calculadora() {
		
		super("Calculadora");
		this.organizarLayout();
//		this.setUndecorated(true);//Remove a barra, o que eu não acho recomendável fazer na janela principal, pois remove os
		//botões de minimização, maximização e encerramento do programa. Chama setResizable(true) na sua implementação.
		this.setSize(232, 322);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.getContentPane().setBackground(Color.ORANGE);
		this.setResizable(false);
		this.setVisible(true);
		
	}
	
	private void organizarLayout() {
		this.setLayout(new BorderLayout());
		Display display = new Display();
		Teclado teclado = new Teclado();
		
		display.setPreferredSize(new Dimension(232, 60));
		
		this.getContentPane().add(display, BorderLayout.NORTH);
		this.getContentPane().add(teclado,BorderLayout.CENTER);
		
	}

	public static void main(String[] args) {
		new Calculadora();
	}
	
}
