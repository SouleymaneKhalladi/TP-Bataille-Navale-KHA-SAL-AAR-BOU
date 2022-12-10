package batailleNavale.controller;

import batailleNavale.model.Board;
import batailleNavale.view.TerminalBoardView;

import java.util.Scanner;

import static java.lang.Thread.sleep;

public class Menu implements java.io.Serializable{
    public static void menu() throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Bienvenue dans la bataille navale mobile\n");

        System.out.println("La taille du plateau sera du 15 x 15 !");

        Board boardPlayer = new Board(15, 15); // Création du plateau du joueur
        boardPlayer.initBoard();
        boardPlayer.fillBoard();
        Player player = new Player(true, boardPlayer); // Création du joueur
        player.setTime(10*60); // Set Stopwatch to the player

        Board boardCpu = new Board(15, 15); // Création du plateau de l'ordinateur
        boardCpu.initBoard();
        boardCpu.fillBoard();
        Player cpu = new Computer(boardCpu); // Création de l'ordinateur

        TerminalBoardView vue = new TerminalBoardView(boardPlayer, boardCpu);

        int choice = 0; // Choix que saisira le joueur
        int i = 0; // Compteur
        System.out.println("Faites votre choix\n" +
                "┌───────────────────────────┐\n" +
                "|1. Jouer                   |\n" +
                "|2. Charger une partie      |\n" +
                "|3. Aide                    |\n" +
                "|4. Quitter la partie       |\n" +
                "└───────────────────────────┘");
        choice = sc.nextInt(); // Le joueur doit saisir un chiffre entre 1 et 4
        while (choice < 1 && choice > 4) // Tant que la saisie n'est pas bonne, on redemande à ce que le joueur saisisse
        {
            System.out.println("Votre saisie est incorrect, choisissez entre 1 et 4");
            choice = sc.nextInt();
        }

