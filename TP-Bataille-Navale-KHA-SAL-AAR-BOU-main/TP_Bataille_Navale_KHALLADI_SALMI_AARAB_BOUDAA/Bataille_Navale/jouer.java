package Bataille_Navale;
import Bataille_Navale.GrilleJ;
import java.util.Scanner;
import static java.lang.Thread.sleep;


public class jouer {


    
    public static void action(){
        int action;
        Scanner ac = new Scanner(System.in);
        System.out.println("Que voulez vous faire ? :\n1 -> Tirer \n2 -> Déplacer un navire   \n3 -> Quitter le jeu ");
        action=ac.nextInt();
        
        switch(action){
            case 1 :
            //tirer
            break;
            
            case 2:
            //deplacer
            break;

            case 3:
            System.exit(0);
            break;
        }
    }
 
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

            GrilleJ g1 = new GrilleJ(15,15); //joueur
            g1.afficher();
            

            GrilleO g2 = new GrilleO(15,15);
            g2.afficher();

            action();

            break;
            //choix 2 : charger une partie
            case 2:
          
            break;
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
            System.out.println("Avant le lancement d'une partie vous serez invité à choisir la taille du plateau. Sachant que plus celui-ci est grand, plus la partie sera longue et compliquée.");
            System.out.println("");
            sleep(2500);
            System.out.println("Le plateau contient : \n* Un cuirassé noté 'C' avec une force de frappe de 9 cases et une taille de 7 cases.");
            System.out.println("* Deux croiseurs noté 'c' avec une force de frappe de 4 cases et une taille de  5 cases.");
            System.out.println("* Trois destroyeurs noté 'D' avec une force de frappe de 1 case et une taille de 3 cases, avec une fusée éclairante pour chaques destroyeurs.");
            System.out.println("* Quatre SousMarin noté 'S' avec une force de frappe de 1 case et une taille de 1 cases. Seules les SousMarins peuvent couler d'autre SousMarin.");
            System.out.println("");System.out.println("");System.out.println("");
            sleep(2500);
            System.out.println("Pour jouer, plusieurs actions vous serons proposées. Toujours à l'aide de votre clavier vous pourrez : ");System.out.println("");
            System.out.println("1- Tirer. Pour ce faire vous devrez choisir dans un premier temps avec quelle bateau vous voulez tirer et  ensuite, choisir quelle case vous voulez viser.");
            System.out.println("2- Déplacer une navire. Pour ce faire, sélectionner dans un premier temps un navire non touché que vous voulez déplacer d'une case dans le sens du navire.");
            System.out.println("les cases vides touchés sont marquées d'un : O. Les cases touchés contenant un navire sont marquées d'un : X. ");
            System.out.println(""); System.out.println(""); System.out.println("Bon jeu !");System.out.println("");System.out.println("");
            sleep(2500);
                
            menu();

            break;
            case 4:
            System.exit(0);
            break;
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
