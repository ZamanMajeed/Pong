//Zaman Majeed
//GameFrame made
import java.awt.*;
import javax.swing.*;

public class GameFrame extends JFrame {

	GamePanel panel; // Creating a panel to put the contents of the pong game in

	public GameFrame() {
		panel = new GamePanel(); 
		
		this.add(panel);
		
		this.setTitle("Pong For Two");//title
		
		this.setResizable(false); // No resizing as it will mess up the window
		
		this.setBackground(Color.blue); // Make background blue like a real ping pong table
		
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // GUI stops when x button is used 
		this.pack(); // makes JFrame correct size
		this.setVisible(true); // Makes visible 
		this.setLocationRelativeTo(null); // Centers the frame

	}
}