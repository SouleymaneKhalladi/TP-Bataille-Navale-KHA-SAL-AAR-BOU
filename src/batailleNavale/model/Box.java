package batailleNavale.model;


public class Box implements java.io.Serializable{
    private static final long serialVersionUID = -4244087376389177399L;

    private int line;
    private int column;
    private boolean displayDestroyer = false;

    public Box() {
        
    }
    
    /**
     * Constructor
     * @param line line
     * @param column column
     */
    public Box(int line, int column) {
        this.line = line;
        this.column = column;
    }
    
    /**
     * @return the line
     */
    public int getLine() {
        return line;
    }

    /**
     * @param line the line to set
     */
    public void setLine(int line) {
        this.line = line;
    }

    /**
     * @return the column
     */
    public int getColumn() {
        return column;
    }

    /**
     * @param column the column to set
     */
    public void setColumn(int column) {
        this.column = column;
    }
    /**
     * @return the displayDestroyer
     */
    public boolean isDisplayDestroyer() {
        return displayDestroyer;
    }

    /**
     * @param displayDestroyer the displayDestroyer to set
     */
    public void setDisplayDestroyer(boolean displayDestroyer) {
        this.displayDestroyer = displayDestroyer;
    }
    
}
