package batailleNavale.model;

import java.awt.Point;


public class Battleship extends Vessel implements java.io.Serializable {
    private static final long serialVersionUID = 4972243516124434277L;
    
    /**
     * Constructeur
     */
    public Battleship(){
        super("C",9,7); // Power and size
        super.setFullname("Cuirasse");
    }
    
    /**
     *
     * @param p Point
     * @param vertical vertical or horizontal
     */
    public Battleship(Point p, boolean vertical){
        super(p, vertical, 7);
        super.setName("C");
        super.setPower(9);
        super.setFullname("Cuirasse");
    }
    
}
