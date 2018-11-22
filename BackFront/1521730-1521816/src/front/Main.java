package front;
import javax.swing.JFrame;

public class Main extends JFrame {

	private static final long serialVersionUID = 836988929125495440L;

	static JFrame frame = new JFrame("1521730-1521816 - SUPER LUDO");
	static int number;

	public static void main(String[] args)
	{
		ConfiguraFrame();

		Tabuleiro tab = new Tabuleiro();
		frame.add(tab);
		frame.setVisible(true);	
		frame.repaint();
	 }
	
	static void ConfiguraFrame() {
		
		//frame.setSize(800, 620); //mac
		frame.setSize(800, 640); //windows
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	}
}
