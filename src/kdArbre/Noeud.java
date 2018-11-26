package kdArbre;

public class Noeud {
    private int valeur1;
    private int valeur2;
    private int valeur3;

    public Noeud(v1, v2, v3) {
        this.valeur1 = v1;
        this.valeur2 = v2;
        this.valeur3 = v3;
    }

    public int getValeur1() {
        return valeur1;
    }

    public void setValeur1(int valeur1) {
        this.valeur1 = valeur1;
    }

    public int getValeur2() {
        return valeur2;
    }

    public void setValeur2(int valeur2) {
        this.valeur2 = valeur2;
    }

    public int getValeur3() {
        return valeur3;
    }

    public void setValeur3(int valeur3) {
        this.valeur3 = valeur3;
    }

    public String toString() {
        return "Noeud{" +
                "valeur1=" + valeur1 +
                ", valeur2=" + valeur2 +
                ", valeur3=" + valeur3 +
                ", bascule=" + bascule +
                '}';
    }


}