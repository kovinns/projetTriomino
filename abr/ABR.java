package abr;

public class ABR {
  private int valeur;
  private ABR filsGauche;
  private ABR filsDroit;

  public ABR(int v,ABR filsG, ABR filsD) {
    this.valeur = v;
    this.filsGauche = filsG;
    this.filsDroit = filsD;
  }

  public ABR(int v) {
    this.valeur = v;
    this.filsGauche = null;
    this.filsDroit = null;
  }

  /*les set ne sont pas corectes car il faut checker la valeur du fils que l'ont fait inserer

  public void setFilsGauche(ABR filsG) {
    this.filsGauche = filsG;
  }

  public void setFilsDroit(ABR filsD) {
    this.filsDroit = filsD;
  }

  public void setValeur(int v) {
    this.valeur = v;
  }

  */

  public ABR getFilsGauche() {
    return this.filsGauche;
  }

  public ABR getFilsDroit() {
    return this.filsDroit;
  }

  public int getValeur() {
    return this.valeur;
  }

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

  public void inserer(int v) {

  }


}
