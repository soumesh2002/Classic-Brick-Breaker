import javax.swing.JPanel;
import java.awt.event.KeyListener;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Game extends JPanel implements KeyListener, ActionListener {

    private boolean play = false;
    private int score = 0; // starting score

    private int totalBricks = 21;

    private Timer time;
    private int delay = 8;

    // player
    private int playerX = 310; // starting position of player

    // Ball
    private int ballPosX = 120;
    private int ballPosY = 350;

    // Ball Direction
    private int ballXdir = -1;
    private int ballYdir = -2;

    // brick map renderer
    private BrickMap map;

    public Game() {
        map = new BrickMap(3, 7);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        time = new Timer(delay, this);
        time.start();
    }

    public void paint(Graphics g) {

        // background
        g.setColor(Color.black);
        g.fillRect(1, 1, 692, 592);

         // generating the map
         map.draw((Graphics2D)g); 

        // borders
        g.fillRect(0, 0, 3, 592);
        g.fillRect(0, 0, 692, 3);
        g.fillRect(691, 1, 3, 592);

        // drawing the paddle
        g.setColor(Color.green);
        g.fillRect(playerX, 550, 100, 8);

        // drawing the ball
        g.setColor(Color.orange);
        g.fillOval(ballPosX, ballPosY, 20, 20);

        g.setColor(Color.white);
        g.setFont(new Font("Consolas", Font.ITALIC, 20));
        g.drawString(""+score, 590, 30);

        if (totalBricks <= 0) {
            play = false;
            ballXdir = 0;
            ballYdir = 0;
            g.setColor(Color.red);
            g.setFont(new Font("Consolas", Font.BOLD, 20));
            g.drawString("press enter to restart the game, score: " + score, 230, 350);
        }

        if (ballPosY > 570) {
            play = false;
            ballXdir = 0;
            ballYdir = 0;
            g.setColor(Color.RED);
            g.setFont(new Font("Consolas", Font.BOLD, 30));
            g.drawString("game over score: " + score, 190, 300);

            g.setColor(Color.RED);
            g.setFont(new Font("Consolas", Font.BOLD, 20));
            g.drawString("press (enter) to restart", 230, 350);
        }

        g.dispose();
        // dispose() used to cause the JFrame window to
        // be destroyed and cleaned up by the OS.

    }

    @Override
    public void actionPerformed(ActionEvent e) { 
        time.start();

        if(play) {			
			if(new Rectangle(ballPosX, ballPosY, 20, 20).intersects(new Rectangle(playerX, 550, 30, 8))) {
				ballYdir = -ballYdir;
				ballXdir = -2;
			} else if(new Rectangle(ballPosX, ballPosY, 20, 20).intersects(new Rectangle(playerX + 70, 550, 30, 8))) {
                ballYdir = -ballYdir;
                ballXdir = ballXdir + 1;
            } else if (new Rectangle(ballPosX, ballPosY, 20, 20).intersects(new Rectangle(playerX + 70, 550, 30, 8))) {
                ballYdir = -ballYdir;
                ballXdir = ballXdir + 1;
            } else if (new Rectangle(ballPosX, ballPosY, 20, 20).intersects(new Rectangle(playerX + 30, 550, 40, 8))) {
                ballYdir = -ballYdir;
            }

            // check brick collision with the ball
            A: for (int i = 0; i < map.map.length; i++) {
                for (int j = 0; j < map.map[0].length; j++) {
                    if (map.map[i][j] > 0) {
                        // score
                        int brickX = j * map.brickWidth + 80;
                        int brickY = i * map.brickHeight + 50;
                        int brickWidth = map.brickWidth;
                        int brickHeight = map.brickHeight;

                        Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);					
						Rectangle ballRect = new Rectangle(ballPosX, ballPosY, 20, 20);
						Rectangle brickRect = rect;

                        if(ballRect.intersects(brickRect)) {					
							map.setBrickValue(0, i, j);
							score+=5;	
							totalBricks--;
							
							// when ball hit right or left of brick
							if(ballPosX + 19 <= brickRect.x || ballPosX + 1 >= brickRect.x + brickRect.width) {
								ballXdir = -ballXdir;
							}
							// when ball hits top or bottom of brick
							else {
								ballYdir = -ballYdir;				
							}
							
							break A;
                        }
                    }
                }
            }
            ballPosX += ballXdir;
            ballPosY += ballYdir;

            if (ballPosX < 0) {
                ballXdir = -ballXdir;
            } if (ballPosY < 0) {
                ballYdir = -ballYdir;
            } if (ballPosX > 670) {
                ballXdir = -ballXdir;
            }

            repaint();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (playerX >= 600) {
                playerX = 600;
            } else {
                moveRight();
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (playerX < 10) {
                playerX = 10;
            } else {
                moveLeft();
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!play) {
                play = true;
                ballPosX = 120;
                ballPosY = 350;
                ballXdir = -1;
                ballYdir = -2;
                playerX = 310;
                score = 0;
                totalBricks = 21; // 7x3
                map = new BrickMap(3, 7);
                repaint();
            }
        }
    }

    private void moveLeft() {
        play = true;
        playerX -= 20;
    }

    private void moveRight() {
        play = true;
        playerX += 20;
    }


}
