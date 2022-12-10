package batailleNavale.controller;

import batailleNavale.model.Box;
import batailleNavale.model.BoxHit;
import batailleNavale.model.EmptyBox;
import batailleNavale.model.Destroyer;
import batailleNavale.model.Board;
import batailleNavale.model.ShipPart;
import batailleNavale.model.Vessel;
import batailleNavale.view.TerminalBoardView;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Player implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private static final char caractereDepart = 'A';
    private boolean display;
    private Board player;
    private TerminalBoardView view;
    private int score;
    private int time;

    /**
     * Constructeur, met aussi à jour la vue
     *
     * @param b true c'est un joueur / false un ordinateur
     * @param p plateau
     */
    public Player(boolean b, Board p) {
        this.display = b;
        this.player = p;
        this.view = new TerminalBoardView(b, getPlayer()); // si true donc c'est le plateau joueur sinon plateau ordi
    }

    /**
     * Constructeur
     *
     * @param b true si c'est un joueur
     */
    public Player(boolean b) {
        this.display = b;
    }

    /**
     * Met à jour le Joueur avec son plateau et la vue
     *
     * @param p plateau
     */
    public void setPlayer(Board p) {
        this.player = p;
        this.getView().setPlayer(p);
    }

    /**
     * Affiche seulement les messages d'erreur si c'est le joueur qui joue
     *
     * @param msg Message personnalisé pour joueur seulement
     */
    public void msgPlayer(String msg) {
        if (this.isDisplay() == true) { // si true alors c'est le joueur donc on afficher les msg d'erreur
            System.out.println(msg);
        }
    }

    /**
     * Vérifie si la saisie est correcte
     *
     * @param choix A0 par exemple
     * @return true si le choix est valide
     */
    public boolean validChoice(String choix) {// verifie si le choix est conforme par rapport aux lettres et chiffres
        choix = choix.toUpperCase(); // met la saisie de l'utilisateur en MAJUSCULE
        switch (choix.length()) {
            case 2:
                // si 2 caracteres dans la saisie (exemple 'A7') alors :
                return ((choix.charAt(0) >= 'A' && choix.charAt(0) <= 'O') && (choix.charAt(1) >= '0' && choix.charAt(1) <= '9')); //verifie si la saisie est entre [A-O] (premier caractere) et [1-9] (deuxieme caractere)	
            case 3:
                // si 3 caracteres dans la saisie (exemple 'F14') alors :
                return ((choix.charAt(0) >= 'A' && choix.charAt(0) <= 'O') && choix.charAt(1) == '1' && (choix.charAt(2) >= '0' && choix.charAt(2) <= '4'));
            default:
                return false;
        }
    }

    /**
     * Saisie random et controlé de l'ordi
     *
     * @return une string qui sera par la suite convertie en ligne et colonne
     */
    public String cpuInput() { // Saisie de l'ordi
        Random r = new Random();
        String saisie = "";
        int aleatoireL = r.nextInt(this.getPlayer().getHeight());
        int aleatoireC = r.nextInt(this.getPlayer().getLength());
        char ligne = 'A';
        ligne += aleatoireL;
        saisie = saisie + ligne + aleatoireC;
        return saisie;
    }

    /**
     * Saisie controle du joueur
     *
     * @param mode Message d'erreur personnalisé
     * @return une string qui sera par la suite convertie en ligne et colonne
     */
    public String playerInput(String mode) {
        String moving = ""; // le choix de la case selon l'affichage sur le terminale
        if (this.isDisplay()) {
            Scanner choice = new Scanner(System.in);
            this.msgPlayer(mode); // cet affichage provisoire
            moving = choice.nextLine();
            while (validChoice(moving) == false) // il resaisie jusqu'a que la saisie soit correcte
            {
                this.msgPlayer("Choix non conforme ! Veuillez choisir entre -> [A-O][0-14] : ");
                moving = choice.nextLine();
            }
        } else { // coté ordi
            moving = cpuInput();
            while (validChoice(moving) == false) {
                moving = cpuInput();
            }
        }
        return moving.toUpperCase(); // On met en maj pour que les autres fonctions reprennent à partir de la
    }

    /**
     * Savoir si un joueur a gagné
     *
     * @param p plateau
     * @return true si le joueur a gagné
     */
    public boolean checkWin(Board p) { // return un boolean si le joueur ou ordi a gagné
        for (int i = 0; i < p.getHeight(); i++) {
            for (int j = 0; j < p.getLength(); j++) {
                if (p.getBoard(i, j) instanceof ShipPart) // si la case est une partie de navire
                {
                    if (((ShipPart) p.getBoard(i, j)).isTouched() == false) {//si le partie de navire n'est pas touché on renvoie false directement car la partie n'est pas terminée
                        return false;
                    }
                }
            }
        }
        return true; // si apres avoir parcouru tout le plateau il n'a pas retourné un false alors toutes les parties du navires sont touchées.
    }

    /**
     * Demande au joueur de choisir une case de son plateau
     *
     * @param p plateau
     * @param mode Message d'erreur personnalisé
     * @return une case que le joueur / ordi a choisi
     */
    public Box boxChoice(Board p, String mode) {
        String choice = playerInput(mode);
        int column = choice.charAt(0) - caractereDepart;
        String lineChoice = choice.substring(1, choice.length());
        int line = Integer.parseInt(lineChoice);
        Box boxChoice = p.getBoard(line, column);
        boxChoice.setColumn(column);
        boxChoice.setLine(line);
        return boxChoice;
    }

    /**
     * Demande au joueur de choisir un navire
     *
     * @param p plateau
     * @param mode Message d'erreur personnalisé
     * @return la partie de navire du navire choisie
     */
    public ShipPart shipChoice(Board p, String mode) {
        Box shipChoice = boxChoice(p, mode);
        while (!(shipChoice instanceof ShipPart)) {
            this.msgPlayer("Ce n'est pas un navire");
            shipChoice = boxChoice(p, mode);
        }
        ShipPart pn = (ShipPart) shipChoice;
        return pn;
    }

    /**
     * Verifie si le navire n'est pas coulé pour qu'il puisse tirer avec
     *
     * @param p plateau
     * @param mode Message d'erreur personnalisé
     * @return une partie du navire du navire choisie
     */
    public ShipPart checkIfShipIsAlive(Board p, String mode) { //pour tirer
        ShipPart pn = shipChoice(p, mode);
        while (pn.isTouched()) {
            this.msgPlayer("Choissisez un navire non touché.");
            pn = shipChoice(p, mode);
        }
        return pn;
    }

    /**
     * Verifie si le navire selectionné est intact pour le déplacer
     *
     * @param p plateau
     * @param mode Message d'erreur personnalisé
     * @return le navire qui doit sera déplacé
     */
    public ShipPart checkIfShipIsIntact(Board p, String mode) { // pour déplacement
        ShipPart pn = shipChoice(p, mode);
        while (isNotTouched(pn) == false) {// le navire est endommagé
            this.msgPlayer("Le navire ne peut être déplacé, veuillez choisir un navire intact.");
            pn = checkIfShipIsAlive(p, mode);
        }
        return pn;
    }

    /**
     * Verifie si la case où le joueur veut tirer est valide
     *
     * @param targetShoot case
     * @param n navire
     * @return true si la case est valide
     */
    public boolean hitChoice(Box targetShoot, Vessel n) { // return un boolean pour savoir si la case ou il veut tirer est valide
        if (targetShoot instanceof EmptyBox) {
            return true;
        } else if (targetShoot instanceof ShipPart) {
            ShipPart pn = (ShipPart) targetShoot;
            if (pn.isTouched()) {
                this.msgPlayer("La partie de ce navire est déjà touchée");
                return false;
            }
            if (n.getName() != "S" && pn.getN().getName() == "S") { // si le navire choisi pour tirer n'est pas un sous marin et que la case ou le jouer veut tirer est un sous marin
                this.msgPlayer("Vous ne pouvez pas tirer sur cette case.(Sûrement un sous-marin)");
                return false;
            } else if (n.getName() == "S" && ((pn.getN().getName() == "S") || (pn.getN().getName() != "S"))) {
                return true;
            } else if (n.getName() != "S" && pn.getN().getName() != "S") {
                return true;
            } else if (n.getName() == "D" && ((Destroyer) n).getEclairer() == 1 && pn.getN().getName() == "S") { // si il a choisi un destroyeur et qu'il lui reste une fusée
                return true;
            }
        }
        return false;
    }

    /**
     * Verifie si les cases autour peuvent etre tiré ou pas
     *
     * @param hitBox case
     * @param n navire
     * @return true si la case est valide
     */
    public boolean checkIfBoxesAroundCanBeTouched(Box hitBox, Vessel n) {
        if (hitBox instanceof EmptyBox) {
            return true;
        } else if (hitBox instanceof ShipPart) {
            ShipPart pn = (ShipPart) hitBox;
            if (n.getName() != "S" && pn.getN().getName() == "S") { // si le navire choisi pour tirer n'est pas un sous marin et que la case ou le jouer veut tirer est un sous marin
                return false;
            } else if (n.getName() == "S" && ((pn.getN().getName() == "S") || (pn.getN().getName() != "S"))) {
                return true;
            } else if (n.getName() != "S" && pn.getN().getName() != "S") {
                return true;
            }
        }
        return false;
    }

    /**
     * Verifie si le partie de navire n'est pas touché
     *
     * @param pn Partie de navire
     * @return true si le partie de navire n'est pas touchée
     */
    public boolean isNotTouched(ShipPart pn) {
        ShipPart[] tab = pn.getN().getShipPart();
        int i = 0;
        while (i < tab.length) {
            if (tab[i].isTouched() == true) {
                return false;
            }
            i++;
        }
        return true;
    }

    /**
     * Verifie le déplacement en ligne
     *
     * @param indice ligne
     * @return true si le déplacement en ligne est possible
     */
    public boolean checkOnLineMoving(int indice) {
        return (indice >= 0) && (indice < this.getPlayer().getHeight());
    }

    /**
     * Verifie le déplacement en colonne
     *
     * @param index colonne
     * @return true si le déplacement en colonne est possible
     */
    public boolean checkOnColumnMoving(int index) {
        return (index >= 0) && (index < this.getPlayer().getLength());
    }

    /**
     * Verifie les déplacements possible du navire choisie
     *
     * @param p plateau
     * @param pn partie de navire
     * @return une HashMap avec les directions possible. Clé :
     * ['droite','gauche','haut','bas] et valeur : un Point
     */
    public Map<String, Point> checkMoving(Board p, ShipPart pn) {
        Vessel vessel = pn.getN();
        Point start = pn.getN().getPoint();
        int size = vessel.getSize();
        int start_x = (int) start.getX();
        int start_y = (int) start.getY();
        int end_x = (int) start_x + size - 1;
        int end_y = (int) start_y + size - 1;
        Map<String, Point> direction = new HashMap<>();

        if (vessel.isVertical() == true) {
            if (checkOnLineMoving(start_x - 1)) {
                if ((p.getBoard(start_x - 1, start_y) instanceof EmptyBox)) {
                    this.msgPlayer("Déplacement possible vers le haut");
                    direction.put("haut", new Point(start_x - 1, start_y));
                }
            }
            if (checkOnLineMoving(end_x + 1)) {
                if ((p.getBoard(end_x + 1, start_y) instanceof EmptyBox)) {
                    this.msgPlayer("Déplacement possible vers le bas");
                    direction.put("bas", new Point(end_x + 1, start_y));
                }
            }
        } else if (vessel.isVertical() == false) {
            if (checkOnColumnMoving(start_y - 1)) {
                if ((p.getBoard(start_x, start_y - 1) instanceof EmptyBox)) {
                    this.msgPlayer("Déplacement possible vers la gauche");
                    direction.put("gauche", new Point(start_x, start_y - 1));
                }
            }
            if (checkOnColumnMoving(end_y + 1)) {
                if ((p.getBoard(start_x, end_y + 1) instanceof EmptyBox)) {
                    this.msgPlayer("Déplacement possible vers la droite");
                    direction.put("droite", new Point(start_x, end_y + 1));
                }
            }
        }
        return direction;
    }

    /**
     * Verifie le déplacement que fait le joueur à partir du HashMap
     *
     * @param direction HashMap
     * @return un hashMap avec la direction choisie et son point
     */
    public Map<String, Point> choiceDirection(Map<String, Point> direction) { // saisie controlee de la direction que le joueur met
        String choice = "";
        if (this.isDisplay()) {
            Scanner sc = new Scanner(System.in);
            this.msgPlayer("Veuillez choisir la direction du déplacement");
            choice = sc.next();
            while (!direction.containsKey(choice)) { // direction contient les direction possibles
                this.msgPlayer("Veuillez choisir une direction disponible.");
                choice = sc.next();
            }
        } else { //ordi
            String[] tabDirection = {"haut", "bas", "droite", "gauche"};
            Random r = new Random();
            int index = r.nextInt(4);
            choice = tabDirection[index];
            while (!direction.containsKey(choice)) {
                index = r.nextInt(4);
                choice = tabDirection[index];
            }
        }
        Map<String, Point> choixHash = new HashMap<>();
        choixHash.put(choice, direction.get(choice));
        return choixHash; // on retourne un hashMap avec la direction choisie + son point d'arrivée
    }

    /**
     * Verifie si au moins un navire n'est pas touchée dans le plateau
     *
     * @param p plateau
     * @return un compteur, si le compteur superieur à 0 alors il y a au moins
     * un navire intacte
     */
    public int checkIfAShipIsTouched(Board p) {
        int compteur = 0;
        for (int i = 0; i < p.getHeight(); i++) { //boucle imbriqué parcourir le plateau
            for (int j = 0; j < p.getLength(); j++) {
                if (p.getBoard(i, j) instanceof ShipPart) {
                    if (isNotTouched((ShipPart) p.getBoard(i, j))) { // si c'est true le navire est intacte
                        compteur++;
                    }
                }
            }
        }
        return compteur; // si le compteur > 0 donc ya au moins un navire intact ( pas endommagé)
    }

    /**
     * Vérifie si le joueur peut faire l'action 'déplacer()'
     *
     * @return true si le joueur peut se déplacer
     */
    public boolean checkShipMoving() {
        if (checkIfAShipIsTouched(this.getPlayer()) == 0) { // On check si il n'y a pas au moin un navire non-endommagé
            this.msgPlayer("Aucun navire ne peut etre déplacer");
            return false;
        }
        return true;
    }

    /**
     * Deplacement controlé
     */
    public void moving() { //reste a verifier si apres que les navires peuvent faire un deplacement (sils sont pas bloqués)
        if (this.isDisplay()) { // si le bool true c'est donc le joueur
            getView().displayPlayerBoard();
        } else {
            System.out.println(getView());
            //vue.affichePlateauOrdi();
        }

        Map<String, Point> direction = new HashMap<>();
        ShipPart pn = checkIfShipIsIntact(this.getPlayer(), "Choissisez un navire que vous voulez déplacer"); //choisie un navire pour le déplacer il doit être intact
        direction = checkMoving(this.getPlayer(), pn);
        while (direction.isEmpty()) {
            this.msgPlayer("Le navire ne peut être déplacé. Choissisez un autre navire");
            pn = checkIfShipIsIntact(this.getPlayer(), "Choissisez un navire que vous voulez déplacer");
            direction = checkMoving(this.getPlayer(), pn);
        }
        if (this.isDisplay() == false) { //ordinateur
            System.out.println("L'ordinateur a choisi de déplacer son navire : " + pn.getN().getFullname());
        }
        direction = choiceDirection(direction); // recupere une hashMap (ex: si l'utilisateur ecrit 'gauche' on a gauche en clé et la case ou le deplacement doit etre fait en valeur
        Vessel n = pn.getN();
        ShipPart tmp; // recupere l'indice du partieNavire et le met dans le deplacement
        if (direction.containsKey("bas")) {
            tmp = (ShipPart) this.getPlayer().getBoard((int) n.getPoint().getX(), (int) n.getPoint().getY());
            this.getPlayer().setBoard(tmp, (int) direction.get("bas").getX(), (int) direction.get("bas").getY()); // ajoute a la fin la nouvelle partie de navire
            this.getPlayer().setBoard(new EmptyBox(), (int) n.getPoint().getX(), (int) n.getPoint().getY()); //bas // enleve le debut du partie de navire
            n.setPoint((int) n.getPoint().getX() + 1, (int) n.getPoint().getY()); // pour changer le point debut du navire
        } else if (direction.containsKey("haut")) {
            tmp = (ShipPart) this.getPlayer().getBoard((int) n.getPoint().getX() + n.getSize() - 1, (int) n.getPoint().getY());
            this.getPlayer().setBoard(tmp, (int) direction.get("haut").getX(), (int) direction.get("haut").getY());
            this.getPlayer().setBoard(new EmptyBox(), (int) n.getPoint().getX() + n.getSize() - 1, (int) n.getPoint().getY()); //haut
            n.setPoint((int) n.getPoint().getX() - 1, (int) n.getPoint().getY()); //desfois le getPartieNavire() et a 0 ou a taille - 1 ça depend de quelle case on enleve avant
        } else if (direction.containsKey("gauche")) {
            tmp = (ShipPart) this.getPlayer().getBoard((int) n.getPoint().getX(), (int) n.getPoint().getY() + n.getSize() - 1); //recupere l'indice de la case qui va être supprimé
            this.getPlayer().setBoard(tmp, (int) direction.get("gauche").getX(), (int) direction.get("gauche").getY());
            this.getPlayer().setBoard(new EmptyBox(), (int) n.getPoint().getX(), (int) n.getPoint().getY() + n.getSize() - 1); //gauche
            n.setPoint((int) n.getPoint().getX(), (int) n.getPoint().getY() - 1);
        } else if (direction.containsKey("droite")) {
            tmp = (ShipPart) this.getPlayer().getBoard((int) n.getPoint().getX(), (int) n.getPoint().getY());
            this.getPlayer().setBoard(tmp, (int) direction.get("droite").getX(), (int) direction.get("droite").getY());
            this.getPlayer().setBoard(new EmptyBox(), (int) n.getPoint().getX(), (int) n.getPoint().getY()); //droite
            n.setPoint((int) n.getPoint().getX(), (int) n.getPoint().getY() + 1);
        }
        if (this.isDisplay()) { // si le bool true c'est donc le joueur
            getView().displayPlayerBoard();
        } else {
            System.out.println(getView()); // Plateau de l'ordinateur visible pour la démo
            System.out.println("L'ordinateur a deplacé un de ses navires.");
            //vue.affichePlateauOrdi(); //affiche le plateau de l'ordi mais selon le joueur donc que des cases vides.
        }
    }

    /**
     * Verifie les indices cases autours de la case cible tirer
     *
     * @param cible case
     * @param i ligne
     * @param j colonne
     * @return true si les indices ne dépasse pas le plateau
     */
    public boolean checkTargetAroundBoard(Box cible, int i, int j) { // Retourne true / false pour voir si il dépasse le plateau
        int line = cible.getLine() + i;
        int column = cible.getColumn() + j;
        return (line >= 0) && (line < this.getPlayer().getHeight()) && (column >= 0) && (column < this.getPlayer().getLength());
    }

    /**
     * Tirer controlé
     *
     * @param p plateau
     */
    public void hit(Board p) {
        if (this.isDisplay()) { // si le bool true c'est donc le joueur
            getView().setPlayer(this.getPlayer());
            getView().setCpu(p);
            getView().displayPlayerBoard();
        } else {
            getView().setCpu(this.getPlayer());
            getView().setPlayer(p);
            System.out.println(getView()); // pour les demos on montre le plateau de l'ordinateur avec ses navires visibles
            //vue.affichePlateauOrdi(); //affiche le plateau de l'ordi mais selon le joueur donc que des cases vides.
        }
        ShipPart pn = checkIfShipIsAlive(this.getPlayer(), "Choissisez un navire avec lequel vous voulez tirer"); //choisie un navire pour tirer
        Vessel vessel = pn.getN();
        int line = 0;
        int column = 0;
        if (this.isDisplay()) { // si le bool true c'est donc le joueur
            //vue.affichePlateauOrdi(); // quand c'est le joueur on montre le plateau de l'ordi (navire non visible) mais quand c'est l'ordi pas la peine d'afficher la grille du joueur
            System.out.println(getView());
        }
        Box targetHit = boxChoice(p, "Veuillez choisir une case où vous voulez tirer");
        while (hitChoice(targetHit, vessel) == false) {
            targetHit = boxChoice(p, "Veuillez choisir une case où vous voulez tirer");
        }
        Box targetAround = p.getBoard(targetHit.getLine(), targetHit.getColumn());
        if (vessel.getName().equals("c") || vessel.getName().equals("C")) { //croiseur et cuirasse
            int indiceTirer = 0;
            if (vessel.getName().equals("c")) {
                indiceTirer = 2;
            } else if (vessel.getName().equals("C")) {
                indiceTirer = 3;
            }
            for (int i = 0; i < indiceTirer; i++) {
                for (int j = 0; j < indiceTirer; j++) {
                    if (checkTargetAroundBoard(targetHit, i, j)) { // verifier les cases autour de la case tirer
                        line = targetHit.getLine() + i;
                        column = targetHit.getColumn() + j;
                        targetAround = p.getBoard(line, column);
                        targetAround.setLine(line);
                        targetAround.setColumn(column);
                        if (checkIfBoxesAroundCanBeTouched(targetAround, vessel)) {
                            if (targetAround instanceof ShipPart) {
                                ((ShipPart) targetAround).setTouched(true);
                                this.score++; // on incremente le score de 1 a chaque partie de navire touché
                            }
                            if (targetAround instanceof EmptyBox) {
                                p.setBoard(new BoxHit(), targetAround.getLine(), targetAround.getColumn());
                            }
                        }
                    }
                }
            }
        }
        if (vessel.getName().equals("D") || vessel.getName().equals("S")) { // destroyeur et sous-marin
            if (vessel.getName().equals("D") && ((Destroyer) vessel).getEclairer() == 1) { // si il a choisit le destroyeur et qu'il a encore une fusée eclairante
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        if (checkTargetAroundBoard(targetHit, i, j)) { // verifier les cases autour de la case tirer
                            line = targetHit.getLine() + i;
                            column = targetHit.getColumn() + j;
                            targetAround = p.getBoard(line, column);
                            targetAround.setLine(line);
                            targetAround.setColumn(column);
                            targetAround.setDisplayDestroyer(true);
                        }
                    }
                }
                ((Destroyer) vessel).setEclairer(0); // il a lancé une fois sa fusée éclairante donc mtn il en a plus
                if (this.isDisplay()) { // si le bool true c'est donc le joueur
                    this.msgPlayer("Vous avez choisie un destroyeur avec une fusée éclairante. Vous l'avez utilisé dans le plateau de l'ordinateur : \n");
                    getView().displayCpuBoard(); //affiche le plateau de l'ordi mais selon le joueur donc que des cases vides.
                }
                return; // quitte la methode car il a tirer une fois avec sa fusée éclairante avec un destroyeur
            }
            if (checkIfBoxesAroundCanBeTouched(targetHit, vessel)) {
                if (targetHit instanceof ShipPart) {
                    ((ShipPart) targetHit).setTouched(true);
                    this.score++; // on incremente le score de 1 a chaque partie de navire touché
                }
                if (targetHit instanceof EmptyBox) {
                    p.setBoard(new BoxHit(), targetHit.getLine(), targetHit.getColumn());
                }
            }
        }
        if (this.isDisplay()) { // si le bool true c'est donc le joueur

            System.out.println(getView()); // pour les demos on montre le plateau de l'ordinateur avec ses navires visibles
            //vue.affichePlateauOrdi(); //affiche le plateau de l'ordi mais selon le joueur donc que des cases vides.
        } else {
            getView().displayPlayerBoard();
        }
    }

    /**
     * Jouer controlé pour ordinateur
     *
     * @param boardPlayer plateau du joueur
     */
    public void jouer(Board boardPlayer) {
        Random rand = new Random();
        int choice;
        if (this.checkShipMoving()) { // si l'ordi peut toujour se déplacer alors random entre 1 et 2
            choice = 1 + rand.nextInt(2);
        } else {
            choice = 1; // si l'ordi peut pas se deplacer alors choix par defaut à 1 donc il pourra que tirer
        }
        switch (choice) {
            case 1:
                System.out.println("L'ordinateur a choisi de tirer sur le plateau du joueur.");
                this.hit(boardPlayer);
                break;
            case 2:
                System.out.println("L'ordinateur a choisi de se déplacer.");
                this.moving();
                break;
        }
    }

    /**
     * Verifie si c'est le joueur qui joue ou l'ordinateur
     *
     * @return true si c'est bien le joueur sinon c'est l'ordinateur
     */
    public boolean isDisplay() {
        return display;
    }

    /**
     * Set si c'est le joueur ou ordinateur
     *
     * @param display the affichage to set
     */
    public void setDisplay(boolean display) {
        this.display = display;
    }

    /**
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * @param score the score to set
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * @return the joueur
     */
    public Board getPlayer() {
        return player;
    }

    /**
     * @return the vue
     */
    public TerminalBoardView getView() {
        return view;
    }

    /**
     * @param view the vue to set
     */
    public void setView(TerminalBoardView view) {
        this.view = view;
    }

    /**
     * @return the time
     */
    public int getTime() {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(int time) {
        this.time = time;
    }
}
