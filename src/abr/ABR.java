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

  public void setFilsGauche(ABR filsG) {
    this.filsGauche = filsG;
  }

  public void setFilsDroit(ABR filsD) {
    this.filsDroit = filsD;
  }

  private void setValeur(int v) {
    this.valeur = v;
  }

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

  public ABR rechercherPlusgrand() {
      if(this.filsDroit == null) {
          return this;
      } else {
          this.filsDroit.rechercherPlusgrand();
      }
  }

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

  public boolean suprimer(int v) {
      boolean res = false;

      if(this.valeur == v) {
          if(this.filsGauche == null) {
              this = this.filsDroit;
          } else {
              if(this.filsDroit == null) {
                  this = this.filsGauche();
              } else {
                  this.filsDroit().inserer(this.filsGauche.getFilsDroit());
                  this.filsGauche.setFilsDroit(this.filsDroit);
                  this = this.filsGauche;
              }
          }
          res = true;
      }
      if(this.valeur > v && this.filsGauche != null) {
          this.filsGauche.suprimer(v);
      }else {
          if(this.valeur < v && this.filsDroit != null) {
              this.filsDroit.suprimer(v);
          }
      }
    return res;
  }

}
