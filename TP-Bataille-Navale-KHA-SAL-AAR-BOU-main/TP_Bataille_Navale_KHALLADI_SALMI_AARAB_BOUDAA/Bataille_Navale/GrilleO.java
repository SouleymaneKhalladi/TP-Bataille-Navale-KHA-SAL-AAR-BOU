package Bataille_Navale;
import java.util.*;

public class GrilleO {

    private int a;
    private int b;
    private char[][] g;
    
    
    public GrilleO(int n, int m){
        
        a=n;
        b=m;
        g = new char[a][b];
        for(int j=0; j<a; j++){
            for(int i=0; i<b;i++){
                g[i][j]=' ';
            }
        }
    }
    
    
    
    
    public void afficher(){
        int nb=0;
        System.out.println("GRILLE DE L'ORDINATEUR :");
        System.out.println("\t   A   B   C   D   E   F   G   H   I   J   K   L   M   N   O");
        for(int j=0; j<a; j++){
            System.out.print(nb + "\t");        //affichage des numéros de cases
            for(int i=0; i<b;i++){
                System.out.print( " | " + g[i][j]);     //affichage ds cases vides de l'ordi
            }
            System.out.println(" | ");
            nb++;
        }
        System.out.println();
        
    }
    
}
