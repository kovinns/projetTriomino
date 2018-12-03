import java.util.ArrayList;
import plateau.*;

public class Application{

  public static void main(String[] args){
    Plateau plateau = new Plateau();
    Triomino t1 = new Triomino(0,1,2,false);
    plateau.afficher();
    clearScreen();
    plateau.addTriomino(t1, 15, 29, 0, false);
    plateau.afficher();
    clearScreen();
    Triomino t2 = new Triomino(0,4,5,false);
    plateau.addTriomino(t2, 14, 28, 5, true);
    plateau.afficher();
  }

  public static void clearScreen(){
    System.out.println();
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

}
