package Bataille_Navale;

public class placement {
    
    private int ligne;
    public int getLigne() {
        return ligne;
    }

    public void setLigne(int ligne) {
        this.ligne = ligne;
    }

    private int colonne;
    public int getColonne() {
        return colonne;
    }

    public void setColonne(int colonne) {
        this.colonne = colonne;
    }

    public placement() {
        
    }
    

    public placement(int ligne ,int colonne) {
        this.ligne = ligne;
        this.colonne=colonne;
    }
}
