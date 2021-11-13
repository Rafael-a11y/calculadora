package br.com.cod3r.calc.modelo;
import java.util.List;
import java.util.ArrayList;
public class Memoria {

	private static final Memoria instancia = new Memoria();
	private final List<MemoriaObservador> observadores = new ArrayList<MemoriaObservador>();
	private enum TipoComando{ZERAR,NUMERO,DIVISAO,MULTIPLICACAO,SUBTRACAO,SOMA,IGUAL,VIRGULA, PORCENTAGEM,MAIS_MENOS;}
	
	private TipoComando ultimaOperacao = null;
	private boolean substituir = false;
	private String textoAtual = "";
	private String textoBuffer = "";
	
	public static Memoria getInstancia() {
		return instancia;
	}
	
	public void adicionarObservador(MemoriaObservador o) {
		observadores.add(o);
	}
	
	public String getTextoAtual() {
		return textoAtual.isEmpty() ? "0" : textoAtual;
	}
	
	public void processarComando(String texto) {
		TipoComando tipoComando = detectarTipoComando(texto);
		System.out.println(tipoComando);
		if(tipoComando == null) {
			return;
		}
		else if(tipoComando == TipoComando.ZERAR) {
			textoAtual = "";
			textoBuffer = "";
			substituir = false;
			ultimaOperacao = null;
		}
		else if(tipoComando == TipoComando.MAIS_MENOS && textoAtual.contains("-")){
			textoAtual = textoAtual.substring(1);
		}
		else if(tipoComando == TipoComando.MAIS_MENOS && !textoAtual.contains("-")) {
			textoAtual = "-" + textoAtual;
		}
		else if(tipoComando == tipoComando.NUMERO || tipoComando == tipoComando.VIRGULA) {
			textoAtual = substituir ? texto : textoAtual + texto;
			substituir = false;
		}
		else {
			substituir = true;
			textoAtual = obterResultadoOperacao();
			textoBuffer = textoAtual;
			ultimaOperacao = tipoComando;
		}
		
		observadores.forEach(o -> o.valorAlterado(this.getTextoAtual()));
		
	}

	private String obterResultadoOperacao() {
		if(ultimaOperacao == null || ultimaOperacao == TipoComando.IGUAL) {
			return textoAtual;
		}
		
		double numeroBuffer = Double.parseDouble(textoBuffer.replace(",", "."));
		double numeroAtual = Double.parseDouble(textoAtual.replace(",", "."));
		double resultado = 0;
		
		if(ultimaOperacao == TipoComando.SOMA) {
			resultado = numeroBuffer + numeroAtual;
		}
		else if(ultimaOperacao == TipoComando.SUBTRACAO) {
			resultado = numeroBuffer - numeroAtual;
		}
		else if(ultimaOperacao == TipoComando.MULTIPLICACAO) {
			resultado = numeroBuffer * numeroAtual;
		} 
		else if(ultimaOperacao == TipoComando.DIVISAO) {
			resultado = numeroBuffer / numeroAtual;
		}
		else if(ultimaOperacao == TipoComando.PORCENTAGEM) {
			resultado = (numeroBuffer / 100) * numeroAtual;
		}
		
		String texto = String.valueOf(resultado).replace(".", ",");
		boolean inteiro = texto.endsWith(",0");
		return inteiro ? texto.replace(",0", "") : texto;
	}

	private TipoComando detectarTipoComando(String texto) {
		if(textoAtual.isEmpty() && texto == "0") return null;
		try {
			Integer.parseInt(texto);
			return TipoComando.NUMERO;
		}
		catch(NumberFormatException e) {
			//Quando não é um número!
			if("AC".equals(texto)) {
				return TipoComando.ZERAR;
			}
			else if("/".equals(texto)) {
				return TipoComando.DIVISAO;
			}
			else if("*".equals(texto)) {
				return TipoComando.MULTIPLICACAO;
			}
			else if("+".equals(texto)) {
				return TipoComando.SOMA;
			}
			else if("-".equals(texto)) {
				return TipoComando.SUBTRACAO;
			}
			else if("=".equals(texto)) {
				return TipoComando.IGUAL;
			}
			else if(",".equals(texto) && !textoAtual.contains(",")) {
				return TipoComando.VIRGULA;
			}
			else if("±".equals(texto)) {
				return TipoComando.MAIS_MENOS;
			}
			else if("%".equals(texto)) {
				return TipoComando.PORCENTAGEM;
			}
		}
		return null;
	}
}
