package back;
import java.awt.Color;

public class Movimentacao {
	
	static int casaX, casaY;

	public Movimentacao() {
		
	}
	
	public static void MovimentacaoNormal(int x, int y, int roll, Color cor, int indice) { 
		
		//Caminho branco

		int auxX = x;
		int auxY = y;
		int z = -1 ;

		for (int i = 0 ; i<roll; i++) {

			if (auxX < 5 && auxY == 6 && z!= i) {	
				auxX += 1;
				z ++;
			}
			
			else if (auxX == 5 && auxY == 6 && z!= i) {
				//casa de virada [5][6], anda em y 
				
				auxX = 6;
				auxY -= 1;
				z ++;
			}
			else if (auxX == 6 && auxY > 0 && auxY < 6  &&  z!= i) {	
				
				auxY -= 1;
				z ++;
			}
			else if (auxX == 6 && auxY > 0 && auxY < 6  &&  z!= i) {	
				
				auxY -= 1;
				z ++;
			}
			else if (auxX == 6 && auxY == 0 && z!= i) { 
				//casa de virada [6][0], anda em x 
				
				auxX += 1;
				auxY = 0;
				z ++;
			}
			else if (auxX == 7 && auxY == 0 && z!= i) { // --------------------- Casa de virada caso peca for verde
				if (cor == Color.GREEN) {
					auxY += 1;
					z++;
				}
				else {
					auxX += 1;
					z ++;
				}
			}
			else if (auxX == 8 && auxY >= 0 && auxY < 5 && z!= i) { 
				//casa de virada [8][0], anda em y
				//+ caminho ate casa [8][5]
				auxY += 1;
				z++;
				
			}
			else if (auxX == 8 && auxY == 5 && z!= i) { 
				//casa de virada [8][5], anda em x
				
				auxX += 1;
				auxY = 6;
				z ++;
			}
			else if (auxX >= 9 && auxX <14 && auxY == 6 && z!= i) { 
				auxX += 1;
				z ++;
			}
			else if (auxX == 14 && auxY == 6 && z!= i) { 
				//casa de virada [14][6] , anda em y
				auxY += 1;
				z ++;
			}
			else if (auxX == 14 && auxY == 7 && z!= i) {  // --------------------- Casa de virada caso peca for amarela 
				
				if (cor == Color.YELLOW) {
					auxX -= 1;
					z++;
				}
				else {
					auxY += 1;
					z ++;
				}
			}
			else if (auxX <= 14 && auxX > 9 && auxY == 8 && z!= i) { 
				//casa de virada [14][8], anda em x
				//+ caminho ate casa [9][8]
				auxX -= 1;
				z ++;
			}
			else if (auxX == 9 && auxY == 8 && z!= i) { 
				//casa de virada [9][8], anda em y
				auxX = 8;
				auxY += 1;
				z ++;
			}
			else if (auxX == 8 && auxY >= 9 && auxY < 14 && z!= i) { 
				
				auxY += 1;
				z ++;
			}
			else if (auxX == 8 && auxY == 14 && z!= i) { 
				//casa de virada [8][14] , anda em x
				auxX -= 1;
				z ++;
			}
			else if (auxX == 7 && auxY == 14 && z!= i) { // --------------------- Casa de virada caso peca for azul	
				if (cor == Color.BLUE) {
					auxY -= 1;
					z++;
				}
				else {
					auxX -= 1;
					z ++;
					
				}
			}
			else if (auxX == 6 && auxY == 14 && z!= i) { 
				//casa de virada [6][14], anda em y
				
				auxY -= 1;
				z ++;
			}
			else if (auxX == 6 && auxY <= 14 && auxY > 9 && z!= i) { 
	
				auxY -= 1;
				z ++;
			}
			else if (auxX == 6 && auxY == 9 && z!= i) { 
				//casa de virada [6][9], anda em x
				
				auxX -= 1;
				auxY = 8;
				z ++;
			}
			else if (auxX <= 5 && auxX > 0 && auxY == 8 && z!= i) { 
				
				auxX -= 1;
				z ++;
			}
			else if (auxX == 0 && auxY == 8 && z!= i) { 
				//casa de virada [0][8], anda em y
				
				auxY -= 1;
				z ++;
			}
			else if (auxX == 0 && auxY == 7 && z!= i ) {  // --------------------- Casa de virada caso peca for vermelha
		
				if (cor == Color.RED) {
					auxX += 1;
					z++;
					System.out.printf("ENTROU CARAI NESS BUCETA \n", indice);
				}
				else {
					auxY -= 1;
					z ++;
				}
			}
			
			
			// --------------------- CASOS CAMINHOS COLORIDOS ----------------------------------
			
			//Caso caminho Vermelho int[][] caminhoVermelho = {{1, 7}, {2, 7},{3, 7},{4, 7},{5, 7}};
			
			else if (auxX > 0 && auxX < 6 && auxY == 7 && z!= i && cor == Color.RED) {
				// se x >0 e x < 6 e y == 7
				System.out.printf("ENTROU CARAI NESS BUCETA \n", indice);
				auxX += 1;
				z++;
				
			}
			else if (auxX == 6 && auxY == 7 && z!= i && cor == Color.RED) {
				//aumentar em +1 de peca na casa final no time vermelho
				System.out.printf("Peca Vermelha de indice = %d chegou na casa final \n", indice);
				System.out.printf("Escolha outra peca para mover \n", indice);
				z++;
			}
			
			//Caso caminho Verde int[][] caminhoVerde = {{7, 1}, {7, 2},{7, 3},{7, 4},{7, 5}};
			else if (auxX == 7 && auxY > 0 && auxY < 6 && z!= i && cor == Color.GREEN) {
				// se x == 7 e y >0 e y < 6
				
				auxY += 1;
				z++;
				
			}
			else if (auxX == 7 && auxY == 6 && z!= i && cor == Color.GREEN) {
				//aumentar em +1 de peca na casa final no time verde
				System.out.printf("Peca Verde de indice = %d chegou na casa final \n", indice);
				System.out.printf("Escolha outra peca para mover \n", indice);
				z++;
			}
			
			//Caso caminho Amarelo static int[][] caminhoAmarelo = {{13, 7}, {12, 7},{11, 7},{10, 7},{9, 7}};
			else if (auxX < 14 && auxX > 8 && auxY == 7 && z!= i && cor == Color.YELLOW) {
				// se y == 7 e x< 14 e x> 8 
				
				auxX -= 1;
				z++;
				
			}
			else if (auxX == 8 && auxY == 7 && z!= i && cor == Color.YELLOW) {
				//aumentar em +1 de peca na casa final no time amarelo
				System.out.printf("Peca Amarela de indice = %d chegou na casa final \n", indice);
				System.out.printf("Escolha outra peca para mover \n", indice);
				z++;
			}
			
			//Caso caminho Azul
			//static int[][] caminhoAzul = {{7, 13}, {7, 12},{7, 11},{7, 10},{7, 9}};
			
			else if (auxY < 14 && auxY > 8 && auxX == 7 && z!= i && cor == Color.BLUE) {
				// se x == 7  8 e y< 14 e y> 8 
				
				auxY -= 1;
				z++;
				
			}
			else if (auxX == 7 && auxY == 8 && z!= i && cor == Color.BLUE) {
				//aumentar em +1 de peca na casa final no time amarelo
				System.out.printf("Peca Amarela de indice = %d chegou na casa final \n", indice);
				System.out.printf("Escolha outra peca para mover \n", indice);
				z++;
			}
		}
		
		casaX = auxX;
		casaY = auxY;
		
		System.out.printf(" Classe Movimentacao - mover para [%d][%d]\n", casaX, casaY );
	
	}//fim movimenntacao normal




}//fim classe movimentacao

		
	
	