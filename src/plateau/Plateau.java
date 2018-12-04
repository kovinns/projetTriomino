package plateau;

import java.util.Scanner;
import java.util.ArrayList;

import structRecherche.StructRecherche;
import structRecherche.kdArbre.TdArbre;

public class Plateau{

  private PaireTriominos[][] plateau;
  private int minX, minY, maxX, maxY;
  private StructRecherche struct;

  public Plateau(){
    this.plateau = new PaireTriominos[30][59];
    for(int i = 0; i < 30; i++){
      for(int j = 0; j < 59; j++){
        this.plateau[i][j] = null;
      }
    }
    this.struct = new TdArbre();
    this.minX = 29;
    this.minY = 59;
    this.maxX = 0;
    this.maxY = 0;
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
        if(x < 29){
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
          if(y < 29){
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
        if(x < 29){
          if(this.plateau[x+1][y] != null){
            this.plateau[x+1][y].maj(1, 1, 2, t);
          }
        }
      }
    }
    if(place){
      if(x < this.minX){
        this.minX = x;
      }
      if(y < this.minY){
        this.minY = y;
      }
      if(x > this.maxX){
        this.maxX = x;
      }
      if(y > this.maxY){
        this.maxY = y;
      }
    }
    return place;
  }

  public void setEmplacement(Triomino t, int x, int y, int p){
    if(this.plateau[x][y] == null){
      this.plateau[x][y] = new PaireTriominos(x, y, this);
    }
    this.plateau[x][y].setTriomino(t, p);
    this.struct.ajouterTriomino(t);
  }

