package batailleNavale.model;

import java.awt.Point;


public class BattleCruiser extends Vessel implements java.io.Serializable {
    private static final long serialVersionUID = 6047829828920103167L;

    /**
     * Constructeur
     */
    public BattleCruiser() {
        super("c", 4, 5); //puissance et taille
        super.setFullname("Croiseur");
    }

    /**
     *
     * @param p Point
     * @param vertical vertical or horizontal
     */
    public BattleCruiser(Point p, boolean vertical) {
        super(p, vertical, 5);
        super.setName("c");
        super.setPower(4);
        super.setFullname("Croiseur");
    }

}
