package Main;

import batailleNavale.controller.Backup;
import batailleNavale.controller.Menu;
import batailleNavale.controller.Player;
import batailleNavale.controller.Computer;
import batailleNavale.view.TerminalBoardView;
import batailleNavale.model.Board;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import static java.lang.Thread.sleep;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Partie implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    public static void main(String[] args) {
System.out.println("                       $o");
System.out.println("                       $                     Bataille Navale");
System.out.println("                      $$$      .oo..     'oooo'oooo'ooooooooo....");
System.out.println("                       $       $$$$$$$");
System.out.println("                   .ooooooo.   $$!!!!!");
System.out.println("                 .'.........'. $$!!!!!      o$$oo.   Khalladi  Salmi  Aarab Boudaa");
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
        try {
            Menu.menu(); // Affichage du menu principale du jeu
        } catch (InterruptedException ex) { // Si une erreur, gestion de l'exception
            Logger.getLogger(Partie.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
