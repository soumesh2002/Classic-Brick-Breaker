import javax.swing.JPanel;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

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


    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        
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
