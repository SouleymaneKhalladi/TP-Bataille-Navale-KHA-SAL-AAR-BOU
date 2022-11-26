package Bataille_Navale;
import java.util.Scanner;
import static java.lang.Thread.sleep;


public class jouer {

    
 
    public static void menu() throws InterruptedException {

        //valeur donnée par l'utilisateur
        Scanner sc = new Scanner(System.in);
        System.out.println("Que voulez vous faire ? :\n1 -> Jouer une nouvelle partie \n2 -> Chargez une ancienne partie   \n3 -> A propos du jeu  \n4 -> Quitter le jeu ");

        //valeur entière prise pour les choix dans le menu
        int choix;
        //on affecte à choix la valeur saisie
        choix = sc.nextInt();

        //saisie du nombre conforme (1,2,3 ou 4)
        while(choix>4 || choix<1){
            System.out.println("Veuillez choisir entre 1, 2, 3, ou 4");
            choix = sc.nextInt();


        }
        switch (choix){

            //Choix 1 : lancement d'une nouvelle partie
            case 1:

            //definition de la taille du plateau :
            //longueur
            System.out.println("Saisissez la longeur du plateau (nombre de cases) : ");
            
            int longueur;
            Scanner scl = new Scanner(System.in);
            longueur = scl.nextInt();

            //saisie d'une longueur conforme
            while (!(longueur >= 8)) {
                System.out.println("Saisissez une longueur entière supérieur ou égale à 8");
                longueur = scl.nextInt();
            }
            
            //largeur
            System.out.println("Saisissez la largeur du plateau (nombre de cases) : ");

            int largeur;
            Scanner scL = new Scanner(System.in);
            largeur = scL.nextInt();

            //saisie d'une largeur conforme
            while (!(largeur >= 8)) {
                System.out.println("Saisissez une longueur entière supérieur ou égale à 8");
                longueur = scL.nextInt();
            }

            //lancer methode jeu


            //choix 2 : charger une partie
            case 2:
          

            //choix 3 : régles et commandes du jeu.
            case 3:
            
            System.out.println(""); System.out.println("Règles du jeu et commandes :");
            
            sleep(1500);
            
            System.out.println(""); System.out.println("But du jeu :"); System.out.println("");
            System.out.print("Le jeu de la bataille navale, commence en plaçant tous les navires dans une grille secrète. Chacun à leur tour,");
            System.out.print("les joueurs doivent trouver et couler les bateaux adverses, \nen communiquant les coordonnées visées composées d'une lettre puis d'un nombre.");
            System.out.print("le gagnant est le dernier joueur disposant de navires.");
            System.out.println("");System.out.println("");System.out.println("");
            sleep(2500);

            System.out.println("Depuis le menu : \nvous pouvez presser la touche 1 de votre clavier afin de jouer, \nla touche 2 pour charger une partie, \nou encore la touche 4 pour quitter le jeu. ");
            System.out.println("");System.out.println("");
            sleep(1500);
            System.out.println("Avant le lancement d'une partie vous serez invité à choisir la taille du plateau. Sachant que plus celui-ci est grand, plus la partie sera longue et compliquée");
            System.out.println("");
            sleep(2500);
            System.out.println("Le plateau contient : * Un cuirassé noté 'C' avec une force de frappe de 9 cases et de taille 7.");
            System.out.println("* Deux croiseurs noté 'c' avec une force de frappe de 4 cases et de taille 5.");
            System.out.println("* Trois destroyeurs noté 'D' avec une force de frappe de 1 case et de taille 3 avec une fusée éclairante pour chaques destroyeurs.");
            System.out.println("* Quatre SousMarin noté 'S' avec une force de frappe de 1 case et de taille 1. Seules les SousMarins peuvent couler d'autre SousMarin.");
            System.out.println("");System.out.println("");System.out.println("");
            sleep(2500);
        }
        
     

      
   
    }
    
    
    public static void main(String[] args) throws InterruptedException {
        System.out.println("                       $o");
        System.out.println("                       $                     Bataille Navale");
        System.out.println("                      $$$      .oo..     'oooo'oooo'ooooooooo....");
        System.out.println("                       $       $$$$$$$");
        System.out.println("                   .ooooooo.   $$!!!!!");
        System.out.println("                 .'.........'. $$!!!!!      o$$oo.   Khalladi  Salmi  Aarab  Boudaa");
        System.out.println("    $          .o'  oooooo   '.$$!!!!!      $$!!!!!       'oo''oooo''");
        System.out.println(" ..o$ooo...    $                '!!''!.     $$!!!!!");
        System.out.println(" $    ..  '''oo$$$$$$$$$$$$$.    '    'oo.  $$!!!!!");
        System.out.println(" !.......      '''..$$ $$ $$$   ..        '.$$!!''!");
        System.out.println(" !!$$$!!!!!!!!oooo......   '''  $$ $$ :o           'oo.");
        System.out.println(" !!$$$!!!$$!$$!!!!!!!!!!oo.....     ' ''  o$$o .      ''oo..");
        System.out.println(" !!!$$!!!!!!!!!!!!!!!!!!!!!!!!!!!!ooooo..      'o  oo..    $");
        System.out.println("  '!!$$!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!oooooo..  ''   ,$");
        System.out.println("   '!!$!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!oooo..$$");
        System.out.println("    !!$!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!$'");
        System.out.println("    '$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$!!!!!!!!!!!!!!!!!!,");
        System.out.println(".....$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$.....");
        System.out.println("");
        System.out.println("");
       
        menu(); 
    }

}