        switch (choice) {
            case 1:
                Game.play(boardPlayer, boardCpu, player, cpu, vue); // On lance la partie
                break;
            case 2:
                // On charge la partie du joueur à partir du fichier
                player = Backup.load("PartieNavalePlayer", "player");
                cpu = Backup.load("PartieNavaleCpu", "cpu");
                vue.setPlayer(player.getPlayer());
                vue.setCpu(cpu.getPlayer());
                Game.play(player.getPlayer(), cpu.getPlayer(), player, cpu, vue); // chargement fini, le joueur peut donc rejouer
                break;
            case 3:
                System.out.println(
                        "Bienvenue sur la plateforme d'aide aux joueurs :\n\n" +
                                "Au départ, vous vous trouverez dans le menu principal du jeu avec plusieurs choix possible\n" +
                                "\n" +
                                "-> Jouer\n" +
                                "Le joueur et l’ordinateur disposent chacun de deux grilles de 15*15 cases\n" +
                                "- Une grille n°1 pour positionner et visualiser ses navires\n" +
                                "- Une grille n°2 pour visualiser les dégâts causés à l’adversaire\n" +
                                "Chaque joueur possède une flotte de 10 navires : 1 cuirassé, 2 croiseurs, 3 destroyers et 4 sous-marins\n" +
                                "\n" +
                                "Caractéristique des navires\n" +
                                "- Chaque type de navire est de taille différente : \n" +
                                "┌─────────────────────┐\n" +
                                "| Type       | Taille |\n" +
                                "|─────────────────────|\n" +
                                "| Cuirassé     7 cases|\n" +
                                "| Croiseur   | 5 cases|\n" +
                                "| Destroyeur | 3 cases|\n" +
                                "| Sous-marin | 1  case|\n" +
                                "└─────────────────────┘\n"
                );
                sleep(1000);
                System.out.println(
                        "- Le positionnement des navires\n" +
                                "En début de jeu les navires sont positionnés aléatoirement et bien répartis dans la grille N° 1 de chaque\n" +
                                "joueur (voir ci-dessous des exemples). C’est le joueur humain qui commence la partie. Les navires\n" +
                                "peuvent être positionnés verticalement ou horizontalement sur la grille. Mais attention, deux navires\n" +
                                "ne peuvent occuper la même case\n" +
                                "- La puissance du tir\n" +
                                "┌───────────────────────────────┐\n" +
                                "| Type       | Puissance du tir |\n" +
                                "|───────────────────────────────|\n" +
                                "| Cuirassé   |           9 cases|\n" +
                                "| Croiseur   |           5 cases|\n" +
                                "| Destroyeur |           1 case |\n" +
                                "| Sous-marin |           1 case |\n" +
                                "└───────────────────────────────┘\n"
                );
                sleep(1000);
                System.out.println(
                        "Les actions d’un navire par tour de jeu et par joueur\n" +
                                "Chaque joueur (humain et ordinateur) joue à tour de rôle. Bien entendu, un joueur ne doit pas voir les\n" +
                                "grilles de son adversaire : seules les grilles du joueur en cours sont affichées.\n" +
                                "A chaque tour de jeu, les joueurs peuvent choisir l’une des 2 actions suivantes sur un seul navire de\n" +
                                "leur choix de la grille n° 1, en choisissant les coordonnées de l’une des cases du navire :\n\n" +
                                "1/ Tirer - Le joueur tire en choisissant les coordonnées d'une case valide de l'adversaire\n" +
                                "2/ Déplacer - Le joueur peut déplacer le navire d'une seul case sauf s'il est touché. Un navire ne peut pas se déplacer en diagonale.\n" +
                                "En cas d'obstacle, un navire ne pourra pas effectuer son déplacement et devra tenter une nouvelle action.\n\n" +
                                "A chaque tour de jeu, vous pourrez visualiser l'action, les coordonnées et le type du navire choisis par l'ordinateur.\n"
                );
                sleep(1000);
                System.out.println(
                        "Victoire, sauvegarde et chargement d'une partie\n\n" +
                                "Le premier qui a coulé toute la flotte de son adversaire a gagné la partie !\n" +
                                "A chaque instant, vous pouvez quitter la partie en cours avec une touche du clavier. La partie est\n" +
                                "alors sauvegardée dans un fichier texte. La sauvegarde comprend la position et le type de tous les\n" +
                                "navires restant et coulés des grilles.\n" +
                                "Pour charger une partie, il faut passer par le menu principal et choisir l'option Charger une partie.\n\n" +
                                "Fin de l'aide\n" +
                                "──────────────────────────────────────────────────────────────────────────────\n"
                );
                //////////////////////////////////////////////////////////////////////////
                System.out.println("Tapez 1 pour lancer le jeu, 2 pour retourner dans le menu principal");
                int opt = 0;
                opt = sc.nextInt();
                while (opt != 1 && opt != 2) // Si il saisis le mauvais chiffre
                {
                    System.out.println("Votre saisie est incorrect, 1 pour lancer le jeu, 2 pour retourner dans le menu");
                    opt = sc.nextInt();
                }
                if(opt == 1){
                    Game.play(boardPlayer, boardCpu, player, cpu, vue); // Lancement du jeu
                }
                else{
                    menu(); // Retour au menu
                }
                break;
            case 4:
                System.out.println("Sauvegarde du plateau Joueur");
                sleep(1000);
                Backup.save("PartieNavalePlayer", boardPlayer, "board", 0);
                Backup.save("ScoreJoueur", null, "score", player.getScore());
                Backup.save("TimeJoueur", null, "time", player.getTime());
                System.out.println("Sauvegarde réussi !");

                System.out.println("Sauvegarde du plateau Ordinateur");
                sleep(1000);
                Backup.save("PartieNavaleCpu", boardCpu, "board", 0);
                System.out.println("Sauvegarde réussi !");

                System.out.println("Vous avez quitté la partie");
                System.exit(0);
                break;
            default:
                break;
        }
    }
}
