



import java.awt.*;
import java.util.*;

public class Ball extends Rectangle {

	
	Random random;
	int xVelocity;// x direction
	int yVelocity;// y direction
	int initialSpeed = 5;// sets speed of ball

	// ball appears on screen centered
	
	
	Ball(int x, int y, int width, int height) {
	
		super(x, y, width, height);
		
		random = new Random();
		// makes random x direction
		
		int randomXDirection = random.nextInt(2);
		
		if (randomXDirection == 0)
			randomXDirection--;
		
		setXDirection(randomXDirection * initialSpeed);
		// makes random y direction
		
		int randomYDirection = random.nextInt(2);
		
		if (randomYDirection == 0)
			
			randomYDirection--;
		
		
		setYDirection(randomYDirection * initialSpeed);

	}

	// sets random x dir
	
	public void setXDirection(int randomXDirection) {
		
		
		xVelocity = randomXDirection;
		
	}

	// sets a y dir that is random
	public void setYDirection(int randomYDirection) {
		
		
		yVelocity = randomYDirection;
	}

	// updates direction of ball
	public void move() {
		
		
		x += xVelocity;
		
		
		y += yVelocity;
	}

	// ball graphic and colour
	public void draw(Graphics g) {
		
		g.setColor(Color.orange);
		
		
		g.fillOval(x, y, height, width);
		
	}

	public int yVelocity(int i) {
		
		
		
		return 0;
	}

}