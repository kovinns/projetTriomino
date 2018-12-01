package plateau;

public class Plateau{

  private PaireTriominos[][] plateau;

  public Plateau(){
    this.plateau = new PaireTriominos[30][59];
    for(int i = 0; i < 30; i++){
      for(int j = 0; j < 59; j++){
        this.plateau[i][j] = null;
      }
    }
    this.plateau[15][29] = new PaireTriominos(15, 29, this);
    this.plateau[15][29].addEmplacement(new Triomino(null, null, null, true));
  }

  public boolean addTriomino(Triomino t, int x, int y, int orientation, boolean forcer){
    if(x < 0 || x > 29 || y < 0 || y > 58){
      return false;
    }
    if(this.plateau[x][y] == null){
      if(!forcer){
        return false;
      }else{
        this.plateau[x][y] = new PaireTriominos(x, y, this);
      }
    }
    boolean place = this.plateau[x][y].addTriomino(t, orientation, forcer);
    if(place){
      if(orientation < 3){
        this.plateau[x][y].maj(1, 0, 0, 2, 1, t);
        if(x > 0){
          if(this.plateau[x-1][y] != null){
            this.plateau[x-1][y].maj(0, 2, 0, t);
          }
          if(y > 0){
            if(this.plateau[x-1][y-1] != null){
              this.plateau[x-1][y-1].maj(0, 1, 0, t);
              this.plateau[x-1][y-1].maj(1, 2, 0, t);
            }
          }
        }
        if(x < 14){
          if(this.plateau[x+1][y] == null){
            this.plateau[x+1][y] = new PaireTriominos(x+1, y, this);
          }
          this.plateau[x+1][y].maj(0, 0, 2, t);
          this.plateau[x+1][y].maj(1, 0, 2, 1, 1, t);
          if(y > 0){
            if(this.plateau[x+1][y-1] != null){
              this.plateau[x+1][y-1].maj(1, 1, 2, t);
            }
          }
          if(y < 58){
            if(this.plateau[x+1][y+1] != null){
              this.plateau[x+1][y+1].maj(0, 0, 1, t);
              this.plateau[x+1][y+1].maj(1, 0, 1, t);
            }
          }
        }
        if(y > 0){
          if(this.plateau[x][y-1] == null){
            this.plateau[x][y-1] = new PaireTriominos(x, y-1, this);
          }
          this.plateau[x][y-1].maj(0, 1, 2, t);
          this.plateau[x][y-1].maj(1, 1, 0, 2, 2, t);
        }
        if(y < 58){
          if(this.plateau[x][y+1] != null){
            this.plateau[x][y+1].maj(0, 2, 1, t);
          }
        }
      }else{
        this.plateau[x][y].maj(0, 0, 0, 1, 2, t);
        if(y > 0){
          if(this.plateau[x][y-1] != null){
            this.plateau[x][y-1].maj(1, 1, 0, t);
          }
          if(x > 0){
            if(this.plateau[x-1][y-1] != null){
              this.plateau[x-1][y-1].maj(0, 1, 0, t);
              this.plateau[x-1][y-1].maj(1, 2, 0, t);
            }
          }
        }
        if(y < 58){
          if(this.plateau[x][y+1] == null){
            this.plateau[x][y+1] = new PaireTriominos(x, y+1, this);
          }
          this.plateau[x][y+1].maj(0, 0, 1, 2, 2, t);
          this.plateau[x][y+1].maj(1, 0, 1, t);
          if(x > 0){
            if(this.plateau[x-1][y+1] != null){
              this.plateau[x-1][y+1].maj(0, 2, 1, t);
            }
          }
          if(y < 14){
            if(this.plateau[x+1][y+1] != null){
              this.plateau[x+1][y+1].maj(0, 0, 2, t);
              this.plateau[x+1][y+1].maj(1, 0, 2, t);
            }
          }
        }
        if(x > 0){
          if(this.plateau[x-1][y] == null){
            this.plateau[x-1][y] = new PaireTriominos(x-1, y, this);
          }
          this.plateau[x-1][y].maj(0, 1, 1, 2, 0, t);
          this.plateau[x-1][y].maj(1, 2, 1, t);
        }
        if(x < 14){
          if(this.plateau[x+1][y] != null){
            this.plateau[x+1][y].maj(1, 1, 2, t);
          }
        }
      }
    }
    return place;
  }

