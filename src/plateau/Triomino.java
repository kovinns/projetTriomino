package plateau;

 public class Triomino{

  private int[] coins;
  private int x, y, orientation;

  public Triomino(int a, int b, int c){
    this.coins = new int[3];
    this.coins[0] = a;
    this.coins[1] = b;
    this.coins[2] = c;
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

  public String toString(){
    return this.coins[0] + " " + this.coins[1] + " " + this.coins[2];
  }

}
