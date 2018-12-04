import java.util.ArrayList;
import java.util.Scanner;
import plateau.Plateau;
import plateau.Triomino;

public class Application{

  public static void main(String[] args){
    Plateau plateau = new Plateau();
    int compteur = 0;
    compteur = RemplirPlateau(plateau);
    ArrayList<Triomino> liste = new ArrayList<Triomino>();
    compteur = TriominosAPlacer(liste, compteur);
    ArrayList<Triomino> pioche = new ArrayList<Triomino>();
    compteur = OuPiocher(pioche, compteur);
    Jouer(plateau, liste, pioche);
  }

  private static int RemplirPlateau(Plateau plateau){
    clearScreen();
    System.out.println("***********************************************************************");
    System.out.println("                      REMPLISSAGE DU TABLEAU                           ");
    System.out.println("***********************************************************************");
    int compteur = 0;
    Scanner sc = new Scanner(System.in);
    principale:
    while(compteur < 30){
      boolean valide = false;
      Integer x = null;
      Integer y = null;
      Integer p = null;
      while(!valide){
        valide = true;
        System.out.println("***********************************************************************");
        System.out.println("Entrez 'stop' pour ne pas entrer de nouveau triomino.");
        System.out.println("Entrez les coordonnées du triomino (jusqu'à 29 en x et 59 en y) et son orientation ( 0 pointe en haut, 1 pointe en bas): ");
        System.out.println("Il est conseillé de commencer à la case [15,29] position 0, se trouvant à l'exacte centre du plateau");
        System.out.println("Exemple : '15 29 0'");
        String coord = sc.nextLine();
        if(coord.contains("stop")){
          break principale;
        }
        String[] table = coord.split(" ");
        try{
          x = new Integer(table[0]);
          y = new Integer(table[1]);
          p = new Integer(table[2]);
          if(x < 0 || x > 29 || y < 0 || y > 59){
            throw new Exception("Veillez rester sur le plateau s'il vous plait");
          }
          if(p < 0 || p > 1){
            throw new Exception("Cette orientation est indisponnible");
          }else{
            p *= 3;
          }
        }catch(Exception e){
          valide = false;
          System.out.println("***********************************************************************");
          System.out.println(e);
          System.out.println("L'une des données demandées avait un format incorrecte ou était absente");
          System.out.println("Veuillez recommencer");
        }
      }
      Integer a = null;
      Integer b = null;
      Integer c = null;
      valide = false;
      while(!valide){
        valide = true;
        System.out.println("***********************************************************************");
        System.out.println("Entrez les trois valeurs du triomino dans le sens horraire ('a b c'): ");
        System.out.println("Toute erreur de connectivité entre deux triomino sera de votre responsabilité, non de la notre.");
        System.out.println("                a                     a-b");
        System.out.println("orientation 0 : |\\    orientation 1 : |/");
        System.out.println("                c-b                   c");
        String coord = sc.nextLine();
        String[] table = coord.split(" ");
        try{
          a = new Integer(table[0]);
          b = new Integer(table[1]);
          c = new Integer(table[2]);
          if(a < 0 || a > 5 || b < 0 || b > 5 || c < 0 || c > 5){
            throw new Exception("Vous ne connaissez pas les règles du triomino de toute évidence");
          }
        }catch(Exception e){
          valide = false;
          System.out.println("***********************************************************************");
          System.out.println(e);
          System.out.println("L'une des données demandées avait un format incorrecte ou était absente");
          System.out.println("Veuillez recommencer");
        }
      }
      plateau.addTriomino(new Triomino(a, b, c, false), x, y, p, true);
      compteur++;
      clearScreen();
      plateau.afficher();
    }
    if(compteur == 0){
      plateau.addTriomino(new Triomino(null, null, null, true),15, 29, 0, true);
    }
    return compteur;
  }

  private static int TriominosAPlacer(ArrayList<Triomino> liste, int dejaPlaces){
    System.out.println("***********************************************************************");
    System.out.println("                      REMPLISSAGE DE LA MAIN                           ");
    System.out.println("***********************************************************************");
    Scanner sc = new Scanner(System.in);
    int compteur = dejaPlaces;
    principale:
    while(compteur < 30){
      boolean valide = false;
      Integer a = null;
      Integer b = null;
      Integer c = null;
      while(!valide){
        valide = true;
        System.out.println("***********************************************************************");
        System.out.println("Entrez 'stop' pour ne pas entrer de nouveau triomino.");
        System.out.println("Entrez les valeur 'a b c' dans le sens horraire du triomino à ajouter");
        System.out.println("Exemple : '5 0 2'");
        String ligne = sc.nextLine();
        if(ligne.contains("stop")){
          break principale;
        }
        String[] table = ligne.split(" ");
        try{
          a = new Integer(table[0]);
          b = new Integer(table[1]);
          c = new Integer(table[2]);
          if(a < 0 || a > 5 || b < 0 || b > 5 || c < 0 || c > 5){
            throw new Exception("Vous ne connaissez pas les règles du triomino de toute évidence");
          }
        }catch(Exception e){
          valide = false;
          System.out.println("***********************************************************************");
          System.out.println(e);
          System.out.println("L'une des données demandées avait un format incorrecte ou était absente");
          System.out.println("Veuillez recommencer");
        }
        if(valide){
          Triomino t = new Triomino(a, b, c, false);
          liste.add(t);
          System.out.println("***********************************************************************");
          System.out.println("Triomino (" + t + ") ajouté");
          compteur ++;
        }
      }
    }
    return compteur;
  }

