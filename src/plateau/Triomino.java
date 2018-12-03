package plateau;

 public class Triomino{

  private Integer[] coins;
  private int x, y, orientation, score;
  private boolean emplacement;

  public Triomino(Integer a, Integer b, Integer c, boolean emplacement){
    this.coins = new Integer[3];
    this.coins[0] = a;
    this.coins[1] = b;
    this.coins[2] = c;
    this.orientation = 0;
    this.emplacement = emplacement;
    this.score = 0;
  }

  public void positionner(int x, int y, int orientation){
    this.x = x;
    this.y = y;
    this.orientation = orientation;
    orientation %= 3;
    if(orientation == 1){
      int save = this.coins[0];
      this.coins[0] = this.coins[2];
      this.coins[2] = this.coins[1];
      this.coins[1] = save;
    }else if(orientation == 2){
      int save = this.coins[0];
      this.coins[0] = this.coins[1];
      this.coins[1] = this.coins[2];
      this.coins[2] = save;
    }
  }

  public Integer getCoin(int i){
    return this.coins[i%3];
  }

  public void setCoin(int i, Integer a){
    this.coins[i%3] = a;
  }

  public boolean isEmplacement(){
    return this.emplacement;
  }

  public void rotation(){
    if(!this.emplacement){
      int save = this.coins[0];
      this.coins[0] = this.coins[2];
      this.coins[2] = this.coins[1];
      this.coins[1] = save;
    }
  }

  public boolean remplacable(Triomino t, int orientation){
    if(!this.emplacement){
      return false;
    }
    for(int i = 0; i < 3; i++){
      if(this.coins[i] != null && this.coins[i] != t.getCoin((i+orientation)%3)){
        return false;
      }
    }
    return true;
  }

  public Integer getOrientation(Triomino t){
    for(int i = 0; i < 3; i++){
      if(this.remplacable(t, i)){
        return i;
      }
    }
    return -1;
  }

  public int getX(){
    return this.x;
  }

  public int getY(){
    return this.y;
  }

  public int getScore(){
    return this.score;
  }

  public void setScore(int score){
    this.score = score;
  }

  public String toString(){
    return this.coins[0] + " " + this.coins[1] + " " + this.coins[2];
  }

}
