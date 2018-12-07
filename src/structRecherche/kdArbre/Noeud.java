package structRecherche.kdArbre;

import java.util.ArrayList;
import plateau.Triomino;

public class Noeud {

  private Triomino triomino;
  private Noeud filsGauche, filsDroit;
  private int bascule;

  public Noeud(Triomino triomino, int bascule){
    this.triomino = triomino;
    this.filsGauche = null;
    this.filsDroit = null;
    this.bascule = bascule;
  }

  public Integer getValeur(int d){
    return this.triomino.getCoin(d);
  }

  public Triomino getTriomino(){
    return this.triomino;
  }

  public Noeud getFilsGauche(){
    return this.filsGauche;
  }

  public Noeud getFilsDroit(){
    return this.filsDroit;
  }

  public boolean ajouterTriomino(Triomino t){
    Integer v1 = this.getValeur(this.bascule);
    Integer v2 = t.getCoin(this.bascule);
    if(v1 == null || (v2 != null && v2.compareTo(v1) <= 0)){
      if(this.filsGauche != null){
        return this.filsGauche.ajouterTriomino(t);
      }else{
        this.filsGauche = new Noeud(t, (this.bascule+1)%3);
        return true;
      }
    }else{
      boolean place = true;
      if(v2 == null){
        if(this.filsGauche != null){
          place = this.filsGauche.ajouterTriomino(t);
        }else{
          this.filsGauche = new Noeud(t, (this.bascule+1)%3);
        }
      }
      if(this.filsDroit != null){
        return this.filsDroit.ajouterTriomino(t) && place;
      }else{
        this.filsDroit = new Noeud(t, (this.bascule+1)%3);
        return place;
      }
    }
  }

  public ArrayList<Triomino> rechercher(Triomino t){
    ArrayList<Triomino> liste = new ArrayList<Triomino>();
    Integer v1 = this.getValeur(this.bascule);
    Integer v2 = t.getCoin(this.bascule);
    if(v1 == null || v2.compareTo(v1) <= 0){
      if(
        (v1 == null || v1.equals(v2)) &&
        (this.getValeur((this.bascule+1)%3) == null || this.getValeur((this.bascule+1)%3).equals(t.getCoin((this.bascule+1)%3))) &&
        (this.getValeur((this.bascule+2)%3) == null || this.getValeur((this.bascule+2)%3).equals(t.getCoin((this.bascule+2)%3)))
      ){
        liste.add(this.triomino);
      }
      if(this.filsGauche != null){
        ArrayList<Triomino> retour = this.filsGauche.rechercher(t);
        if(retour != null){
          liste.addAll(retour);
        }
      }
    }else{
      if(this.filsDroit != null){
        ArrayList<Triomino> retour = this.filsDroit.rechercher(t);
        if(retour != null){
          liste.addAll(retour);
        }
      }
    }
    return liste;
  }

  public boolean supprimerTriomino(Triomino t){
    Integer v1 = this.getValeur(this.bascule);
    Integer v2 = t.getCoin(this.bascule);
    if(v1 == null || (v2 != null && v2.compareTo(v1) <= 0)){
      if(this.filsGauche != null){
        Triomino t2 = this.filsGauche.getTriomino();
        if(t2 == t){
          if(this.filsGauche.getFilsGauche() != null || this.filsGauche.getFilsDroit() != null){
            return this.filsGauche.remplacer();
          }else{
            this.filsGauche = null;
            return true;
          }
        }else{
          return this.filsGauche.supprimerTriomino(t);
        }
      }else{
        return false;
      }
    }else{
      boolean supprime = true;
      if(v2 == null){
        if(this.filsGauche != null){
          Triomino t2 = this.filsGauche.getTriomino();
          if(t2 == t){
            if(this.filsGauche.getFilsGauche() != null || this.filsGauche.getFilsDroit() != null){
              supprime = this.filsGauche.remplacer();
            }else{
              this.filsGauche = null;
            }
          }else{
            supprime =  this.filsGauche.supprimerTriomino(t);
          }
        }
      }
      if(this.filsDroit != null){
        Triomino t2 = this.filsDroit.getTriomino();
        if(t2 == t){
          if(this.filsDroit.getFilsGauche() != null || this.filsDroit.getFilsDroit() != null){
            return this.filsDroit.remplacer() && supprime;
          }else{
            this.filsDroit = null;
            return supprime;
          }
        }else{
          return this.filsDroit.supprimerTriomino(t) && supprime;
        }
      }else{
        return false;
      }
    }
  }

  public boolean remplacer(){
    if(this.filsGauche != null){
      Triomino t;
      if(this.filsGauche.getFilsGauche() != null || this.filsGauche.getFilsDroit() != null){
        t = this.filsGauche.max(this.bascule);
        this.triomino = t;
        return this.filsGauche.supprimerTriomino(t) && ((t.getCoin(this.bascule) == null && this.filsDroit != null)? this.filsDroit.supprimerTriomino(t) : true);
      }else{
        t = this.filsGauche.getTriomino();
        this.triomino = t;
        this.filsGauche = null;
        return true;
      }
    }else if(this.filsDroit != null){
      this.filsGauche = this.filsDroit;
      this.filsDroit = null;
      return this.remplacer();
    }else{
      return false;
    }
  }

  public Triomino max(int b){
    Integer max = this.triomino.getCoin(b);
    Triomino t = this.triomino;
    if(max == null){
      return t;
    }else{
      Triomino tG = null;
      Triomino tD = null;
      if(this.filsDroit != null){
        tD = this.filsDroit.max(b);
        if(tD.getCoin(b) == null || tD.getCoin(b).compareTo(max) > 0){
          t = tD;
          max = tD.getCoin(b);
          if(max == null){
            return t;
          }
        }
      }
      if(this.bascule != b && this.filsGauche != null){
        tG = this.filsGauche.max(b);
        if(tG.getCoin(b) == null || tG.getCoin(b).compareTo(max) > 0){
          t = tG;
          max = tG.getCoin(b);
        }
      }
      return t;
    }
  }

  public String toString(){
    return "(" + ((this.filsGauche != null)? this.filsGauche.toString() : "" ) + ") - " + this.triomino.toString() + " - (" + ((this.filsDroit != null)? this.filsDroit.toString() : "" )  + ")";
  }

}
