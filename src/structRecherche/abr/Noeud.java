package structRecherche.abr;

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
    for(int i = 0; i < 3; i++){
      Integer v1 = this.triomino.getCoin(i);
      Integer v2 = t.getCoin(i);
      if(v1 != null && v2 != null && v1.compareTo(v2) < 0){
        if(this.filsDroit == null) {
          Noeud nouveauNoeud = new Noeud(t);
          this.filsDroit = nouveauNoeud;
          return true;
        }else{
          return this.filsDroit.inserer(t);
        }
      }else if(v1 == null || v2 == null || v1.compareTo(v2) > 0){
        boolean place = true;
        if(v2 == null){
          if(this.filsDroit == null) {
            Noeud nouveauNoeud = new Noeud(t);
            this.filsDroit = nouveauNoeud;
          }else{
            place = this.filsDroit.inserer(t);
          }
        }
        if(this.filsGauche == null) {
          Noeud nouveauNoeud = new Noeud(t);
          this.filsGauche = nouveauNoeud;
          return place;
        } else {
          return this.filsGauche.inserer(t) && place;
        }
      }else{
        if(i == 2){
          if(this.filsGauche == null) {
            Noeud nouveauNoeud = new Noeud(t);
            this.filsGauche = nouveauNoeud;
            return true;
          } else {
            return this.filsGauche.inserer(t);
          }
        }
      }
    }
    return false;
  }

  /**
   * Recherche une triomino dans l'arbre à partir du noeud courant
   * @param v la triomino à rechercher dans l'arbre
   * @return un booléan indiquant si la triomino a été trouvé ou non
   */
  public ArrayList<Triomino> rechercher(Triomino t) {
    ArrayList<Triomino> liste = null;
    for(int i = 0; i < 3; i++){
      Integer v1 = this.triomino.getCoin(i);
      Integer v2 = t.getCoin(i);
      if(v1 == null || v1.compareTo(v2) > 0){
        if(this.filsGauche != null){
          liste = this.filsGauche.rechercher(t);
          break;
        }
      }else if(v1 != null && v1.compareTo(v2) < 0){
        if(this.filsDroit != null){
          liste = this.filsDroit.rechercher(t);
          break;
        }else{
          return null;
        }
      }else{
        if(i == 2){
          if(this.filsGauche != null){
            liste = this.filsGauche.rechercher(t);
          }
        }
      }
    }
    if(
      (this.triomino.getCoin(0) == null || this.triomino.getCoin(0).equals(t.getCoin(0))) &&
      (this.triomino.getCoin(1) == null || this.triomino.getCoin(1).equals(t.getCoin(1))) &&
      (this.triomino.getCoin(2) == null || this.triomino.getCoin(2).equals(t.getCoin(2)))
    ){
      if(liste == null){
        liste = new ArrayList<Triomino>();
      }
      liste.add(this.triomino);
    }
    return liste;
  }

  public boolean supprimer(Triomino t){
    for(int i = 0; i < 3; i++){
      Integer v1 = this.triomino.getCoin(i);
      Integer v2 = t.getCoin(i);
      if(v1 != null && v2 != null && v1.compareTo(v2) < 0){
        if(this.filsDroit == null) {
          return false;
        }else{
          Triomino t2 = this.filsDroit.getTriomino();
          if(t == t2){
            if(this.filsDroit.getFilsGauche() != null || this.filsDroit.getFilsDroit() != null){
              if(this.filsDroit.getFilsGauche() != null){
                return this.filsDroit.remplacer();
              }else{
                this.filsDroit = this.filsDroit.getFilsDroit();
              }
            }else{
              this.filsDroit = null;
              return true;
            }
          }else{
            return this.filsDroit.supprimer(t);
          }
        }
      }else if(v1 == null || v2 == null || v1.compareTo(v2) > 0){
        boolean supprime = true;
        if(v2 == null){
          if(this.filsDroit != null) {
            Triomino t2 = this.filsDroit.getTriomino();
            if(t == t2){
              if(this.filsDroit.getFilsGauche() != null || this.filsDroit.getFilsDroit() != null){
                if(this.filsDroit.getFilsGauche() != null){
                  supprime = this.filsDroit.remplacer();
                }else{
                  this.filsDroit = this.filsDroit.getFilsDroit();
                }
              }else{
                this.filsDroit = null;
              }
            }else{
              supprime = this.filsDroit.supprimer(t);
            }
          }
        }
        if(this.filsGauche == null) {
          return false;
        }else{
          Triomino t2 = this.filsGauche.getTriomino();
          if(t == t2){
            if(this.filsGauche.getFilsGauche() != null || this.filsGauche.getFilsDroit() != null){
              if(this.filsGauche.getFilsGauche() != null){
                return this.filsGauche.remplacer();
              }else{
                this.filsGauche = this.filsGauche.getFilsDroit();
              }
            }else{
              this.filsGauche = null;
              return supprime;
            }
          }else{
            return this.filsGauche.supprimer(t) && supprime;
          }
        }
      }else{
        if(i == 2){
          if(this.filsGauche == null) {
            return false;
          }else{
            Triomino t2 = this.filsGauche.getTriomino();
            if(t == t2){
              if(this.filsGauche.getFilsGauche() != null || this.filsGauche.getFilsDroit() != null){
                if(this.filsGauche.getFilsGauche() != null){
                  return this.filsGauche.remplacer();
                }else{
                  this.filsGauche = this.filsGauche.getFilsDroit();
                }
              }else{
                this.filsGauche = null;
                return true;
              }
            }else{
              return this.filsGauche.supprimer(t);
            }
          }
        }
      }
    }
    return false;
  }

  public boolean remplacer(){
    if(this.filsGauche == null){
      return false;
    }else{
      if(this.filsGauche.getFilsDroit() != null){
        Triomino t = this.filsGauche.supprimerPlusGrand();
        for(int i = 0; i < 3; i++){
          Integer v1 = this.triomino.getCoin(i);
          Integer v2 = t.getCoin(i);
          if(v1 != null && v2 != null && v1.compareTo(v2) < 0){
            if(this.filsDroit != null) {
              Triomino t2 = this.filsDroit.getTriomino();
              if(t == t2){
                if(this.filsDroit.getFilsGauche() != null || this.filsDroit.getFilsDroit() != null){
                  if(this.filsDroit.getFilsGauche() != null){
                    this.filsDroit.remplacer();
                    break;
                  }else{
                    this.filsDroit = this.filsDroit.getFilsDroit();
                    break;
                  }
                }else{
                  this.filsDroit = null;
                  break;
                }
              }else{
                this.filsDroit.supprimer(t);
                break;
              }
            }
          }else if(v1 == null || v2 == null || v1.compareTo(v2) > 0){
            if(v2 == null){
              if(this.filsDroit != null) {
                Triomino t2 = this.filsDroit.getTriomino();
                if(t == t2){
                  if(this.filsDroit.getFilsGauche() != null || this.filsDroit.getFilsDroit() != null){
                    if(this.filsDroit.getFilsGauche() != null){
                      this.filsDroit.remplacer();
                      break;
                    }else{
                      this.filsDroit = this.filsDroit.getFilsDroit();
                      break;
                    }
                  }else{
                    this.filsDroit = null;
                    break;
                  }
                }else{
                  this.filsDroit.supprimer(t);
                  break;
                }
              }
            }
          }
        }
        this.triomino = t;
        return true;
      }else{
        this.triomino = this.filsGauche.getTriomino();
        this.filsGauche = this.filsGauche.getFilsGauche();
        return true;
      }
    }
  }

  /**
   * Recherche la plus grande triomino stocké dans l'arbre à partir du noeud courant
   * @return le noeud avec la triomino la plus grande de l'arbre
   */
  public Triomino supprimerPlusGrand() {
    if(this.filsDroit != null) {
      if(this.filsDroit.getFilsDroit() != null){
        Triomino t = this.filsDroit.supprimerPlusGrand();
        for(int i = 0; i < 3; i++){
          Integer v1 = this.triomino.getCoin(i);
          Integer v2 = t.getCoin(i);
          if(v1 == null || v2 == null || v1.compareTo(v2) > 0){
            if(this.filsGauche != null) {
              Triomino t2 = this.filsGauche.getTriomino();
              if(t == t2){
                if(this.filsGauche.getFilsGauche() != null || this.filsGauche.getFilsDroit() != null){
                  this.filsGauche.remplacer();
                  break;
                }else{
                  this.filsGauche = null;
                  break;
                }
              }else{
                this.filsGauche.supprimer(t);
                break;
              }
            }
          }else if(v1.equals(v2)){
            if(i == 2){
              if(this.filsGauche != null) {
                Triomino t2 = this.filsGauche.getTriomino();
                if(t == t2){
                  if(this.filsGauche.getFilsGauche() != null || this.filsGauche.getFilsDroit() != null){
                    this.filsGauche.remplacer();
                    break;
                  }else{
                    this.filsGauche = null;
                    break;
                  }
                }else{
                  this.filsGauche.supprimer(t);
                  break;
                }
              }
            }
          }else{
            break;
          }
        }
        return t;
      }else{
        Triomino t = this.filsDroit.getTriomino();
        this.filsDroit = this.filsDroit.getFilsGauche();
        for(int i = 0; i < 3; i++){
          Integer v1 = this.triomino.getCoin(i);
          Integer v2 = t.getCoin(i);
          if(v1 == null || v2 == null || v1.compareTo(v2) > 0){
            if(this.filsGauche != null) {
              Triomino t2 = this.filsGauche.getTriomino();
              if(t == t2){
                if(this.filsGauche.getFilsGauche() != null || this.filsGauche.getFilsDroit() != null){
                  this.filsGauche.remplacer();
                }else{
                  this.filsGauche = null;
                }
              }else{
                this.filsGauche.supprimer(t);
              }
            }
          }else if(v1.equals(v2)){
            if(i == 2){
              if(this.filsGauche != null) {
                Triomino t2 = this.filsGauche.getTriomino();
                if(t == t2){
                  if(this.filsGauche.getFilsGauche() != null || this.filsGauche.getFilsDroit() != null){
                    this.filsGauche.remplacer();
                  }else{
                    this.filsGauche = null;
                  }
                }else{
                  this.filsGauche.supprimer(t);
                }
              }
            }
          }else{
            break;
          }
        }
        return t;
      }
    } else {
      return this.triomino;
    }
  }

  public String toString(){
    return "(" + ((this.filsGauche != null)? this.filsGauche.toString() : "" ) + ") - " + this.triomino.toString() + " - (" + ((this.filsGauche != null)? this.filsGauche.toString() : "" ) + ")";
  }

}
