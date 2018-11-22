package back;
// ainda nao implementado


import java.awt.Color;
import java.util.Vector;
public class Time {
	
	public Vector<Peao> peoes;
	private Color corT;
	public int qtdPinosCasaFinal = 0;
	protected int dado;
	protected Peao ultimoPinoMovimentado;
	
	
	
	public Time(Color cor) {
		
		this.corT = cor;
		qtdPinosCasaFinal = 0;
		
	}
	
	public void addPinoCasaFinal() {
		qtdPinosCasaFinal += 1;
	}
	
	public void recebePeoes (Vector<Peao> p) {
		peoes = p;
	}
	public Color getCor() {
		return corT;
	}
	

}
