package Bataille_Navale;
import java.util.*;


public class GrilleJ {

    private int a;
    private int b;
    private char[][] g;
    Random r = new Random();
    

    public GrilleJ(int n, int m){
        
        a=n;
        b=m;
        g = new char[a][b];

        for(int j=0; j<a; j++){
            for(int i=0; i<b;i++){

                g[i][j]='~';
            }
        }
    }

    
    
    
    
    public void afficher(){
        System.out.println("GRILE DU JOUEUR :");
        System.out.println("\t   A   B   C   D   E   F   G   H   I   J   K   L   M   N   O");
        int nb = 0;
        for(int j=0; j<a; j++){
            System.out.print(nb + "\t");    //affichage des numéros de cases
            for(int i=0; i<b;i++){
                System.out.print( " | " + g[i][j]);     //affichage des cases vides du joueur
            }       
            System.out.println(" | ");
            nb++;
        }
        System.out.println();
        
    }
  

}
