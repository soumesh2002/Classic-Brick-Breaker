/*
 * Brick Breaker Game - 1.0
 * @author: Soumesh Khuntia
 * 
 * Implementation of classic brick breaker game in Java using swing
 */

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class App {
    public static void main(String[] args) {

        /*Constructor */
        JFrame J = new JFrame();
        Game game_ = new Game();

        J.setBounds(10, 10, 700, 600); /*game window size 700x600 */

        // dialog box title
        J.setTitle("Brick Breaker Game");

        // the frame would have fixed width and height
        J.setResizable(false);

        J.setVisible(true);
        J.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        J.add(game_);
        
    }
}