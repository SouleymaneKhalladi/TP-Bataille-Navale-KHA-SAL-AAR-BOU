package batailleNavale.view;

import batailleNavale.model.Box;
import batailleNavale.model.Board;
import batailleNavale.model.BoxHit;
import batailleNavale.model.EmptyBox;
import batailleNavale.model.ShipPart;

public class TerminalBoardView implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    private Board player;
    private Board cpu;

    /**
     * View
     *
     * @param b true if player turn
     * @param p board
     */
    public TerminalBoardView(boolean b, Board p) {
        if (b) { // If it's player turn
            this.player = p;
        } else {
            this.cpu = p; // If it's cpu turn
        }
    }

    /**
     *
     * @param p player board
     * @param p2 cpu board
     */
    public TerminalBoardView(Board p, Board p2) {
        this.player = p;
        this.cpu = p2;
    }

    /**
     * @return the player
     */
    public Board getPlayer() {
        return player;
    }

    /**
     * @param player the player to set
     */
    public void setPlayer(Board player) {
        this.player = player;
    }

    /**
     * @return the cpu
     */
    public Board getCpu() {
        return cpu;
    }

    /**
     * @param cpu the cpu to set
     */
    public void setCpu(Board cpu) {
        this.cpu = cpu;
    }

    /**
     * To change color on the console compared to the box
     * @param c box
     * @return string color code
     */
    public String shipColor(Box c) {
        String color = "";
        if (c instanceof ShipPart) {
            ShipPart pn = (ShipPart) c;
            if(pn.getN().getName().equals("C")) {
                color = "\033[31m";
            } else if(pn.getN().getName().equals("c")) {
                color = "\033[36m";
            } else if(pn.getN().getName().equals("D")) {
                color = "\033[35m";
            } else if(pn.getN().getName().equals("S")) {
                color = "\033[34m";
            }
        }
        else if (c instanceof BoxHit) {
            color = "\033[30m";
        }
        else if (c instanceof EmptyBox) {
            color = "\033[30m";
        }
        return color;
    }

    /**
     *
     */
    public void displayPlayerBoard() {
        String s = "Plateau du joueur : \n";
        int l = 0;
        char c = 'A';
        s += "\t| ";
        for (int k = 0; k < this.player.getHeight(); k++) {
            s += c + " | ";
            c++;
        }
        s += "\n\t ";
        for (int k = 0; k < this.player.getLength(); k++) {
            s += " _  ";
        }
        s += "\n";
        for (int i = 0; i < this.player.getHeight(); i++) {
            s += l + "\t| "; // Display board digits
            for (int j = 0; j < this.player.getLength(); j++) // show board content
            {
                s += shipColor(this.getPlayer().getBoard()[i][j]) + this.getPlayer().getBoard()[i][j].toString();
                s += " | ";
            }
            s += "\n";
            l++; // ascii code for numbers
        }
        System.out.println(s);
    }

    /**
     *
     */
    public void displayCpuBoard() {
        String s = "Plateau de l'ordinateur : \n";
        int l = 0;
        char c = 'A';
        s += "\t| ";
        for (int k = 0; k < this.cpu.getHeight(); k++) {
            s += c + " | ";
            c++;
        }
        s += "\n\t ";
        for (int k = 0; k < this.cpu.getLength(); k++) {
            s += " _  ";
        }
        s += "\n";
        for (int i = 0; i < this.cpu.getHeight(); i++) {
            s += l + "\t| "; // Display board digits
            for (int j = 0; j < this.cpu.getLength(); j++) // show board content
            {
                if (this.getCpu().getBoard(i, j).isDisplayDestroyer() == false) {
                    if (this.getCpu().getBoard()[i][j] instanceof BoxHit || this.getCpu().getBoard()[i][j] instanceof EmptyBox) {
                        s +=  shipColor(this.getCpu().getBoard()[i][j]) + this.getCpu().getBoard()[i][j].toString() + " | ";
                    } else if (((ShipPart) this.getCpu().getBoard()[i][j]).isTouched()) {
                        s += "+" + " | ";
                    } else {
                        s += "~" + " | ";
                    }
                } else {
                    s += this.getCpu().getBoard(i, j).toString() + " | ";
                }
            }
            s += "\n";
            l++; // ascii code for numbers
        }
        System.out.println(s);
    }

    // Call 2 methods to display player and cpu boards
    /**
     *
     */
    public void displayBoards() {
        displayPlayerBoard();
        displayCpuBoard();
    }

    /**
     *
     * @return o display cpu board with his ships
     * visibles
     */
    public String toString() { // methodé temporaire pour les démos a faire lors de la soutenance.
        System.out.println("C'est le plateau de l'ordinateur");
        String s = "";
        int l = 0;
        char c = 'A';
        s += "\t| ";
        for (int k = 0; k < this.cpu.getHeight(); k++) {
            s += c + " | ";
            c++;
        }
        s += "\n\t ";
        for (int k = 0; k < this.cpu.getLength(); k++) {
            s += " _  ";
        }
        s += "\n";
        for (int i = 0; i < this.cpu.getHeight(); i++) {
            s += l + "\t| "; // afficher les chiffres de plateau
            for (int j = 0; j < this.cpu.getLength(); j++) // afficher le contenu du plateau
            {
                s += shipColor(this.getCpu().getBoard()[i][j]) + this.cpu.getBoard()[i][j].toString();
                s += " | ";
            }
            s += "\n";
            l++; // incremente le code ascii pour les chiffres
        }
        return s;
    }
}
