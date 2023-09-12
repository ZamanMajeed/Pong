//Zaman Majeed
//Game panel made and configured


import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.Random;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable, KeyListener {

	static final int WINDOW_WIDTH = 1500;// width of window
	static final int WINDOW_HEIGHT = 800;// height of window
	
	
	static final Dimension SCREEN_SIZE = new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT);// dimension
	
	//paddle and ball dimensions
	static final int BALL_DIAMETER = 30;
	static final int PADDLE_WIDTH = 10; 
	static final int PADDLE_HEIGHT = 150;

	// threads used
	
	Thread gameThread;
	
	Image image;
	
	Graphics graphics;
	
	Random random;
	
	Paddle paddle1;
	
	Paddle paddle2;
	
	Ball ball;
	
	Score score;

	GamePanel() {
		
		
		
		newPaddles();// paddle created initially
		
		newBall();// ball created initially
		score = new Score(WINDOW_WIDTH, WINDOW_HEIGHT);// score initialized
		this.setFocusable(true);// make everything in this class appear on the screen
		
		
		this.addKeyListener(new ActionListener());// keyboard input setup
		
		this.setPreferredSize(SCREEN_SIZE);// screensize

		gameThread = new Thread(this);// run more than one class at once
		
		gameThread.start();
	}

	// produces the vall
	
	
	public void newBall() {
		
		random = new Random();
		ball=new Ball((WINDOW_WIDTH / 2) - (BALL_DIAMETER / 2),
			random.nextInt(WINDOW_HEIGHT - BALL_DIAMETER),
			BALL_DIAMETER, BALL_DIAMETER);
	}

	// produces the paddles
	public void newPaddles() {
		paddle1 = new Paddle(5, (WINDOW_HEIGHT / 2)-(PADDLE_HEIGHT / 2),PADDLE_WIDTH,PADDLE_HEIGHT,1);
		
		
		paddle2 = new Paddle(WINDOW_WIDTH - PADDLE_WIDTH-5,(WINDOW_HEIGHT / 2)-(PADDLE_HEIGHT / 2),PADDLE_WIDTH,
				PADDLE_HEIGHT, 2);
	}

	// updates the window
	public void paint(Graphics g) {
		image = createImage(getWidth(), getHeight());// draw off screen
		graphics = image.getGraphics();
		draw(graphics);
		g.drawImage(image,0,0, this);// redraws
	}

	// updates moving objects
	public void draw(Graphics g) {
		paddle1.draw(g);
		ball.draw(g);
		score.draw(g);
		paddle2.draw(g);
		
		
		
		Toolkit.getDefaultToolkit().sync(); 

	}

	// updates the postions as things move
	public void move() {
		paddle1.move();
		ball.move();
		paddle2.move();
		
	}

	// handles when things on screen collides
	public void checkCollision() throws InterruptedException, LineUnavailableException, IOException, UnsupportedAudioFileException{

		// bounce ball off top & bottom window edges
		if (ball.y <= 0) {
			ball.setYDirection(-ball.yVelocity);
			
		}
		if (ball.y >= WINDOW_HEIGHT - BALL_DIAMETER) {
			ball.setYDirection(-ball.yVelocity);
			Audio.playBounceAudio(); // Calling a method from Audio class to play a bounce sound
		}
		// bounce ball off paddles
		if (ball.intersects(paddle1)) {
			ball.xVelocity = Math.abs(ball.xVelocity);
			ball.xVelocity++;
			if (ball.yVelocity > 0)
				ball.yVelocity++;
			else
				ball.yVelocity--;
			ball.setXDirection(ball.xVelocity);
			ball.setYDirection(ball.yVelocity);
			Audio.playBounceAudio(); // Calling a method from Audio class to play a bounce sound
		}
		if (ball.intersects(paddle2)) {
			ball.xVelocity = Math.abs(ball.xVelocity);
			ball.xVelocity++;
			if (ball.yVelocity > 0)
				ball.yVelocity++;
			else
				ball.yVelocity--;
			ball.setXDirection(-ball.xVelocity);
			ball.setYDirection(ball.yVelocity);
			Audio.playBounceAudio(); // Calling a method from Audio class to play a bounce sound
		}

		// stops paddles at window edges
		
		if (paddle1.y <= 0)
			paddle1.y = 0;
		
		if (paddle1.y >= (WINDOW_HEIGHT - PADDLE_HEIGHT))
			paddle1.y = WINDOW_HEIGHT - PADDLE_HEIGHT;
		
		if (paddle2.y <= 0)
			paddle2.y = 0;
		
		if (paddle2.y >= (WINDOW_HEIGHT - PADDLE_HEIGHT))
			paddle2.y = WINDOW_HEIGHT - PADDLE_HEIGHT;
		
		// give a player 1 point and creates new paddles & ball
		if (ball.x <= 0 ) {
			score.player2++;
			newPaddles();
			newBall();
			
		}
		if (ball.x >= WINDOW_WIDTH - BALL_DIAMETER)  {
			score.player1++;
			newPaddles();
			newBall();
			
		}

		// restarts at 3 points
		if (score.win=true) {
			
			
			
			this.setFocusable(true);
			
			
			
		}
	}

	// runs without ending
	public void run() {
		// slows down the code
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		// game loop
		while (true) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1) {
				move();
				try {
					try {
						checkCollision();
					} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				repaint();
				delta--;
			}
		}
	}

	public class ActionListener extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			paddle1.keyPressed(e);
			paddle2.keyPressed(e);
		}

		public void keyReleased(KeyEvent e) {
			paddle1.keyReleased(e);
			paddle2.keyReleased(e);
		}
	}
	

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}