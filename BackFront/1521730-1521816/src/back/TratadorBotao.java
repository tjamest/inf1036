package back;
import java.awt.*; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TratadorBotao implements ActionListener {

	static int roll = 0;
	
	Container c;

	public TratadorBotao(Container x) {
		c=x;
	}

	public void actionPerformed(ActionEvent e) {
	     roll = (int)(Math.random()*6+1);
	     System.out.printf("valor tirado eh %d\n", roll); 
	} 

	 public static int getNumber() {
		return roll;
	 }

}
