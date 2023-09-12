//Zaman MAjeed
//Score panel made and configured
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Score extends Rectangle {

	static int GAME_WIDTH;
	static int GAME_HEIGHT;
	int player1;
	int player2;
	boolean win = false;
	
	Score(int GAME_WIDTH, int GAME_HEIGHT) {
		Score.GAME_WIDTH = GAME_WIDTH;
		Score.GAME_HEIGHT = GAME_HEIGHT;
	}

	// draws the score and line down the middle
	public void draw(Graphics g) {
		// sets color and font
		g.setColor(Color.black);
		
		g.setFont(new Font("Arial", Font.PLAIN, 45));

		// line down middle
		for (int i = 0; i < GAME_HEIGHT; i = i + 20) { 
			g.drawLine(GAME_WIDTH / 2, i, GAME_WIDTH / 2, i + 30);
		}
		for (int j = 0; j < GAME_HEIGHT; j = j + 20) { 
			g.drawLine(GAME_WIDTH / 2, j+1, GAME_WIDTH / 2, j + 31);
		}
		// score
		g.drawString(String.valueOf(player1), (GAME_WIDTH / 2) - 55, 50);
		g.drawString(String.valueOf(player2), (GAME_WIDTH / 2) + 20, 50);
		
		//win screen
		if(player1==3) {
			win = true;
			g.drawString("LEFT WINS", 150, 300);
		}
		if(player2==3) {
			win = true;
			g.drawString("RIGHT WINS", 550, 300);
		}
		
	}
}