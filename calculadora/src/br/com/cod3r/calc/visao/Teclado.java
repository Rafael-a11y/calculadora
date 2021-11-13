package br.com.cod3r.calc.visao;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

import br.com.cod3r.calc.modelo.Memoria;
@SuppressWarnings("serial")
public class Teclado extends JPanel implements ActionListener{

	private final Color COR_CINZA_ESCURO = new Color(68,69,68);
	private final Color COR_CINZA_CLARO = new Color(97,100,99);
	private final Color COR_LARANJA = new Color(242, 163, 60);

	/*gridx e gridy - Recebem um valor inteiro para especificar as coordenadas do componentes.
	 * 
	 *gridwidth e gridheight - Especifica quantas células o componente irá ocupar, o valor padrão é 1.
	 *
	 *weightx e weighty - Definem o peso, o tamanho das células, recebem um double e por padrão recebem 0.
	 *
	 *fill - Serve para redimencionamento::
	 *	NONE - Não há redimensionamento.
	 *	HORIZONTAL - Redimensiona horizontalmente.
	 *	VERTICAL -  Redimensiona verticalmente.
	 *	BOTH - Redimensiona para ambos os eixos.
	 *
	 *anchor - Indica em que parte da célula o componente da célula deve ficar ancorado caso não esteja ocupando toda a célula.
	 *	O valor padrão é CENTER
	 *
	 *insets - Define um espaçamento ao redor do componente, ´pe defindo por um objeto Insets que por padrão não deixa espaçamento.
	 *
	 *ipadx e ipady - ipad significa Internal Padding, serve para defdinir a largura e altura em pixels, o valor padrão é 0.
	 *
	 *
	 *												DICAS
	 *
	 *Use o gridwidth e gridheght para definir o REMAINDER e o RELATIVE.
	 *
	 *Caso queira que o botão seja maior sem redimensionar os outros componentes, use o gridwidth e/ou gridheight para definir
	 *	que o componente irá ocupar mais de uma célula.
	 *
	 *O weightx e weighty aumentam os tamanhos das células, não o recomendo pois ele redmensiona outras células para que a 
	 *	grid fique adequada, ou seja, se você aumenta uma célula horizontalmente, as céluas que estão na mesma coluna (em cima
	 *	e embaixo desta) também serão redimensionadas de acordo, mesmo que você queira redimensionar somente auele componente
	 *	que está recebendo o GridBagConstraints que essa restrição, sendo assim, caso queira um botão maior use o griwidth 
	 *	e/ou gridheight para que este ocupe mais de uma célula e também o atributo fill para fazer com que o componente
	 *	preencha toda a célula.
	 *
	 *weightx e weighyt redimensionam o componente automaticamente para que as células ocupem todo o Container.
	 *
	 *anchor serve para especificar que parte da célula o componente irá ficar posicionado, o componente não precisa ocupar
	 *	mais de uma célula para o atributo anchor ser usado.*/
	
	public Teclado() {
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		this.setLayout(layout);
		c.weightx = 1;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		
		this.adicionarBotao("AC", COR_CINZA_ESCURO, c, 0, 0);
		this.adicionarBotao("±", COR_CINZA_ESCURO, c, 1, 0);
		this.adicionarBotao("%", COR_CINZA_ESCURO, c, 2, 0);
		this.adicionarBotao("/", COR_LARANJA, c, 3, 0);
		
		this.adicionarBotao("7", COR_CINZA_CLARO, c, 0, 1);
		this.adicionarBotao("8", COR_CINZA_CLARO, c, 1, 1);
		this.adicionarBotao("9", COR_CINZA_CLARO, c, 2, 1);
		this.adicionarBotao("*", COR_LARANJA, c, 3, 1);
		
		this.adicionarBotao("4", COR_CINZA_CLARO, c, 0, 2);
		this.adicionarBotao("5", COR_CINZA_CLARO, c, 1, 2);
		this.adicionarBotao("6", COR_CINZA_CLARO, c, 2, 2);
		this.adicionarBotao("-", COR_LARANJA, c, 3, 2);
		
		this.adicionarBotao("1", COR_CINZA_CLARO, c, 0, 3);
		this.adicionarBotao("2", COR_CINZA_CLARO, c, 1, 3);
		this.adicionarBotao("3", COR_CINZA_CLARO, c, 2, 3);
		this.adicionarBotao("+", COR_LARANJA, c, 3, 3);
		
		c.gridwidth = 2;
		this.adicionarBotao("0", COR_CINZA_CLARO, c, 0, 4);
		c.gridwidth = 1;
		this.adicionarBotao(",", COR_CINZA_CLARO, c,2, 4);
		this.adicionarBotao("=", COR_LARANJA, c, 3, 4);
		
	}
	private void adicionarBotao(String texto, Color cor, GridBagConstraints c, int x, int y) {
		c.gridx = x;
		c.gridy = y;
		Botao botao = new Botao(texto, cor);
		botao.addActionListener(this);
		this.add(botao, c);
	}
	@Override
	public void actionPerformed(ActionEvent evento) {
		if(evento.getSource() instanceof Botao) {
			Botao botao = (Botao) evento.getSource();
			Memoria.getInstancia().processarComando(botao.getText());
		}
	}
}
