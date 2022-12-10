package batailleNavale.model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class Board implements java.io.Serializable {
    private static final long serialVersionUID = 3227240812366509718L;

    private Box[][] board;
    private int height;
    private int length;

    /**
     * Constructeur
     * @param height board height
     * @param length board length
     */
    public Board(int height, int length) {
        this.height = height;
        this.length = length;
        this.board = new Box[height][length];
    }

    /**
     * Ship initialization
     * @return a default initialized ship arrayList
     */
    public ArrayList<Vessel> initializationShip() {

        ArrayList<Vessel> listeVessel = new ArrayList<Vessel>();

        Vessel battleship = new Battleship();
        Vessel battleCruiser1 = new BattleCruiser();
        Vessel battleCruiser2 = new BattleCruiser();
        Vessel destroyer1 = new Destroyer();
        Vessel destroyer2 = new Destroyer();
        Vessel destroyer3 = new Destroyer();
        Vessel sousMarin1 = new Submarine();
        Vessel sousMarin2 = new Submarine();
        Vessel sousMarin3 = new Submarine();
        Vessel sousMarin4 = new Submarine();

        listeVessel.add(battleship);

        listeVessel.add(battleCruiser1);
        listeVessel.add(battleCruiser2);

        listeVessel.add(destroyer1);
        listeVessel.add(destroyer2);
        listeVessel.add(destroyer3);

        listeVessel.add(sousMarin1);
        listeVessel.add(sousMarin2);
        listeVessel.add(sousMarin3);
        listeVessel.add(sousMarin4);

        return listeVessel;
    }

    /**
     * VCheck if we can put the ship on the board
     * @param n ship
     * @return true if we can put the ship
     */
    public boolean checkPutShip(Vessel n) { // Check if we can put the ship on the board
        int ligne = (int) n.getPoint().getX();
        int colonne = (int) n.getPoint().getY();
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.length; j++) { // Boucle imbriqué pour parcourir le plateau
                if (ligne == i && colonne == j) {
                    for (int k = 0; k < n.getSize(); k++) {
                        if (n.isVertical()) {
                            if (!(getBoard(k + i, j) instanceof EmptyBox)) {
                                return false;
                            }
                        } else {
                            if (!(getBoard(i, k + j) instanceof EmptyBox)) {
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    /**
     * Put the ship on the board
     * @param n ship
     */
    public void putShip(Vessel n) {
        int ligne = (int) n.getPoint().getX();
        int colonne = (int) n.getPoint().getY();
        for (int i = 0; i < this.height; i++) { //boucle imbriqué pour parcourir le plateau
            for (int j = 0; j < this.length; j++) {
                for (int k = 0; k < n.getSize(); k++) {
                    if (ligne == i && colonne == j) {
                        if (n.isVertical()) {
                            this.board[k + i][j] = n.getShipPart(k);
                        } else {
                            this.board[i][k + j] = n.getShipPart(k);
                        }
                    }
                }
            }
        }
    }

    /**
     *  Check if the ship dont exceed the board's limit
     * @param line line
     * @param column column
     * @param size ship size
     * @param vertical true if vertical else horizontal
     * @return true if the board index is correct
     */
    public boolean checkBoardIndex(int line, int column, int size, boolean vertical) {
        if (vertical) {
            return (line + size >= 0) && (line + size < getHeight());
        } else if (vertical == false) {
            return (column + size >= 0) && (column + size < getLength());
        }
        return false;
    }

    /**
     * Control randomless ship parameters
     * @param n navire
     */
    public void controlCheckBoardIndex(Vessel n) {
        Random rand = new Random();
        int line = rand.nextInt(getHeight());
        int column = rand.nextInt(getLength());
        boolean vertical = rand.nextBoolean();
        Point p = new Point(line, column);
        while (checkBoardIndex(line, column, n.getSize(), vertical) == false) {
            line = rand.nextInt(getHeight());
            column = rand.nextInt(getLength());
            vertical = rand.nextBoolean();
            p = new Point(line, column);
        }
        n.setVertical(vertical);
        n.setPoint(p);
    }

    /**
     * Randomly fill the table with verification
     */
    public void fillBoard() {
        ArrayList<Vessel> listeVessel = new ArrayList<Vessel>();
        listeVessel = initializationShip(); // ship to put on the board
        Vessel n;
        for (int i = 0; i < listeVessel.size(); i++) {
            n = listeVessel.get(i);
            controlCheckBoardIndex(n);
            while (checkPutShip(n) == false) {
                controlCheckBoardIndex(n);
            }
            putShip(n);
        }
    }

    /**
     * Initializes baord to empty spaces
     */
    public void initBoard() { // initializes board to empty spaces
        for (int i = 0; i < this.height; i++) { // Boucle imbriqué pour parcourir le plateau
            for (int j = 0; j < this.length; j++) {
                this.board[i][j] = new EmptyBox();
            }
        }
    }

    /**
     * @return the board
     */
    public Box[][] getBoard() {
        return board;
    }

    /**
     * 
     * @param i line
     * @param j column
     * @return a box of the board with clues given
     */
    public Box getBoard(int i, int j) {
        return this.board[i][j];
    }

    /**
     * Set a box of the board with clues given
     * @param c box
     * @param i line
     * @param j column
     */
    public void setBoard(Box c, int i, int j) {
        this.board[i][j] = c;
    }

    /**
     * @param board the board to set
     */
    public void setBoard(Box[][] board) {
        this.board = board;
    }

    /**
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * @return the length
     */
    public int getLength() {
        return length;
    }

    /**
     * @param length the length to set
     */
    public void setLength(int length) {
        this.length = length;
    }

}
