package kdArbre;

/**
 * Classe Implémentant un noeud d'un 3d-Arbre
 * @author Victor BOIX
 * @version 1.0
 */
public class Noeud {
    private int valeur1;
    private int valeur2;
    private int valeur3;

    /**
     * Constructeur de la classe Noeud
     * @param v1 la premiere valeur du noeud
     * @param v2 la deuxieme valeur du noeud
     * @param v3 la troisieme valeur du noeud
     */
    public Noeud(v1, v2, v3) {
        this.valeur1 = v1;
        this.valeur2 = v2;
        this.valeur3 = v3;
    }

    /**
     *
     * @return la premiere valeur du noeud
     */
    public int getValeur1() {
        return valeur1;
    }

    /**
     * Définie la premiere valeur du noeud
     * @param valeur1 la valeur à définir
     */
    public void setValeur1(int valeur1) {
        this.valeur1 = valeur1;
    }

    /**
     *
     * @return la deuxieme valeur du noeud
     */
    public int getValeur2() {
        return valeur2;
    }

    /**
     * Définie la deuxieme valeur du noeud
     * @param valeur2 la valeur à définir
     */
    public void setValeur2(int valeur2) {
        this.valeur2 = valeur2;
    }

    /**
     *
     * @return la troisieme valeur du noeud
     */
    public int getValeur3() {
        return valeur3;
    }

    /**
     * Définie la troisieme valeur du noeud
     * @param valeur3 la valeur à définir
     */
    public void setValeur3(int valeur3) {
        this.valeur3 = valeur3;
    }

    /**
     *
     * @return le noeud sous forme d'une chaine de carateres
     */
    public String toString() {
        return "Noeud{" +
                "valeur1=" + valeur1 +
                ", valeur2=" + valeur2 +
                ", valeur3=" + valeur3 +
                ", bascule=" + bascule +
                '}';
    }


}