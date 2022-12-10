package batailleNavale.model;

import java.awt.Point;
public abstract class Vessel implements java.io.Serializable{
    private static final long serialVersionUID = 183229727626299550L;
    
    protected int size;
    protected int power;
    protected String name; // Nom formaté avec un caractère
    protected String fullname; // Ship full name
    private ShipPart[] shipPart; // Ship part
    private boolean isVertical;
    private Point start;

    /**
     * Constructor
     * @param name ship name
     * @param power ship power
     * @param size ship size
     */
    public Vessel(String name, int power, int size) {
        this.name = name;
        this.size = size;
        this.power = power;
        this.shipPart = new ShipPart[size];
        for (int i = 0; i < size; i++) {
            this.shipPart[i] = new ShipPart(this, i); // le this dans new ShipPart(this) est pour envoyer les propriétés du navire
        }
    }

    /**
     * Constructor
     * @param p Point
     * @param verticale vertical or horizontal
     * @param size ship size
     */
    public Vessel(Point p, boolean verticale, int size) {
        this.shipPart = new ShipPart[size];
        this.isVertical = verticale;
        this.size = size;
        this.start = p;
        for (int i = 0; i < size; i++) {
            this.shipPart[i] = new ShipPart(this, i); // le this dans new PartieNavire(this) c'est pour envoyer les proprietes du navire
        }
    }

    /**
     * Constructor
     * @param v vessel
     */
    public Vessel(Vessel v) {
        this.size = v.size;
        this.power = v.power;
        this.name = v.name;
    }

    /**
     * @return the size of the vessel
     */
    public int getSize() {
        return size;
    }

    /**
     *
     * @return the start point of the ship
     */
    public Point getPoint() {
        return this.start;
    }

    /**
     *
     * @param i line
     * @param j column
     */
    public void setPoint(int i, int j) {
        this.start = new Point(i, j);
    }

    /**
     *
     * @param p Point
     */
    public void setPoint(Point p) {
        this.start = p;
    }

    /**
     * @return the power
     */
    public int getPower() {
        return power;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param size the size to set
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * @param power the power to set
     */
    public void setPower(int power) {
        this.power = power;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return if isVertical
     */
    public boolean isVertical() {
        return isVertical;
    }

    /**
     * @param vertical the isVertical to set
     */
    public void setVertical(boolean vertical) {
        this.isVertical = vertical;
    }

    /**
     * @param k ship part index
     * @return the affected part
     */
    public ShipPart getShipPart(int k) {
        return shipPart[k];
    }

    /**
     *
     * @return a table of ship part
     */
    public ShipPart[] getShipPart() {
        return shipPart;
    }

    /**
     * @param shipPart set ship part of the vessel
     */
    public void setPartieTouchees(ShipPart[] shipPart) {
        this.shipPart = shipPart;
    }
    
    /**
     *
     * @return ship's full name
     */
    public String getFullname() {
        return this.fullname;
    }
    
    /**
     *
     * @param nc set ship's full name
     */
    public void setFullname(String nc) {
        this.fullname = nc;
    }

    /**
     *
     * @return display reduced name of the ship, for example "D" for "Destroyer"
     */
    public String toString() {
        return this.name;
    }
}
