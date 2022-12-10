package batailleNavale.model;

import java.awt.Point;

public class Destroyer extends Vessel implements java.io.Serializable {
    private static final long serialVersionUID = 7996729821609277950L;

    private int eclairer = 1;

    /**
     * Constructeur
     */
    public Destroyer() {
        super("D", 1, 3); // Power and size
        super.setFullname("Destroyeur");
    }

    /**
     *
     * @param p Point
     * @param vertical vertical or horizontal
     */
    public Destroyer(Point p, boolean vertical) {
        super(p, vertical, 3);
        super.setName("D");
        super.setPower(1);
        super.setFullname("Destroyeur");
    }

    /**
     * @return the eclairer
     */
    public int getEclairer() {
        return eclairer;
    }

    /**
     * @param eclairer the eclairer to set
     */
    public void setEclairer(int eclairer) {
        this.eclairer = eclairer;
    }

}
