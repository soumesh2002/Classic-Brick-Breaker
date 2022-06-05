public class BrickMap {
    public int map[][];
    public int brickWidth[];
    public int brickHeight[];

    // generate map: bricks
    public BrickMap(int r, int c) { // r: row, c: col 
        map = new int[r][c];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                map[i][j] = 1;
            }
        }
    }
}
