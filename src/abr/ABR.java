package abr;

/**
 * Classe implémantant l'Arbre Binaire de Recherche
 * @author Victor BOIX
 * @version 1.0
 */

public class ABR {
  private int valeur;
  private ABR filsGauche;
  private ABR filsDroit;

    /**
     * constructeur de la classe ABR
     * @param v la valeur du noeud
     * @param filsG le fils gauche du noeud
     * @param filsD le fils droit du noeud
     */
  public ABR(int v,ABR filsG, ABR filsD) {
    this.valeur = v;
    this.filsGauche = filsG;
    this.filsDroit = filsD;
  }

    /**
     * constructeur de la classe ABR
     * @param v la valeur du noeud
     */
  public ABR(int v) {
    this.valeur = v;
    this.filsGauche = null;
    this.filsDroit = null;
  }

    /**
     * Définie le fils gauche du noeud courant
     * @param filsG l'arbre à inserer en fils gauche
     */
  public void setFilsGauche(ABR filsG) {
    this.filsGauche = filsG;
  }

    /**
     * Définie le fils droit du noeud courant
     * @param filsD l'arbre à inserer en fils droit
     */
  public void setFilsDroit(ABR filsD) {
    this.filsDroit = filsD;
  }

    /**
     * Définie la valeur du noeud courant
     * @param v la valeur a définir
     */
  private void setValeur(int v) {
    this.valeur = v;
  }

    /**
     *
     * @return le fils gauche du noeud courant
     */
  public ABR getFilsGauche() {
    return this.filsGauche;
  }

    /**
     *
     * @return le fils droit du noeud courant
     */
  public ABR getFilsDroit() {
    return this.filsDroit;
  }

    /**
     *
     * @return la valeur du noeud courant
     */
  public int getValeur() {
    return this.valeur;
  }

    /**
     * Recherche une valeur dans l'arbre à partir du noeud courant
     * @param v la valeur à rechercher dans l'arbre
     * @return un booléan indiquant si la valeur a été trouvé ou non
     */
  public boolean rechercher(int v) {
    boolean res = false;

    if(this.valeur == v) {
      res = true;
    } else {
      if(this.valeur > v && this.filsGauche != null) {
        res = this.filsGauche.rechercher(v);
      }

      if(this.valeur < v && this.filsDroit != null) {
        res = this.filsDroit.rechercher(v);
      }
    }

    return res;
  }

    /**
     * Recherche la plus grande valeur stocké dans l'arbre à partir du noeud courant
     * @return le noeud avec la valeur la plus grande de l'arbre
     */
  public ABR rechercherPlusgrand() {
      if(this.filsDroit == null) {
          return this;
      } else {
          this.filsDroit.rechercherPlusgrand();
      }
  }

    /**
     * Insere une valeur dans l'arbre à partir du noeud courant
     * @param v la valeur à inserer dans l'arbre
     */
  public void inserer(int v) {

    if(this.valeur >= v) {
      if(this.filsGauche == null) {
        ABR nouveauNoeud = new ABR(v);
        this.setFilsGauche(nouveauNoeud);
      } else {
        this.filsGauche.inserer(v);
      }
    } else {
      if(this.valeur < v) {
        if(this.filsDroit == null) {
          ABR nouveauNoeud = new ABR(v);
          this.setFilsDroit(nouveauNoeud);
        } else {
          this.filsDroit.inserer(v);
        }
      }
    }
  }

    /**
     * Insere un arbre dans l'arbre à partir du noeud courant
     * @param a l'arbre à inserer
     */
  public void inserer(ABR a) {
      if(this.valeur >= a.getValeur()) {
          if (this.filsGauche() == null) {
              this.setFilsGauche(a);
          } else {
              this.filsGauche().inserer(a);
          }
      } else {
          if(this.valeur < a.getValeur()){
              if(this.filsDroit == null) {
                  this.setFilsDroit(a);
              } else {
                  this.filsDroit.inserer(a);
              }
          }
      }
  }

    /**
     * Suprime un élément de l'arbre à partir du noeud courant
     * @param v l'element à suprimer de l'arbre
     * @return un boolean indiquant si l'élement a été suprimé ou non
     */
  public boolean suprimer(int v) {
      boolean res = false;

      if(this.valeur == v) {
          if(this.filsGauche == null) {
              this = this.filsDroit;
          } else {
              if(this.filsDroit == null) {
                  this = this.filsGauche;
              } else {
                  this.filsDroit.inserer(this.filsGauche.getFilsDroit());
                  this.filsGauche.setFilsDroit(this.filsDroit);
                  this = this.filsGauche;
              }
          }
          res = true;
      }
      if(this.valeur > v && this.filsGauche != null) {
          this.filsGauche.suprimer(v);
      }else if(this.valeur < v && this.filsDroit != null) {
          this.filsDroit.suprimer(v);
      }
    return res;
  }

}
