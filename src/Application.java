import java.util.ArrayList;

public class Application{

  public static void main(String[] args) {

    ArrayList<Triomino> liste = new ArrayList<Triomino>();
    for(int i = 0; i < 6; i++){
      for(int j = i; j < 6; j++){
        for(int k = j; k < 6; k++){
          liste.add(new Triomino(i, j, k));
        }
      }
    }
    System.out.println(liste.size());
    for(int i = 0; i < liste.size(); i++){
      System.out.println(liste.get(i));
    }

  }

}
