package plateau;

public class PaireTriominos{

  Triomino[] paire;
  int x, y;

  public PaireTriominos(int x, int y){
    this.x = x;
    this.y = y;
    this.paire = new Triomino[2];
    this.paire[0] = null;
    this.paire[1] = null;
  }

  public void addTriomino(Triomino t, int orientation){
    t.positionner(this.x, this.y, orientation);
    if(orientation > 2){
      this.paire[1] = t;
    }else{
      this.paire[1] = t;
    }
  }

  public void setPosition(int x, int y){
    this.x = x;
    this.y = y;

  }

}