  private static int OuPiocher(ArrayList<Triomino> liste, int dejaPlaces){
    System.out.println("***********************************************************************");
    System.out.println("                     REMPLISSAGE DE LA PIOCHE                          ");
    System.out.println("***********************************************************************");
    Scanner sc = new Scanner(System.in);
    int compteur = dejaPlaces;
    principale:
    while(compteur < 30){
      boolean valide = false;
      Integer a = null;
      Integer b = null;
      Integer c = null;
      while(!valide){
        valide = true;
        System.out.println("***********************************************************************");
        System.out.println("Entrez 'stop' pour ne pas entrer de nouveau triomino.");
        System.out.println("Entrez les valeur 'a b c' dans le sens horraire du triomino à ajouter");
        System.out.println("Exemple : '5 0 2'");
        String ligne = sc.nextLine();
        if(ligne.contains("stop")){
          break principale;
        }
        String[] table = ligne.split(" ");
        try{
          a = new Integer(table[0]);
          b = new Integer(table[1]);
          c = new Integer(table[2]);
          if(a < 0 || a > 5 || b < 0 || b > 5 || c < 0 || c > 5){
            throw new Exception("Vous ne connaissez pas les règles du triomino de toute évidence");
          }
        }catch(Exception e){
          valide = false;
          System.out.println("***********************************************************************");
          System.out.println(e);
          System.out.println("L'une des données demandées avait un format incorrecte ou était absente");
          System.out.println("Veuillez recommencer");
        }
        if(valide){
          Triomino t = new Triomino(a, b, c, false);
          liste.add(t);
          System.out.println("***********************************************************************");
          System.out.println("Triomino (" + t + ") ajouté");
          compteur ++;
        }
      }
    }
    return compteur;
  }

  private static ArrayList<Triomino> OuPlacerTriomino(Triomino t, Plateau p){
    return p.rechercheInStrut(t);
  }

  private static Integer QuelTriominoPlacer(ArrayList<Triomino> liste, Plateau plateau){
    int score = -1;
    int longueur = -1;
    Triomino t = null;
    Triomino emplacement = null;
    for(int i = 0; i < liste.size(); i++){
      Triomino courant = liste.get(i);
      int scoreCour = courant.getCoin(0) + courant.getCoin(1) + courant.getCoin(2);
      ArrayList<Triomino> listeRetour = OuPlacerTriomino(courant, plateau);
      for(int j = 0; j < listeRetour.size(); j++){
        Triomino t2 = listeRetour.get(j);
        if(t2.getScore()+scoreCour > score || listeRetour.size() < longueur){
          score = t2.getScore()+scoreCour;
          longueur = listeRetour.size();
          t = courant;
          emplacement = t2;
        }
      }
    }
    if(t == null){
      return null;
    }else{
      boolean place = plateau.addTriomino(t, emplacement.getX(), emplacement.getY(), emplacement.getOrientation(t), false);
      if(place){
        System.out.println("Le triomino (" + t + ") a été placé en " + emplacement.getX() + " " + emplacement.getY());
        liste.remove(t);
        return score;
      }else{
        return null;
      }
    }
  }

  private static void Jouer(Plateau plateau, ArrayList<Triomino> liste, ArrayList<Triomino> pioche){
    boolean fini = false;
    boolean perdu = false;
    int score = 0;
    while(!fini){
      Integer marque = QuelTriominoPlacer(liste, plateau);
      if(marque == null){
        if(pioche.size() == 0){
          perdu = true;
          fini = true;
        }else{
          int rang = (int)(Math.random() * pioche.size());
          Triomino t = pioche.remove(rang);
          liste.add(t);
          System.out.println("Aucun triomino ne peut être placé, le triomino (" + t + ") a été pioché");
        }
      }else{
        System.out.println("Vous avez marqué " + marque + " points");
        score += marque;
        plateau.afficher();
        System.out.println("Le score actuel est de : " + score);
        if(liste.size() == 0){
          fini = true;
        }
      }
    }
    System.out.println("Vous avez " + ((perdu)? "perdu": "gagné") + ".");
    System.out.println("Vous avez eu le temps de marquer " + score + " points.");
  }

  public static void clearScreen(){
    System.out.println();
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

}