  public Integer getCoin(int x, int y, int c){
    Integer valeur = this.plateau[x][y].getCoin(c);
    if(valeur == null){
      if((c == 0 || c == 1) && x > 0){
        valeur = this.plateau[x-1][y].getCoin(3-c);
      }
      if(valeur == null){
        if(c == 0 && x > 0 && y > 0){
          valeur = this.plateau[x-1][y-1].getCoin(2);
        }
        if(valeur == null){
          if((c == 0 || c == 3) && y > 0){
            valeur = this.plateau[x][y-1].getCoin((c+3)/3);
          }
          if(valeur == null){
            if(c == 3 && x < 14 && y > 0){
              valeur = this.plateau[x+1][y-1].getCoin(1);
            }
            if(valeur == null){
              if((c == 2 || c == 3) && y < 58){
                valeur = this.plateau[x+1][y].getCoin(3-c);
              }
              if(valeur == null){
                if(c == 2 && x < 14 && y < 58){
                  valeur = this.plateau[x+1][y+1].getCoin(0);
                }
                if(valeur == null){
                  if((c == 1 || c == 2) && x < 14){
                    valeur = this.plateau[x][y+1].getCoin((c-1)*3);
                  }
                  if(valeur == null){
                    if(c == 1 && x > 0 && y < 58){
                      valeur = this.plateau[x-1][y+1].getCoin(3);
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
    return valeur;
  }

  public void affichage(){
    String ligne = "     ";
    for(int i = 0; i < 59; i++){
      ligne += " " + i + " ";
      if(i < 10){
        ligne += " ";
      }
    }
    System.out.println(ligne);
    System.out.println();
    for(int i = 0; i < 15; i++){
      String l1 = "     ";
      String l2 = ((i < 10)? : " ") + i + "   ";
      String l3 = "     ";
      for(int j = 0; j < 59; j++){
        Triomino t1 = this.plateau[i][j].getTriomino(0);
        Triomino t2 = this.plateau[i][j].getTriomino(1);
        if(t2 == null || t2.isEmplacement()){
          if(t1 == null || t1.isEmplacement()){
            Triomino p1 = null;
            Triomino p2 = null;
            if(x > 0 && this.plateau[x-1][y] != null){
              p1 = this.plateau[x][y-1].getTriomino(0);
            }
            if(y > 0 && this.plateau[x][y-1] != null){
              p2 = this.plateau[x-1][y].getTriomino(1);
            }
            Integer coin = this.getCoin(i, j, 0);
            l1 += ((coin == null)? " " : coins ) + ((p1 == null)? "  ": "--");
            l2 += ((p2 == null)? " " : "|" ) + "  ";
            l3 += ((p2 == null)? " " : "|" ) + "  ";
          }else{
            Triomino p1 = null;
            if(x > 0 && this.plateau[x-1][y] != null){
              p1 = this.plateau[x][y-1].getTriomino(0);
            }
            l1 += t1.getCoin(0) + ((p1 == null)? "  ": "--");
            l2 += "|\\ ";
            l3 += "|X\\";
          }
        }else{
          if(t1 == null || t1.isEmplacement()){
            Triomino p2 = null;
            if(y > 0 && this.plateau[x][y-1] != null){
              p2 = this.plateau[x][y-1].getTriomino(1);
            }
            l1 += t2.getCoin(0) + "--";
            l2 += ((p2 == null)? " " : "|" ) + "\\X";
            l3 += ((p2 == null)? " " : "|" ) + " \\";
          }else{
            l1 += t2.getCoin(0) + "--";
            l2 += "|\\X";
            l3 += "|X\\";
          }
        }
      }
    }

  }

}
