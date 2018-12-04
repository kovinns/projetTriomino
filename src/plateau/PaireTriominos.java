package plateau;

public class PaireTriominos{

  private Triomino[] paire;
  private int x, y;
  private Plateau plateau;

  public PaireTriominos(int x, int y, Plateau plateau){
    this.x = x;
    this.y = y;
    this.paire = new Triomino[2];
    this.paire[0] = null;
    this.paire[1] = null;
    this.plateau = plateau;
  }

  public boolean addTriomino(Triomino t, int orientation, boolean forcer){
    Triomino tri;
    if(!forcer && this.paire[orientation/3] == null){
      return false;
    }
    tri = paire[orientation/3];
    if(!forcer && !tri.remplacable(t, orientation)){
      return false;
    }
    if(tri != null){
      this.plateau.supprInStruct(tri);
    }
    this.paire[orientation/3] = t;
    t.positionner(this.x, this.y, orientation);
    return true;
  }

  public void setPosition(int x, int y){
    this.x = x;
    this.y = y;
  }

  public void maj(int position, int a1, int a2, Triomino t){
    Triomino tri = this.paire[position];
    if(tri != null && tri.isEmplacement()){
      if(tri.getCoin(a1) == null){
        this.plateau.supprInStruct(tri);
        tri.setCoin(a1, t.getCoin(a2));
        this.plateau.addInStruct(tri);
      }
      if(tri.getScore() < 60 && tri.getCoin(0) != null && tri.getCoin(1) != null && tri.getCoin(2) != null){
        int score = 40;
        int ajout = 0;
        if(position == 0){
          if(this.plateau.nombrePlaceParCoin(this.x, this.y, 0) == 5){
            ajout += 10;
          }
          if(this.plateau.nombrePlaceParCoin(this.x, this.y, 2) == 5){
            ajout += 10;
          }
          if(ajout < 20 && this.plateau.nombrePlaceParCoin(this.x, this.y, 3) == 5){
            ajout += 10;
          }
        }else{
          if(this.plateau.nombrePlaceParCoin(this.x, this.y, 0) == 5){
            ajout += 10;
          }
          if(this.plateau.nombrePlaceParCoin(this.x, this.y, 1) == 5){
            ajout += 10;
          }
          if(ajout < 20 && this.plateau.nombrePlaceParCoin(this.x, this.y, 2) == 5){
            ajout += 10;
          }
        }
        score += ajout;
        tri.setScore(score);
      }
    }

  }

  public void maj(int position, int a1, int a2, int b1, int b2, Triomino t){
    Triomino tri = this.paire[position];
    if(tri != null){
      if(tri.isEmplacement()){
        boolean modifie = false;
        if(tri.getCoin(a1) == null){
          this.plateau.supprInStruct(tri);
          modifie = true;
          tri.setCoin(a1, t.getCoin(a2));
        }
        if(tri.getCoin(b1) == null){
          if(!modifie){
            this.plateau.supprInStruct(tri);
            modifie = true;
          }
          tri.setCoin(b1, t.getCoin(b2));
        }
        if(modifie){
          this.plateau.addInStruct(tri);
        }
      }
    }else{
      tri = new Triomino(null, null, null, true);
      tri.positionner(this.x, this.y, position*3);
      this.paire[position] = tri;
      tri.setCoin(a1, t.getCoin(a2));
      tri.setCoin(b1, t.getCoin(b2));
      int c = 3 - a1 - b1;
      Integer valeur = this.plateau.getCoin(this.x, this.y, ((c == 0)? 0 : ((c == 1+position)? 2 : ((position == 0)? 3 : 1 ) ) ));
      tri.setCoin(c, valeur);
      this.plateau.addInStruct(tri);
    }
    if(tri.getScore() < 60 && tri.getCoin(0) != null && tri.getCoin(1) != null && tri.getCoin(2) != null){
      int score = 40;
      int ajout = 0;
      if(position == 0){
        if(this.plateau.nombrePlaceParCoin(this.x, this.y, 0) == 5){
          ajout += 10;
        }
        if(this.plateau.nombrePlaceParCoin(this.x, this.y, 2) == 5){
          ajout += 10;
        }
        if(ajout < 20 && this.plateau.nombrePlaceParCoin(this.x, this.y, 3) == 5){
          ajout += 10;
        }
      }else{
        if(this.plateau.nombrePlaceParCoin(this.x, this.y, 0) == 5){
          ajout += 10;
        }
        if(this.plateau.nombrePlaceParCoin(this.x, this.y, 1) == 5){
          ajout += 10;
        }
        if(ajout < 20 && this.plateau.nombrePlaceParCoin(this.x, this.y, 2) == 5){
          ajout += 10;
        }
      }
      score += ajout;
      tri.setScore(score);
    }
  }

  public Integer getCoin(int c){
    if(c == 0){
      if(this.paire[0] != null){
        return this.paire[0].getCoin(0);
      }
      if(this.paire[1] != null){
        return this.paire[1].getCoin(0);
      }
    }else if(c == 1){
      if(this.paire[1] != null){
        return this.paire[1].getCoin(1);
      }
    }else if(c == 2){
      if(this.paire[0] != null){
        return this.paire[0].getCoin(1);
      }
      if(this.paire[1] != null){
        return this.paire[1].getCoin(2);
      }
    }else{
      if(this.paire[0] != null){
        return this.paire[0].getCoin(2);
      }
    }
    return null;
  }

  public int nombrePlaceParCoin(int c){
    int retour = 0;
    if(c == 0){
      if(this.paire[0] != null && !this.paire[0].isEmplacement()){
        retour++;
      }
      if(this.paire[1] != null && !this.paire[1].isEmplacement()){
        retour++;
      }
    }else if(c == 1){
      if(this.paire[1] != null && !this.paire[1].isEmplacement()){
        retour++;
      }
    }else if(c == 2){
      if(this.paire[0] != null && !this.paire[0].isEmplacement()){
        retour++;
      }
      if(this.paire[1] != null && !this.paire[1].isEmplacement()){
        retour++;
      }
    }else{
      if(this.paire[0] != null && !this.paire[0].isEmplacement()){
        retour++;
      }
    }
    return retour;
  }

  public Triomino getTriomino(int p){
    return this.paire[p%2];
  }

  public void setTriomino(Triomino t, int p){
    t.positionner(this.x, this.y, p*3);
    this.paire[p] = t;
  }

  public String toString(){
    return ((this.paire[0] != null)? "0=(" + this.paire[0].toString() + ") ": "" ) + ((this.paire[1] != null)? " 1=(" + this.paire[1].toString() + ")" : "" );
  }

}
