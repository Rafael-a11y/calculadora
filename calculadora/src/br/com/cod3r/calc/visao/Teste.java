package br.com.cod3r.calc.visao;
import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
public class Teste extends JFrame{

	private final GridBagLayout grid;
	private final JPanel painel;
	private int num = 0;
	private JScrollPane barraRolagem;
	
	public Teste() {
		super("Teste");
		this.grid = new GridBagLayout();
		this.painel = new JPanel();
		organizarComponentes();
		iniciarGUI();
	}
	
	private void organizarComponentes() {
		this.painel.setPreferredSize(new Dimension(400,150));
		this.painel.setLayout(grid);
		GridBagConstraints gridConstraints = new GridBagConstraints();
		for (int i = 0; i <= 9; i++) {
			GridBagConstraints gridConstantes = new GridBagConstraints();
			gridConstantes.weightx = 0.0;
			gridConstantes.weighty = 0.0;
			gridConstantes.gridwidth = GridBagConstraints.RELATIVE;
			gridConstantes.gridheight = GridBagConstraints.RELATIVE;
			gridConstantes.fill = GridBagConstraints.HORIZONTAL;
			if(i == 2 || i == 5 || i == 8) {
				gridConstantes.gridwidth = GridBagConstraints.REMAINDER;
			}
			if(i != 9) {
				this.painel.add(new JButton(i + ""), gridConstantes);
			}
			else {
				gridConstantes.gridwidth = 3;
				gridConstantes.gridheight = 1;
				gridConstantes.fill = GridBagConstraints.HORIZONTAL;
				gridConstantes.anchor = gridConstraints.CENTER;
				this.painel.add(new JButton(i + ""), gridConstantes);
			}
			
		}
		}
	
	private void iniciarGUI() {
		this.setSize(new Dimension(400, 200));
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.getContentPane().add(painel, BorderLayout.NORTH);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		new Teste();
	}
}
