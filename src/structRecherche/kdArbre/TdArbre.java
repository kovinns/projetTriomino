package structRecherche.kdArbre;

import structRecherche.StructRecherche;
import plateau.Triomino;

import java.util.ArrayList;

public class TdArbre implements StructRecherche {

  private Noeud racine;

  public TdArbre(){
    this.racine = null;
  }

  public boolean ajouterTriomino(Triomino t){
    if(!t.isEmplacement()){
      return false;
    }
    if(this.racine == null){
      this.racine = new Noeud(t, 0);
      return true;
    }else{
      return this.racine.ajouterTriomino(t);
    }
  }

  public ArrayList<Triomino> rechercher(Triomino t){
    if(t.isEmplacement()){
      return new ArrayList<Triomino>();
    }
    if(this.racine == null){
      return new ArrayList<Triomino>();
    }else{
      ArrayList<Triomino> liste = new ArrayList<Triomino>();
      for(int i = 0; i < 3; i++){
        ArrayList<Triomino> retour = this.racine.rechercher(t);
        if(retour != null){
          liste.addAll(retour);
        }
        t.rotation();
      }
      return liste;
    }
  }

  public boolean supprimerTriomino(Triomino t){
    if(!t.isEmplacement()){
      return false;
    }
    if(this.racine == null){
      return false;
    }else{
      Triomino t2 = this.racine.getTriomino();
      if(t == t2){
        if(this.racine.getFilsGauche() != null || this.racine.getFilsDroit() != null){
          return this.racine.remplacer();
        }else{
          this.racine = null;
          return true;
        }
      }else{
        return this.racine.supprimerTriomino(t);
      }
    }
  }

  public String toString(){
    if(this.racine == null){
      return "";
    }else{
      return this.racine.toString();
    }
  }

}
