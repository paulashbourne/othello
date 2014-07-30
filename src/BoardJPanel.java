/*
 * Project Name: Othello
 * Author: Paul Ashbourne
 * Function: Computer version of the Othello board game
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class BoardJPanel extends javax.swing.JPanel {

    int[][] cellFilled = new int[8][8]; //0 denotes empty, 1 held by black, 2 held by white
    int stepValue; //int variable to store distance between cells
    int tileDiameter; //int variable to store diameter of each tile
    int turn = 1; //int variable denoting the current turn
    int gameover = 0; //int variable denoting whether the game is over (0 no, 1 black wins, -1 white wins, 2 tie)
    int[] mousPos = null; //int array denoting x and y position of mouse in grid
    int blackCount = 2; //int variable to store number of black tiles
    int whiteCount = 2; //int variable to store number of white tiles

    @Override
    public void paintComponent(Graphics g) {

        
        //draw background
        g.setColor(new Color(0, 102, 0));
        g.fillRect(0, 0, getWidth(), getHeight());

        //calculate distance between cells
        if (this.getWidth() * 2 / 3 < this.getHeight()) {
            stepValue = (this.getWidth() * 2) / 24 - 4;
        } else {
            stepValue = this.getHeight() / 8 - 4;
        }

        //calculate diameter of tiles
        tileDiameter = stepValue - (int) (stepValue * 0.2);

        //draw grid
        g.setColor(Color.black);
        for (int c = 0; c < 9; c++) {
            int x = c * stepValue + 16;
            int y = c * stepValue + 16;
            g.fillRect(x - 1, 16, 3, stepValue * 8);
            g.fillRect(16, y - 1, stepValue * 8, 3);
        }

        //fill cells held by black or white
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (cellFilled[x][y] != 0) {
                    drawTile(g, x, y, cellFilled[x][y]);
                }
            }
        }

        //fill cell currently covered by mouse
        if (mousPos != null) {
            if (cellFilled[mousPos[0]][mousPos[1]] == 0) {
                drawTile(g, mousPos[0], mousPos[1], turn);
            }
        }
        
        //draw score counts
        drawTile(g, 8, 0, 1);
        drawTile(g, 8, 1, -1);
        g.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, (int) (tileDiameter * 1.5)));
        g.drawString(" " + blackCount, 16 + (int) (stepValue * 8.1) + tileDiameter, 16 + (int) (stepValue * 0.1) + tileDiameter);
        g.drawString(" " + whiteCount, 16 + (int) (stepValue * 8.1) + tileDiameter, 16 + (int) (stepValue * 1.1) + tileDiameter);

        //draw spaces left counter
        g.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, (int) (tileDiameter * 0.8)));
        g.setColor(Color.RED);
        g.drawString("Spaces left: " + (64 - blackCount - whiteCount), 16 + (int) (stepValue * 8.1), 16 + (stepValue * 7) + tileDiameter);

        g.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, (int) (tileDiameter * 0.9)));
        if (gameover == 0) { //check if game is still in play
            //update status display with current turn
            if (turn == -1) { //check if white's turn
                g.setColor(Color.WHITE);
                g.drawString("White's turn", 16 + (int) (stepValue * 8.1), 16 + (stepValue * 2) + tileDiameter);
            } else { //black's turn
                g.setColor(Color.BLACK);
                g.drawString("Black's turn", 16 + (int) (stepValue * 8.1), 16 + (stepValue * 2) + tileDiameter);
            }
        } else {
            //display result of game
            if (gameover == 1) { //check if black wins
                g.setColor(Color.BLACK);
                g.drawString("Black wins!", 16 + (int) (stepValue * 8.1), 16 + (stepValue * 2) + tileDiameter);
            } else if (gameover == -1) { //check if white wins
                g.setColor(Color.WHITE);
                g.drawString("White wins!", 16 + (int) (stepValue * 8.1), 16 + (stepValue * 2) + tileDiameter);
            } else { //tie
                g.setColor(Color.BLACK);
                g.drawString("It's a tie!", 16 + (int) (stepValue * 8.1), 16 + (stepValue * 2) + tileDiameter);
            }
            
            //draw new game button
            g.setColor(Color.RED);
            g.drawString("NEW GAME", 16 + (int) (stepValue * 8.1), 16 + (stepValue * 3) + tileDiameter);
        }
    }

    private void drawTile(Graphics g, int x, int y, int player) {
        if (player == 1) { //determine tile colour
            g.setColor(Color.black);
        } else {
            g.setColor(Color.white);
        }
        g.fillOval(16 + (int) (stepValue * 0.1) + (x * stepValue), 16 + (int) (stepValue * 0.1) + (y * stepValue), tileDiameter, tileDiameter); //draw tile
    }

    /**
     * Creates new form BoardJPanel
     */
    public BoardJPanel() {
        initComponents();
        //loop through all cells, set to empty
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                cellFilled[x][y] = 0;
            }
        }

        //fill initial cells
        cellFilled[3][3] = -1;
        cellFilled[3][4] = 1;
        cellFilled[4][3] = 1;
        cellFilled[4][4] = -1;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(0, 102, 0));
        setPreferredSize(new java.awt.Dimension(300, 300));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                formMouseMoved(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private int countFlips(int x, int y, int p, int deltax, int deltay) {
        int count = 0; //int variable denoting number of flips
        int o = p * -1; //int variable denoting opponent
        int x1 = x; //int variable denoting current x-cell position
        int y1 = y; //int variable denoting current y-cell position

        //while next cell is held by opponent
        while ((x1 + deltax) >= 0 && (x1 + deltax) < 8 && (y1 + deltay) >= 0
                && (y1 + deltay) < 8 && cellFilled[x1 + deltax][y1 + deltay] == o) {
            x1 += deltax; //increment y position
            y1 += deltay; //increment x position
            count++; //increment flip counting variable
        }
        //check if next cell is held by player
        if ((x1 + deltax) >= 0 && (x1 + deltax) < 8 && (y1 + deltay) >= 0
                && (y1 + deltay) < 8 && cellFilled[x1 + deltax][y1 + deltay] == p) {
            return count; //return number of flips
        } else {
            return 0; //end cell not held by player, no flips in direction
        }
    }

    private void cellClicked(int x, int y) {
        if (cellFilled[x][y] == 0) { //check if cell is empty
            boolean moveValid = false; //boolean variable denoting whether move is valid
            for (int deltax = -1; deltax < 2; deltax++) { //loop through all directions in x
                for (int deltay = -1; deltay < 2; deltay++) { //loop through all directions in y
                    if (deltax != 0 || deltay != 0) { //skip if x and y are both zero
                        int flips = countFlips(x, y, turn, deltax, deltay); //count number of flips in direction
                        if (flips > 0) { //check if flips is greater than zero
                            moveValid = true; //move is valid, update variable
                            int x1 = x; //int variable denoting position in x
                            int y1 = y; //int variable denoting position in y
                            for (int pos = 0; pos < flips; pos++) { //loop through number of flips in direction
                                x1 += deltax; //increment x
                                y1 += deltay; //increment y
                                cellFilled[x1][y1] = turn; //flip tile
                            }
                        }
                    }
                }
            }
            if (moveValid) { //check if move is valid
                cellFilled[x][y] = turn; //play tile
                countTiles();
                if (movePossible(turn * -1)) { //check if move exists for opponent
                    turn = turn * -1; //opponent's turn
                } else if (movePossible(turn)) { //check if move exists for player
                    //player has another turn
                    System.out.println("No moves for opponent, play again");
                } else { //no moves for either player
                    repaint();
                    gameEnded(); //game ended
                    return; //exit subroutine
                }
                repaint(); //repaint panel

            }
        }
    }

    private void countTiles() {
        //reset counting variables
        blackCount = 0;
        whiteCount = 0;
        for (int x = 0; x < 8; x++) { //loop through all columns
            for (int y = 0; y < 8; y++) { //loop through all rows
                if (cellFilled[x][y] == 1) { //check if cell held by black
                    blackCount++; //increment black counter
                } else if (cellFilled[x][y] == -1) { //check if cell held by white
                    whiteCount++; //increment white counter
                }
            }
        }
    }

    private void gameEnded() {
        if (blackCount > whiteCount) { //check if more cells held by 
            gameover = 1;
        } else if (blackCount == whiteCount) { //check for tie
            gameover = 2;
        } else { //check if more cells held by white
            gameover = -1;
        }
        repaint();
    }

    private void newGame() {
        //loop through all cells, set to empty
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                cellFilled[x][y] = 0;
            }
        }

        //fill initial cells
        cellFilled[3][3] = -1;
        cellFilled[3][4] = 1;
        cellFilled[4][3] = 1;
        cellFilled[4][4] = -1;

        //reset variables
        turn = 1;
        gameover = 0;
        blackCount = 2;
        whiteCount = 2;
        
        repaint();
    }

    private boolean movePossible(int p) {
        for (int x = 0; x < 8; x++) { //loop through all colums
            for (int y = 0; y < 8; y++) { //loop through all rows
                for (int deltax = -1; deltax < 2; deltax++) { //loop through all directions in x
                    for (int deltay = -1; deltay < 2; deltay++) { //loop through all directions y
                        if (cellFilled[x][y] == 0) { //check if cell is empty
                            if (deltax != 0 || deltay != 0) { //skip if x and y are both zero
                                if (countFlips(x, y, p, deltax, deltay) > 0) { //check if number of flips in direction is greater than 0
                                    return true; //return true
                                }
                            }
                        }
                    }
                }
            }
        }
        return false; //no moves for player, return false
    }

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        if (gameover == 0) { //check if game is still in play
            int[] cellCoord = getCell(evt.getX(), evt.getY()); //get cell of click, store in memory
            if (cellCoord != null) { //check if click is within grid
                cellClicked(cellCoord[0], cellCoord[1]); //attempt play on that cell
            }

        } else {
            if ((evt.getX() >= (16 + (8 * stepValue))) && (evt.getY() <= (16 + (stepValue * 4)))
                    && (evt.getY() > 16 + (stepValue * 3))) { //check for new game click
                newGame();
            }
        }
    }//GEN-LAST:event_formMouseClicked

    private void formMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseMoved
        if (gameover == 0) { //check if game is still in play
            int[] cellCoord = getCell(evt.getX(), evt.getY()); //get cell mouse currently in, store in memory
            if (cellCoord != null) { //check if mouse is within grid
                mousPos = new int[2];
                mousPos[0] = cellCoord[0];
                mousPos[1] = cellCoord[1];
            } else {
                mousPos = null;
            }
            repaint();
        }
    }//GEN-LAST:event_formMouseMoved

    private int[] getCell(int x, int y) {
        //check if click is outside of grid
        if (x <= 17 || y <= 17 || x >= (16 + (8 * stepValue)) || y >= (16 + (8 * stepValue))) {
            return null; //return null value
        }
        int[] cell = {0, 0}; //int array to store x and y coordinates of cell
        x -= 16; //subtract left border from click
        y -= 16; //subtract top border from y
        while (x >= stepValue) { //loop while x is larger than or equal to distance between cells
            x -= stepValue; //subtract distance between cells from x
            cell[0]++; //increment cell x position
        }
        while (y >= stepValue) { //loop while y is larger than or equal to distance between cells
            y -= stepValue; //subtract distance between cells from y
            cell[1]++; //increment cell y position
        }
        return cell; //return cell coordinate array
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
