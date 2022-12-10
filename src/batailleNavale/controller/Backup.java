package batailleNavale.controller;

import Main.Partie;
import batailleNavale.model.Board;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Backup implements java.io.Serializable{
    public static void save(String file, Board player, String action, int value) {
        /*
            Pour sauvegarder le plateau, il faut file, player et action => board
            Pour sauvegarder le score, il faut file, action => score, value => valeur du score
            Pour sauvegarder le time, il faut file, action => time, value => valeur du time
         */
        switch (action){
            case "board":
                try {
                    FileOutputStream fos = new FileOutputStream(file);
                    ObjectOutputStream o = new ObjectOutputStream(fos);
                    o.writeObject(player);
                    o.flush();
                    o.close();
                } catch (java.io.IOException e) {
                    e.printStackTrace();
                }
                break;
            case "score": // Si la condition == score ou time, execute la fonction suivante
            case "time":
                try {
                    FileOutputStream fos = new FileOutputStream(file);
                    ObjectOutputStream o = new ObjectOutputStream(fos);
                    o.writeObject(value);
                    o.flush();
                    o.close();
                } catch (java.io.IOException e) {
                    e.printStackTrace();
                }
                break;
        }

    }

    public static Player load(String file, String action) { // Méthode qui charge la partie à partir des fichiers
        Player player = null; // Initialisation de la variable player
        switch(action){
            case "player": // Si on charge le joueur execute le code suivant
                try {
                    // Récupérations des données à partir des fichiers
                    FileInputStream fis = new FileInputStream(file);
                    FileInputStream fisScore = new FileInputStream("ScorePlayer");
                    FileInputStream fisTime = new FileInputStream("TimePlayer");
                    ObjectInputStream ois = new ObjectInputStream(fis);
                    ObjectInputStream oisScore = new ObjectInputStream(fisScore);
                    ObjectInputStream oisTime = new ObjectInputStream(fisTime);
                    // Création du joueur et affectation de ses valeurs récupérées depuis les fichiers
                    player = new Player(true, (Board) ois.readObject());
                    player.setScore((int) oisScore.readObject());
                    player.setTime((int) oisTime.readObject());
                    ois.close();
                    oisScore.close();
                    oisTime.close();

                } catch (java.io.IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Partie.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case "cpu": // Si on charge l'ordinateur, execute le code suivant
                try {
                    FileInputStream fis = new FileInputStream(file);
                    ObjectInputStream ois = new ObjectInputStream(fis);
                    player = new Computer((Board) ois.readObject());
                    ois.close();
                } catch (java.io.IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Partie.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
        }
        return player;
    }
}