  public Integer getCoin(int x, int y, int c){
    Integer valeur = null;
    if(this.plateau[x][y] != null){
      valeur = this.plateau[x][y].getCoin(c);
    }
    if(valeur == null){
      if((c == 0 || c == 1) && x > 0 && this.plateau[x-1][y] != null){
        valeur = this.plateau[x-1][y].getCoin(3-c);
      }
      if(valeur == null){
        if(c == 0 && x > 0 && y > 0 && this.plateau[x-1][y-1] != null){
          valeur = this.plateau[x-1][y-1].getCoin(2);
        }
        if(valeur == null){
          if((c == 0 || c == 3) && y > 0 && this.plateau[x][y-1] != null){
            valeur = this.plateau[x][y-1].getCoin((c+3)/3);
          }
          if(valeur == null){
            if(c == 3 && x < 29 && y > 0 && this.plateau[x+1][y-1] != null){
              valeur = this.plateau[x+1][y-1].getCoin(1);
            }
            if(valeur == null){
              if((c == 2 || c == 3) && y < 58 && this.plateau[x+1][y] != null){
                valeur = this.plateau[x+1][y].getCoin(3-c);
              }
              if(valeur == null){
                if(c == 2 && x < 29 && y < 58 && this.plateau[x+1][y+1] != null){
                  valeur = this.plateau[x+1][y+1].getCoin(0);
                }
                if(valeur == null){
                  if((c == 1 || c == 2) && x < 29 && this.plateau[x][y+1] != null){
                    valeur = this.plateau[x][y+1].getCoin((c-1)*3);
                  }
                  if(valeur == null){
                    if(c == 1 && x > 0 && y < 58 && this.plateau[x-1][y+1] != null){
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

  public void afficher(){
    System.out.println();
    String ligne = "      ";
    for(int i = minY; i <= maxY; i++){
      ligne += i + " ";
      if(i < 10){
        ligne += " ";
      }
    }
    System.out.println(ligne);
    System.out.println();
    for(int i = minX; i <= maxX; i++){
      String l1 = "     ";
      String l2 = ((i < 10)? " " : "") + i + "   ";
      String l3 = "     ";
      for(int j = minY; j <= maxY; j++){
        Triomino t1 = null;
        Triomino t2 = null;
        if(this.plateau[i][j] != null){
          t1 = this.plateau[i][j].getTriomino(0);
          t2 = this.plateau[i][j].getTriomino(1);
        }
        if(t2 == null || t2.isEmplacement()){
          if(t1 == null || t1.isEmplacement()){
            Triomino p1 = null;
            Triomino p2 = null;
            if(i > 0 && this.plateau[i-1][j] != null){
              p1 = this.plateau[i-1][j].getTriomino(0);
            }
            if(j > 0 && this.plateau[i][j-1] != null){
              p2 = this.plateau[i][j-1].getTriomino(1);
            }
            Integer coin = this.getCoin(i, j, 0);
            l1 += ((coin == null)? " " : coin ) + ((p1 == null || p1.isEmplacement())? "  ": "--");
            l2 += ((p2 == null || p2.isEmplacement())? " " : "|" ) + "  ";
            l3 += ((p2 == null || p2.isEmplacement())? " " : "|" ) + "  ";
          }else{
            Triomino p1 = null;
            if(i > 0 && this.plateau[i-1][j] != null){
              p1 = this.plateau[i-1][j].getTriomino(0);
            }
            Integer coin = t1.getCoin(0);
            l1 += coin + ((p1 == null || p1.isEmplacement())? "  ": "--");
            l2 += "|\\ ";
            l3 += "|X\\";
          }
          if(j == maxY){
            Integer coin = this.getCoin(i, j, 1);
            l1 += ((coin == null)? " " : coin );
            l2 += " ";
            l3 += " ";
          }
        }else{
          if(t1 == null || t1.isEmplacement()){
            Triomino p2 = null;
            if(j > 0 && this.plateau[i][j-1] != null){
              p2 = this.plateau[i][j-1].getTriomino(1);
            }
            Integer coin = t2.getCoin(0);
            l1 += coin + "--";
            l2 += ((p2 == null || p2.isEmplacement())? " " : "|" ) + "\\X";
            l3 += ((p2 == null || p2.isEmplacement())? " " : "|" ) + " \\";
          }else{
            Integer coin = t2.getCoin(0);
            l1 += coin + "--";
            l2 += "|\\X";
            l3 += "|X\\";
          }
          if(j == maxY){
            Integer coin = t2.getCoin(1);
            l1 += coin;
            l2 += "|";
            l3 += "|";
          }
        }
      }
      System.out.println(l1);
      System.out.println(l2);
      System.out.println(l3);
    }
    String l = "     ";
    for(int j = minY; j <= maxY; j++){
      Triomino t1 = null;
      if(this.plateau[maxX][j] != null){
        t1 = this.plateau[maxX][j].getTriomino(0);
      }
      if(t1 != null && !t1.isEmplacement()){
        l += t1.getCoin(2) + "--";
      }else{
        Integer coin = this.getCoin(maxX, j, 3);
        l += ((coin != null)? coin : " " ) + "  ";
      }
    }
    Integer coin = this.getCoin(maxX, maxY, 2);
    l += ((coin != null)? coin : " " );
    System.out.println(l);
    System.out.println();
  }

  public boolean addInStruct(Triomino t){
    return this.struct.ajouterTriomino(t);
  }

  public boolean supprInStruct(Triomino t){
    return this.struct.supprimerTriomino(t);
  }

  public ArrayList<Triomino> rechercheInStrut(Triomino t){
    return this.struct.rechercher(t);
  }

  public int nombrePlaceParCoin(int x, int y, int c){
    int nombre = 0;
    if(plateau[x][y] != null){
      nombre += plateau[x][y].nombrePlaceParCoin(c);
    }
    if(c < 2){
      if(x > 0){
        if(plateau[x-1][y] != null){
          nombre += plateau[x-1][y].nombrePlaceParCoin(3-c);
        }
        if(c == 0){
          if(y > 0){
            if(plateau[x-1][y-1] != null){
              nombre += plateau[x-1][y-1].nombrePlaceParCoin(2);
            }
          }
        }else{
          if(y < 58){
            if(plateau[x-1][y+1] != null){
              nombre += plateau[x-1][y+1].nombrePlaceParCoin(3);
            }
          }
        }
      }
      if(c == 0){
        if(y > 0){
          if(plateau[x][y-1] != null){
            nombre += plateau[x][y-1].nombrePlaceParCoin(1);
          }
        }
      }else{
        if(y < 58){
          if(plateau[x][y+1] != null){
            nombre += plateau[x][y+1].nombrePlaceParCoin(0);
          }
        }
      }
    }else{
      if(x < 29){
        if(plateau[x+1][y] != null){
          nombre += plateau[x+1][y].nombrePlaceParCoin(3-c);
        }
        if(c == 3){
          if(y > 0){
            if(plateau[x+1][y-1] != null){
              nombre += plateau[x+1][y-1].nombrePlaceParCoin(1);
            }
          }
        }else{
          if(y < 58){
            if(plateau[x+1][y+1] != null){
              nombre += plateau[x+1][y+1].nombrePlaceParCoin(0);
            }
          }
        }
      }
      if(c == 3){
        if(y > 0){
          if(plateau[x][y-1] != null){
            nombre += plateau[x][y-1].nombrePlaceParCoin(2);
          }
        }
      }else{
        if(y < 58){
          if(plateau[x][y+1] != null){
            nombre += plateau[x][y+1].nombrePlaceParCoin(3);
          }
        }
      }
    }
    return nombre;
  }

  public String afficherStruct(){
    return this.struct.toString();
  }

  public String toString(){
    String retour = "";
    for(int i = 0; i < 30; i++){
      for(int j = 0; j < 59; j++){
        if(plateau[i][j] != null){
          retour += i + "," + j + ":" +plateau[i][j].toString();
        }
      }
    }
    return retour;
  }

}
