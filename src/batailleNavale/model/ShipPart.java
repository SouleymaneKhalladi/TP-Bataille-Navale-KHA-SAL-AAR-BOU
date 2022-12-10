package batailleNavale.model;

public class ShipPart extends Box implements java.io.Serializable {
    private static final long serialVersionUID = 8161935903879376891L;

    private Vessel n;
    private boolean isTouched = false;
    private int index;

    /**
     * Empty Constructor
     */
    public ShipPart() {

    }

    /**
     *
     * @param n vessel
     * @param index vessel part index
     */
    public ShipPart(Vessel n, int index) {
        this.n = n;
        this.index = index;
    }

    /**
     * @return the n
     */
    public Vessel getN() {
        return n;
    }

    /**
     * @param n the n to set
     */
    public void setN(Vessel n) {
        this.n = n;
    }

    /**
     * @return the isTouched
     */
    public boolean isTouched() {
        return isTouched;
    }

    /**
     * @param touched the isTouched to set
     */
    public void setTouched(boolean touched) {
        this.isTouched = touched;
    }

    /**
     * @return the index
     */
    public int getIndex() {
        return index;
    }

    /**
     * @param index the index to set
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     *
     * @return if vessel part is touched : "+" else we display the reduced name of the ship like "D" for "Destroyeur"
     */
    public String toString() {
        if (this.isTouched == true) {
            return "+";
        } else {
            return this.n.toString();
        }
    }
}
