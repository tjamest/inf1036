package front;
import java.awt.*;
import java.awt.geom.*;
import java.util.Vector;

import javax.swing.*;

import back.Jogo;
import back.Peao;
import back.Time;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Tabuleiro extends JPanel   {
	
	private static Image dado;
	int resp = 0;
	static Graphics2D g2d;
	static int roll = 0;
	public static Color fundo;


	private static final long serialVersionUID = -4264416327199530488L;
	
	public static  Color LIGHT_BLUE = new Color(51,153,255);
	public static  Color DARK_YELLOW = new Color(255,204,51);
	public static  Color DARK_GREEN = new Color(0, 204, 0);
	public static  Color DARK_RED = new Color(204, 0, 0);
	
	//Lista Times
	public static Vector<Time> times = new Vector<Time>();
	
	//vetor de pecas
	static  Vector<Peao> pecasVerm = new Vector<Peao>();
	static Vector<Peao> pecasAzul = new Vector<Peao>();
	static Vector<Peao> pecasVerde = new Vector<Peao>();
	static Vector<Peao> pecasAma = new Vector<Peao>();
	
	//posicoes iniciais dos peos do jogo
	public static int[][] pinoIniVerm = {{1, 1}, {1,4}, {4, 1}, {4,4}};
	public static int[][] pinoIniVerde = {{10, 1}, {13,1}, {10, 4}, {13,4}};
	public static int[][] pinoIniAmar = {{10, 10}, {13,10}, {10, 13}, {13,13}};
	public static int[][] pinoIniAzul = {{1, 10}, {1,13}, {4, 10}, {4,13}};
	
	//posicoes elipses (vetor com posicoes do desenho das elipses bancas)
	static int[][] elipsesVerm = {{40, 40}, {160, 40}, {40, 160}, {160, 160}};
	static int[][] elipsesAzul = {{40, 400}, {160, 400}, {40, 520}, {160, 520}};
	static int[][] elipsesVerde = {{400, 40}, {520, 40}, {400, 160}, {520, 160}};
	static int[][] elipsesAma = {{400, 400}, {520, 400}, {400, 520}, {520, 520}};
	
	//Vetor com todos os nomes das imagens dos dados
	private static String[] images = {"Dado1.png", "Dado2.png", "Dado3.png", "Dado4.png", "Dado5.png", "Dado6.png"};
	

	public Tabuleiro() {
		
    	setLayout(null);
        setBorder(BorderFactory.createLineBorder(Color.black));
        
        new Menu();
        
        this.add(Menu.b1);
        this.add(Menu.b2);
        this.add(Menu.b3);
        this.add(Menu.b4);
        this.add(Menu.turno);
        
        Menu.b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
		        addMouseListener(new Jogo());  //mouse
			}
		});
        
        Menu.b4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
				int x = (int)(Math.random()*6+1);
				Jogo.numeroDado(x);
				roll = x;
				
				//roll = Jogo.numeroDado();
			    dado = new ImageIcon(this.getClass().getResource(images[roll-1])).getImage();  
			}
		});
        
    }

	public void paintComponent(Graphics g) {
		
		repaint();
		
    	super.paintComponent(g);
    	
    	g2d = (Graphics2D) g;
    	
    	DrawBoard(g2d);
    	DrawRectangle(g2d);
    	DrawQuadradosColoridos(g2d);
    	ModoDebug(g2d);
 
    	if (Jogo.newgame == true) {
    		
    		iniciaTimes();
    		iniciaVetorPecas();
    		desenhaPinos();
    		
    		
    		repaint();
    	}
    	//Jogo.printMatrizPosicoes();

	}

	private static void desenhaPinos() {
		
	
		for (int i = 0; i < 4 ; i ++) {	

			DrawPino(g2d, pecasVerm.elementAt(i).CoordX,  pecasVerm.elementAt(i).CoordY, Color.RED, pecasVerm.elementAt(i).alteraTam);
			Jogo.posicoes[pecasVerm.elementAt(i).CoordX][pecasVerm.elementAt(i).CoordY] = i;

			DrawPino(g2d, pecasAzul.elementAt(i).CoordX,  pecasAzul.elementAt(i).CoordY, Color.BLUE, pecasAzul.elementAt(i).alteraTam);
			Jogo.posicoes[pecasAzul.elementAt(i).CoordX][pecasAzul.elementAt(i).CoordY] = i;

			DrawPino(g2d, pecasVerde.elementAt(i).CoordX,  pecasVerde.elementAt(i).CoordY, Color.GREEN, pecasVerde.elementAt(i).alteraTam);
			Jogo.posicoes[pecasVerde.elementAt(i).CoordX][pecasVerde.elementAt(i).CoordY] = i;

			DrawPino(g2d, pecasAma.elementAt(i).CoordX,  pecasAma.elementAt(i).CoordY, Color.YELLOW, pecasAma.elementAt(i).alteraTam);	
			Jogo.posicoes[pecasAma.elementAt(i).CoordX][pecasAma.elementAt(i).CoordY] = i;
		}

	}
	
	public static void movepeca(Peao peca , int x, int y, int index, int dado) {
		
//		int oldX = (int) Math.ceil(peca.elementAt(index).CoordX/40);
//		int oldY = (int) Math.ceil(peca.elementAt(index).CoordY/40);
		
//		peca.elementAt(index).CoordX = (x * 40) + 10;
//		peca.elementAt(index).CoordY = (y * 40) + 10;
		
		//diminuir tamanho do peao de cima caso abrigo
		
		int oldX = peca.CoordX;
		int oldY = peca.CoordY;
		
		peca.CoordX = x;
		peca.CoordY = y;
		

		Jogo.posicoes[oldX][oldY] = -1;
		
		//desenhaPinos();

	}
	
	public static void movepeao(Peao peca, int x, int y) {
		
//		System.out.printf("------ Move Peao X recebido = %d \n ", x);
//		System.out.printf("------ Move Peao Y recebido = %d \n ", y);
//		System.out.printf("------ Move Peao oldX recebido = %d \n ", peca.CoordX);
//		System.out.printf("------ Move Peao oldY recebido = %d \n ", peca.CoordY);
		
		int oldX = peca.CoordX;
		int oldY = peca.CoordY;
		
		peca.CoordX = x;
		peca.CoordY = y;

		Jogo.posicoes[oldX][oldY] = -1;

		desenhaPinos();
		
	}
	
	private void iniciaVetorPecas() {
		
		for (int i = 0; i < 4 ; i ++){
			pecasVerm.add(new Peao(Color.RED, pinoIniVerm [i][0], pinoIniVerm [i][1], i, false));
			pecasAzul.add(new Peao(Color.BLUE, pinoIniAzul [i][0], pinoIniAzul [i][1], i, false));
			pecasVerde.add(new Peao(Color.GREEN,  pinoIniVerde [i][0],  pinoIniVerde [i][1], i, false));
			pecasAma.add(new Peao(Color.YELLOW,  pinoIniAmar [i][0],  pinoIniAmar [i][1], i, false));
		}
		
		times.elementAt(0).recebePeoes(pecasVerm);
		times.elementAt(1).recebePeoes(pecasVerde);
		times.elementAt(2).recebePeoes(pecasAma);
		times.elementAt(3).recebePeoes(pecasAzul);
	}
	
	private void iniciaTimes() {
		
		Time equipeVermelho = new Time(Color.RED);
		Time equipeVerde = new Time(Color.GREEN);
		Time equipeAmarelo = new Time(Color.YELLOW);
		Time equipeAzul = new Time(Color.BLUE);

		times.add(equipeVermelho);
		times.add(equipeVerde);
		times.add(equipeAmarelo);
		times.add(equipeAzul);
		

	}
	
	private void DrawBoard(Graphics2D g2d) {
		
		
		for(int a = 0; a < 15; a++){
			   for(int b = 0; b < 15; b++){
				   
				  //----- SET COLOR PARA QUADRADOS COLORIDOS -----// 
		
				   if(b>5 && b < 9) { 
					   if(a<=5) {
						   
						   g2d.setColor(Tabuleiro.DARK_GREEN);
						   
					   }
					   else if(a>8) {
						   
						   g2d.setColor(Tabuleiro.LIGHT_BLUE);
						   
					   }
					   
				   	}
				   
				   else if(b<=5){
					   
					   g2d.setColor(Tabuleiro.DARK_RED);
					   
				   }
				   else if(b>8) {
					   
					   g2d.setColor(Tabuleiro.DARK_YELLOW);
					   
				   }
				   
				 //----- DESENHA OS PRIMEIROS QUADRADOS COLORIDOS E AS SETINHAS DE DENTRO -----// 
				   
				   if ((a == 6 && b == 1) || (a == 1 && b == 8) ||  (a == 8 && b == 13) 
						   
						   ||  (a == 13 && b == 6)) { 
					   
					   g2d.fillRect(b * 40, a * 40, 40, 40);
					   

					   int[][] arrayX = {{50, 50, 70}, {330, 350, 340}, {550, 550, 530}, {250, 270, 260}};
					   int[][] arrayY = {{250, 270, 260}, {50, 50, 70}, {330, 350, 340}, {550, 550, 530} };
					   
					   
					   DrawTriangulo(arrayX[0], arrayY[0], Color.WHITE, g2d);
					   DrawTriangulo(arrayX[1], arrayY[1], Color.WHITE, g2d);
					   DrawTriangulo(arrayX[2], arrayY[2], Color.WHITE, g2d);
					   DrawTriangulo(arrayX[3], arrayY[3], Color.WHITE, g2d);
  
				   }
				   
				 //----- DESENHA CAMINHO COLORIDOS -----// 
				   
				   else if ((a == 7 && b >=1 && b<=5) || ( a >=1 && a<=5 && b == 7) || (a == 7  && b >=9 && b<=13) || (b == 7 && a >= 9 && a <= 13)) {
					   
					   g2d.fillRect(b * 40, a * 40, 40, 40);
					   
				   }
				   g2d.setColor(Color.black);
				   
				   
				 //----- DESENHA QUADRADOS PRETOS -----// 
				   
				   if ((a == 8 && b == 1) || (a == 13 && b == 8) || (a == 6 && b == 13) || (a == 1 && b == 6) ) {  
					   
					   g2d.fillRect(b * 40, a * 40, 40, 40);
					   
				   }
				   
				   //----- DESENHA TRIANGULOS DO CENTRO -----// 
				
				   else if (a >= 6 && a <=8 && b >= 6 && b <=8 ) { 

					   int[][] arrayX = {{240, 240, 300}, {360, 360, 300}, {240, 360, 300}, {240, 360, 300}};
					   int[][] arrayY = {{240, 360, 300}, {240, 360, 300}, {360, 360, 300}, {240, 240, 300}};
						
					   DrawTriangulo(arrayX[0], arrayY[0], Tabuleiro.DARK_RED, g2d);
					   DrawTriangulo(arrayX[1], arrayY[1], Tabuleiro.DARK_YELLOW, g2d);
					   DrawTriangulo(arrayX[2], arrayY[2], Tabuleiro.LIGHT_BLUE, g2d);
					   DrawTriangulo(arrayX[3], arrayY[3], Tabuleiro.DARK_GREEN, g2d);
						
						g2d.setPaint(Color.BLACK);
						g2d.drawRect(240, 240, 120, 120);

					   
				   }
				   
				   //----- DESENHA O RESTO DOS TRIANGULOS DO TABULEIRO -----//
	
				   else if (a >= 6 && a <=8 ||b >= 6 && b <=8 ) {
					   g2d.drawRect(b * 40, a * 40, 40, 40);
				   }
				   
				   g2d.drawRect(b * 40, a * 40, 40, 40);// tirar depois

			   }
			}
	
	}
	
	static void DrawPino(Graphics2D g2d, int x, int y, Color cor, boolean altera) {
		
	
		int x1  = (x * 40) + 10;
		int y1 = (y * 40) + 10;
		int altura;
		int largura;
		
		if (altera == true ) {
			
			 altura = 15;
			 largura = 15;
			 x1 += 3;
			 y1 += 3;
		}
		else {
			 altura = 20;
			 largura = 20;
			
		}
		 g2d.setPaint(cor);
		 g2d.fill(new Ellipse2D.Double(x1,y1, altura, largura));
	     g2d.setPaint(Color.black);
	     g2d.draw(new Ellipse2D.Double(x1,y1, altura, altura));

	}
	
	static void DrawQuadrado(Graphics2D g2d, int x, int y, Color cor) {
		
		g2d.setColor(cor);
		g2d.fillRect(x, y, 240, 240);
		g2d.setPaint(Color.BLACK);
		g2d.drawRect(x, y, 240, 240);
		
	}
	
	private void DrawQuadradosColoridos(Graphics2D g2d) {
		
		DrawQuadrado(g2d, 0, 0, Tabuleiro.DARK_RED);
		DrawQuadrado(g2d, 0, 360, Tabuleiro.LIGHT_BLUE);
		DrawQuadrado(g2d, 360, 0, Tabuleiro.DARK_GREEN);
		DrawQuadrado(g2d, 360, 360, Tabuleiro.DARK_YELLOW);

		for (int i = 0; i < 4 ; i ++) {		
			
			DrawElipse(g2d, elipsesVerm[i][0], elipsesVerm[i][1], Tabuleiro.DARK_RED);
			DrawElipse(g2d, elipsesAzul[i][0], elipsesAzul[i][1], Tabuleiro.LIGHT_BLUE);
			DrawElipse(g2d, elipsesVerde[i][0], elipsesVerde[i][1], Tabuleiro.DARK_GREEN);
			DrawElipse(g2d, elipsesAma[i][0], elipsesAma[i][1], Tabuleiro.DARK_YELLOW);

		}	
	}
	
	private void DrawElipse(Graphics2D g2d, int x, int y, Color cor) {
		
		 g2d.setPaint(Color.WHITE);
	     g2d.fill(new Ellipse2D.Double(x,y, 40, 40));
	     g2d.setPaint(cor);
	     g2d.draw(new Ellipse2D.Double(x,y, 40, 40));
	     
	}
	
	private void DrawTriangulo(int[] arrayX , int[] arrayY, Color cor, Graphics2D g2d) {
		
		g2d.setPaint(Color.BLACK);
		g2d.drawPolygon(arrayX, arrayY, 3);
		g2d.setPaint(cor);
		g2d.fillPolygon(arrayX, arrayY, 3);
		
	}
	
	private void DrawRectangle(Graphics2D g2d) {
		
		g2d.setColor(Color.GRAY);
		g2d.fill(new Rectangle2D.Double(600, 0, 200, 600));
		
		g2d.setColor(fundo);
		g2d.fill(new Rectangle2D.Double(640, 280, 120, 120));
		
		g2d.drawImage(dado, 650, 290, null);
		
		
	}
	
	private void ModoDebug(Graphics2D g2d) {
		
		g2d.setColor(Jogo.corEquipedaVez);
		g2d.fillRect(620, 585, 145, 10);
		
		JButton dado1 = new JButton("1");
		JButton dado2 = new JButton("2");
		JButton dado3 = new JButton("3");
		JButton dado4 = new JButton("4");
		JButton dado5 = new JButton("5");
		JButton dado6 = new JButton("6");
		
		
		//Definindo posicoes
		
		dado1.setBounds(620,470,45,45);
		this.add(dado1);
		dado2.setBounds(670,470,45,45);
		this.add(dado2);
		dado3.setBounds(720,470,45,45);
		this.add(dado3);
		dado4.setBounds(620,530,45,45);
		this.add(dado4);
		dado5.setBounds(670,530,45,45);
		this.add(dado5);
		dado6.setBounds(720,530,45,45);
		this.add(dado6);
		
		//Listeners
		dado1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					Jogo.numeroDado(1);
					
				}
			});
		
		dado2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Jogo.numeroDado(2);
				
			}
		});
		
		dado3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Jogo.numeroDado(3);
				
			}
		});
		
		dado4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Jogo.numeroDado(4);
				
			}
		});
		
		dado5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Jogo.numeroDado(5);
				
			}
		});
		
		dado6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Jogo.numeroDado(6);
				
			}
		});

	}

}
