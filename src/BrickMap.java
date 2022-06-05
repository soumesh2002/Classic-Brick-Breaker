import java.awt.*;

public class BrickMap {
    public int map[][];
    public int brickWidth;
    public int brickHeight;

    // generate map: bricks
    public BrickMap(int r, int c) { // r: row, c: col 
        map = new int[r][c];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                map[i][j] = 1;
            }
        }

        brickWidth = 540/c; // column
        brickHeight = 150/r; // row
    }

    public void draw(Graphics2D g) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] > 0) {
                    g.setColor(Color.MAGENTA);
                    g.fillRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);

                    // if we want to run the game even if the bricks
                    // are gone, we have to use BasicStroke()
                    // it defines a basic set of rendering attributes

                    g.setStroke(new BasicStroke(3));
                    g.setColor(Color.black);
                    g.drawRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
                }
            }
        }
    }

    public void setBrickValue(int value, int row, int col) {
        map[row][col] = value;
    }
}
