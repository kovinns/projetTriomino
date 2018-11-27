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
    this.plateau[15][29] = new PaireTriominos(15, 29);
    this.plateau[15][29].addEmplacement(new Triomino(null, null, null, true));
  }

  public boolean addTriomino(Triomino t, int x, int y, int orientation){
    //TODO mettre à jour la struct de recherche à chaque ajout de triomino
    if(x < 0 || x > 29 || y < 0 || y > 58){
      return false;
    }
    if(this.plateau[x][y] == null){
      return false;
    }
    boolean place = this.plateau[x][y].addTriomino(t, orientation);
    if(place){
      if(orientation < 3){
        if(x > 0){
          if(this.plateau[x-1][y] == null){
            this.plateau[x-1][y] = new PaireTriominos(x-1, y);
          }
          this.plateau[x-1][y].maj(1, 2, t);
        }
        if(y > 0){
          if(this.plateau[x][y-1] == null){
            this.plateau[x][y-1] = new PaireTriominos(x-1, y);
          }
          this.plateau[x][y-1].maj(1, 2, t);
        }
        this.plateau[x][y].maj(1, 2, t);
      }else{

      }
    }
    return place;
  }

}
