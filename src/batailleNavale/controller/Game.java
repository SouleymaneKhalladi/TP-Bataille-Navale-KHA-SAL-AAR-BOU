package batailleNavale.controller;

import batailleNavale.model.Board;
import batailleNavale.view.TerminalBoardView;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import static java.lang.Thread.sleep;

public class Game implements java.io.Serializable{
    public static void play(Board boardPlayer, Board boardComputer, Player player, Player cpu, TerminalBoardView view) throws InterruptedException {
        // Initilisation du timer
        Timer sw = new Timer();
        sw.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Il vous reste : " + player.getTime() + " secondes.");
                if (player.getTime() <= 0) { // Si le temps est écoulé
                    System.out.println("Le temps est ecoulé ! Vous avez perdu ! ");
                    cancel();
                    System.exit(0);
                }
                player.setTime(player.getTime() - 30);
            }
        }, 1000, 30000);

        // Lancement de la vue
        view.displayBoards();
        System.out.println("La partie commence ! Temps restant : 10 minutes");
        String action = "Faites votre choix : \n "+
                        "┌────────────────┐\n" +
                        "| Choix          |\n" +
                        "|────────────────|\n" +
                        "| 1 -> Tirer     |\n" +
                        "| 2 -> Déplacer  |\n" +
                        "| 3 -> Quitter   |\n" +
                        "└────────────────┘\n";
        Scanner sc = new Scanner(System.in);
        int choice;
        while (!(player.checkWin(boardComputer)) || !(cpu.checkWin(boardPlayer))) { // Tant que personne n'a gagné la partie, on repropose de faire un choix
            if (player.checkShipMoving() == false) { // Si le joueur ne peux plus déplacer ses navires, on lui proposera seulement de tirer ou quitter
                action = "Faites votre choix : \n" +
                        "┌────────────────┐\n" +
                        "| Choix          |\n" +
                        "|────────────────|\n" +
                        "| 1 -> Tirer     |\n" +
                        "| 3 -> Quitter   |\n" +
                        "└────────────────┘\n";
                System.out.println(action);
                choice = sc.nextInt();
                while ((choice != 1) && (choice != 3)) {
                    System.out.println("Saisie incorrect. Veuillez choisir un chiffre entre : [1-3]");
                    System.out.println(action);
                    choice = sc.nextInt();
                }
            } else {
                System.out.println(action);
                choice = sc.nextInt();
                while (choice < 1 && choice > 3) {
                    System.out.println("Saisie incorrect. Veuillez choisir un chiffre entre : [1-2-3]");
                    System.out.println(action);
                    choice = sc.nextInt();
                }
            }

            switch (choice) {
                case 1:
                    player.hit(boardComputer); // Le joueur tire sur une case
                    System.out.println("Score du joueur : " + player.getScore() + " points");
                    break;
                case 2:
                    player.moving(); // Le joueur déplace un navire
                    System.out.println("Le score du joueur est de : " + player.getScore() + " points");
                    break;
                case 3:
                    System.out.println("[Sauvegarde de votre plateau en cours]");
                    Backup.save("PartieNavalePlayer", boardPlayer, "board", 0);
                    Backup.save("ScorePlayer", null, "score", player.getScore());
                    Backup.save("TimePlayer", null, "score", player.getTime());
                    System.out.println("Sauvegarde réussi !");

                    System.out.println("[Sauvegarde du plateau de l'ordinateur en cours]");
                    Backup.save("PartieNavaleCpu", boardComputer, "board", 0);
                    System.out.println("Sauvegarde réussi !");

                    System.out.println("Vous avez quitté la partie");
                    System.exit(0); // Quitte le programme
                    break;
            }
            if (player.checkWin(boardComputer)) { // On check si le joueur a gagné
                System.out.println("Vous avez fini le jeu en : " + (600 - player.getTime()) + " secondes." +
                        "Le joueur a gagné ! Avec un total de : " + (player.getScore() * (600- player.getTime())) + " points.");
                System.exit(0);
            }
            sleep(2000);
            cpu.jouer(boardPlayer);
            if (cpu.checkWin(boardPlayer)) { // On check si l'ordi a gagné
                System.out.println("Vous avez perdu la partie, l'ordinateur est vainqueur !" +
                        "┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼\n" +
                        "███▀▀▀██┼███▀▀▀███┼███▀█▄█▀███┼██▀▀▀\n" +
                        "██┼┼┼┼██┼██┼┼┼┼┼██┼██┼┼┼█┼┼┼██┼██┼┼┼\n" +
                        "██┼┼┼▄▄▄┼██▄▄▄▄▄██┼██┼┼┼▀┼┼┼██┼██▀▀▀\n" +
                        "██┼┼┼┼██┼██┼┼┼┼┼██┼██┼┼┼┼┼┼┼██┼██┼┼┼\n" +
                        "███▄▄▄██┼██┼┼┼┼┼██┼██┼┼┼┼┼┼┼██┼██▄▄▄\n" +
                        "┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼\n" +
                        "███▀▀▀███┼▀███┼┼██▀┼██▀▀▀┼██▀▀▀▀██▄┼\n" +
                        "██┼┼┼┼┼██┼┼┼██┼┼██┼┼██┼┼┼┼██┼┼┼┼┼██┼\n" +
                        "██┼┼┼┼┼██┼┼┼██┼┼██┼┼██▀▀▀┼██▄▄▄▄▄▀▀┼\n" +
                        "██┼┼┼┼┼██┼┼┼██┼┼█▀┼┼██┼┼┼┼██┼┼┼┼┼██┼\n" +
                        "███▄▄▄███┼┼┼─▀█▀┼┼─┼██▄▄▄┼██┼┼┼┼┼██▄\n" +
                        "┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼\n" +
                        "┼┼┼┼┼┼┼┼██┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼██┼┼┼┼┼┼┼┼┼\n" +
                        "┼┼┼┼┼┼████▄┼┼┼▄▄▄▄▄▄▄┼┼┼▄████┼┼┼┼┼┼┼\n" +
                        "┼┼┼┼┼┼┼┼┼▀▀█▄█████████▄█▀▀┼┼┼┼┼┼┼┼┼┼\n" +
                        "┼┼┼┼┼┼┼┼┼┼┼█████████████┼┼┼┼┼┼┼┼┼┼┼┼\n" +
                        "┼┼┼┼┼┼┼┼┼┼┼██▀▀▀███▀▀▀██┼┼┼┼┼┼┼┼┼┼┼┼\n" +
                        "┼┼┼┼┼┼┼┼┼┼┼██┼┼┼███┼┼┼██┼┼┼┼┼┼┼┼┼┼┼┼\n" +
                        "┼┼┼┼┼┼┼┼┼┼┼█████▀▄▀█████┼┼┼┼┼┼┼┼┼┼┼┼\n" +
                        "┼┼┼┼┼┼┼┼┼┼┼┼███████████┼┼┼┼┼┼┼┼┼┼┼┼┼\n" +
                        "┼┼┼┼┼┼┼┼▄▄▄██┼┼█▀█▀█┼┼██▄▄▄┼┼┼┼┼┼┼┼┼\n" +
                        "┼┼┼┼┼┼┼┼▀▀██┼┼┼┼┼┼┼┼┼┼┼██▀▀┼┼┼┼┼┼┼┼┼\n" +
                        "┼┼┼┼┼┼┼┼┼┼▀▀┼┼┼┼┼┼┼┼┼┼┼▀▀┼┼┼┼┼┼┼┼┼┼┼\n" +
                        "┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼");
                System.exit(0);
            }
            sleep(1000);
        }
    }
}
