//Zaman Majeed
//Paddles made and configured


import java.awt.*;
import java.awt.event.*;

public class Paddle extends Rectangle {

	int player;
	int yVelocity;//y velocity for paddle
	int speed = 10;//speed of player paddle

	//constructor class, paddle specifications
	Paddle(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT, int player) {
		super(x, y, PADDLE_WIDTH, PADDLE_HEIGHT);
		this.player = player;
	}

	//if up or down key is pressed for either player
	public void keyPressed(KeyEvent e) {
		switch (player) {
		
		//Making Player 1's keys W and S
		case 1:
			if (e.getKeyCode() == KeyEvent.VK_W) {
				setYDirection(-speed);
			}
			
			if (e.getKeyCode() == KeyEvent.VK_S) {
				setYDirection(speed);
			}
			
			break;
		
			//Making Player 2's keys W and S
		case 2:
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				setYDirection(-speed);
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				setYDirection(speed);
			}
			break;
		}
	}
	//When key is released
	
	public void keyReleased(KeyEvent e) {
		switch (player) {
		case 1:
			if (e.getKeyCode() == KeyEvent.VK_W) {
				setYDirection(0);
			}
			if (e.getKeyCode() == KeyEvent.VK_S) {
				setYDirection(0);
			}
			break;
		case 2:
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				setYDirection(0);
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				setYDirection(0);
			}
			break;
		}
	}

	//sets y direction for the paddles
	public void setYDirection(int yDirection) {
		yVelocity = yDirection;
	}

	//makes the paddle go in the direction of the keys
	public void move() {
		y += yVelocity;
	}

	//draws paddles graphg
	public void draw(Graphics g) {
		if (player == 1)
			g.setColor(Color.black);
		else
			g.setColor(Color.red);
		g.fillRect(x, y, width, height);
	}
}