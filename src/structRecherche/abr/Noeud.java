package abr;

import java.util.ArrayList;
import plateau.Triomino;

public class Noeud {
  private Triomino triomino;
  private Noeud filsGauche;
  private Noeud filsDroit;

  /**
   * constructeur de la classe Noeud
   * @param triomino la triomino du noeud
   */
  public Noeud(Triomino triomino) {
    this.triomino = triomino;
    this.filsGauche = null;
    this.filsDroit = null;
  }

  /**
   *
   * @return le fils gauche du noeud courant
   */
  public Noeud getFilsGauche() {
    return this.filsGauche;
  }

  /**
   *
   * @return le fils droit du noeud courant
   */
  public Noeud getFilsDroit() {
    return this.filsDroit;
  }

  /**
   *
   * @return la triomino du noeud courant
   */
  public Triomino getTriomino() {
    return this.triomino;
  }

  /**
   * Insere une triomino dans l'arbre à partir du noeud courant
   * @param v la triomino à inserer dans l'arbre
   */
  public boolean inserer(Triomino t) {
    if(this.triomino.getCoin(0) != null && (t.getCoin(0) == null || this.triomino.getCoin(0).compareTo(t.getCoin(0)) < 0)){
      if(this.triomino.getCoin(1) != null && (t.getCoin(1) == null || this.triomino.getCoin(1).compareTo(t.getCoin(1)) < 0)){
        if(this.triomino.getCoin(2) != null && (t.getCoin(2) == null || this.triomino.getCoin(2).compareTo(t.getCoin(2)) < 0)){
          if(this.filsDroit == null) {
            Noeud nouveauNoeud = new Noeud(t);
            this.filsDroit = nouveauNoeud;
            return true;
          }else{
            return this.filsDroit.inserer(t);
          }
        }
      }
    }
    if(this.filsGauche == null) {
      Noeud nouveauNoeud = new Noeud(t);
      this.filsGauche = nouveauNoeud;
      return true;
    } else {
      return this.filsGauche.inserer(t);
    }
  }

  /**
   * Recherche une triomino dans l'arbre à partir du noeud courant
   * @param v la triomino à rechercher dans l'arbre
   * @return un booléan indiquant si la triomino a été trouvé ou non
   */
  public ArrayList<Triomino> rechercher(Triomino t) {
    if(this.triomino.getCoin(0) != null && (t.getCoin(0) == null || this.triomino.getCoin(0).compareTo(t.getCoin(0)) < 0)){
      if(this.triomino.getCoin(1) != null && (t.getCoin(1) == null || this.triomino.getCoin(1).compareTo(t.getCoin(1)) < 0)){
        if(this.triomino.getCoin(2) != null && (t.getCoin(2) == null || this.triomino.getCoin(2).compareTo(t.getCoin(2)) < 0)){
          if(this.filsDroit == null) {
            return null;
          }else{
            return this.filsDroit.rechercher(t);
          }
        }
      }
    }
    boolean egal = (this.triomino.getCoin(0) == null || this.triomino.getCoin(0).equals(t.getCoin(0))) &&
      (this.triomino.getCoin(1) == null || this.triomino.getCoin(1).equals(t.getCoin(1))) &&
      (this.triomino.getCoin(2) == null || this.triomino.getCoin(2).equals(t.getCoin(2)));
    if(this.filsGauche == null) {
      if(egal){
        ArrayList<Triomino> liste = new ArrayList<Triomino>();
        liste.add(this.triomino);
        return liste;
      }else{
        return null;
      }
    } else {
      ArrayList<Triomino> liste = this.filsGauche.rechercher(t);
      if(egal){
        liste.add(this.triomino);
      }
      return liste;
    }
  }

  public boolean supprimer(Triomino t){
    if(this.triomino.getCoin(0) != null && (t.getCoin(0) == null || this.triomino.getCoin(0).compareTo(t.getCoin(0)) < 0)){
      if(this.triomino.getCoin(1) != null && (t.getCoin(1) == null || this.triomino.getCoin(1).compareTo(t.getCoin(1)) < 0)){
        if(this.triomino.getCoin(2) != null && (t.getCoin(2) == null || this.triomino.getCoin(2).compareTo(t.getCoin(2)) < 0)){
          if(this.filsDroit == null) {
            return false;
          }else{
            Triomino t2 = this.filsDroit.getTriomino();
            if(t2 == t){
              if(this.filsDroit.getFilsDroit() != null || this.filsDroit.getFilsGauche() != null){
                return this.filsDroit.remplacer();
              }else{
                this.filsDroit = null;
                return true;
              }
            }else{
              return this.filsDroit.supprimer(t);
            }
          }
        }
      }
    }
    if(this.filsGauche == null) {
      return false;
    }else{
      Triomino t2 = this.filsGauche.getTriomino();
      if(t2 == t){
        if(this.filsGauche.getFilsDroit() != null || this.filsGauche.getFilsGauche() != null){
          return this.filsGauche.remplacer();
        }else{
          this.filsGauche = null;
          return true;
        }
      }else{
        return this.filsGauche.supprimer(t);
      }
    }
  }

  public boolean remplacer(){
    if(this.filsGauche != null){
      Triomino t = this.rechercherPlusGrand();
      this.triomino = t;
      if(this.filsGauche.getTriomino() == t){
        if(this.filsGauche.getFilsDroit() != null || this.filsGauche.getFilsGauche() != null){
          return this.filsGauche.remplacer();
        }else{
          this.filsGauche = null;
          return true;
        }
      }else{
        return this.filsGauche.supprimer(t);
      }
    }else if(this.filsDroit != null){
      Triomino t = this.rechercherPlusPetit();
      this.triomino = t;
      if(this.filsDroit.getTriomino() == t){
        if(this.filsDroit.getFilsDroit() != null || this.filsDroit.getFilsGauche() != null){
          return this.filsDroit.remplacer();
        }else{
          this.filsDroit = null;
          return true;
        }
      }else{
        return this.filsDroit.supprimer(t);
      }
    }else{
      return false;
    }
  }

  /**
   * Recherche la plus grande triomino stocké dans l'arbre à partir du noeud courant
   * @return le noeud avec la triomino la plus grande de l'arbre
   */
  public Triomino rechercherPlusGrand() {
    if(this.filsDroit == null) {
      return this.triomino;
    } else {
      return this.filsDroit.rechercherPlusGrand();
    }
  }

  /**
   * Recherche la plus petite triomino stocké dans l'arbre à partir du noeud courant
   * @return le noeud avec la triomino la plus grande de l'arbre
   */
  public Triomino rechercherPlusPetit() {
    if(this.filsGauche == null) {
      return this.triomino;
    } else {
      return this.filsGauche.rechercherPlusPetit();
    }
  }

  public String toString(){
    return ((this.filsGauche != null)? "(" + this.filsGauche.toString() + ") - " : "" ) + "(" + this.triomino.toString() + ")" + ((this.filsGauche != null)? " - (" + this.filsGauche.toString() + ")" : "" );
  }

}
