package batailleNavale.model;

import java.awt.Point;


public class Submarine extends Vessel implements java.io.Serializable {
    private static final long serialVersionUID = 649618530420010451L;


    public Submarine() {
        super("S", 1, 1); // Power and size
        super.setFullname("SousMarin");
    }

    /**
     * Constructor
     * @param p board
     * @param vertical vertical or horizontal
     */
    public Submarine(Point p, boolean vertical) {
        super(p, vertical, 1);
        super.setName("S");
        super.setPower(1);
        super.setFullname("SousMarin");
    }

}
