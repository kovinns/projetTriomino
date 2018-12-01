package kdArbre;

public class TdArbre {
    private Noeud noeud;
    private TdArbre filsGauche;
    private TdArbre filsDroit;
    private int bascule;

    public TdArbre(int v1, int v2, int v3) {
        this.noeud = new Noeud(v1, v2, v3);
        this.bascule = 1;
    }

    public TdArbre(Noeud n) {
        this.noeud = n;
        this.bascule = 1;
    }

    public TdArbre(int v1, int v2, int v3, int b) {
        this.noeud = new Noeud(v1, v2, v3);
        this.bascule = b;
    }

    public TdArbre(Noeud n, int b) {
        this.noeud = n);
        this.bascule = b;
    }

    public Noeud getNoeud() {
        return noeud;
    }

    public TdArbre getFilsGauche() {
        return filsGauche;
    }

    public void setFilsGauche(kd.TdArbre filsGauche) {
        this.filsGauche = filsGauche;
    }

    public TdArbre getFilsDroit() {
        return filsDroit;
    }

    public void setFilsDroit(kd.TdArbre filsDroit) {
        this.filsDroit = filsDroit;
    }

    public int getBascule() {
        return this.bascule;
    }

    public void setBascule(int bascule) {
        this.bascule = bascule;
    }

    public int valeurCourante() {
        if(this.bascule == 1) {
            return this.noeud.getValeur1();
        }
        if(this.bascule == 2) {
            return this.noeud.getValeur2();
        }
        if(this.bascule == 3) {
            return this.noeud.getValeur3();
        }
    }

    public int valeurCourante(int b) {
        if(b == 1) {
            return this.noeud.getValeur1();
        }
        if(b == 2) {
            return this.noeud.getValeur2();
        }
        if(b == 3) {
            return this.noeud.getValeur3();
        }
    }

    public int basculeSuivante() {
        int newBascule;
        if(this.bascule == 3) {
            newBascule = 1;
        } else {
            newBascule = this.bascule++;
        }
        return newBascule;
    }

    public int basculePrecedente() {
        int newBascule;
        if(this.bascule == 1) {
            newBascule = 3;
        } else {
            newBascule = this.bascule--;
        }
        return newBascule;
    }

    public int getValABascule(Noeud n, int b) {
        if(b == 1) {
            return n.getValeur1();
        }
        if(b == 2) {
            return n.getValeur2();
        }
        if(b == 3) {
            return n.getValeur2();
        }
    }

    public boolean egalNoeud(Noeud n1, Noeud2) {
        if(n1.getValeur1() == n2.getValeur1() && n1.getValeur2() == n2.getValeur2() && n1.getValeur3() == n2.getValeur3()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean rechercher(TdArbre a) {
        boolean res = false;

        int valCourante = this.valeurCourante();
        int valCourRech = a.valeurCourante(this.bascule);

        if(valCourante == valCourRech) {
            if(egalNoeud(this.noeud, a.getNoeud())) {
                res = true;
            }
        } else {
            if(valCourante > valCourRech) {
                this.filsGauche.rechercher(a);
            } if(valCourante < valCourRech) {
                this.filsDroit.rechercher(a);
            }
        }

        return res;
    }

    public void inserer(TdArbre a) {
        int valCourante = this.valeurCourante();
        int valCourRech = a.valeurCourante(this.bascule);

        if(valCourante >= valCourRech) {
            if(this.filsGauche == null) {
                a.setBascule(this.basculeSuivante());
                this.setFilsGauche(a);
            } else {
                this.filsGauche.inserer(a);
            }
        } else{
            if(this.filsDroit == null) {
                a.setBascule(this.basculeSuivante());
                this.setFilsDroit(a);
            } else {
                this.filsDroit.inserer(a);
            }
        }
    }

    public boolean suprimer(TdArbre a) {
        boolean res = false;

        int valCourante = this.valeurCourante();
        int valCourSupr = a.valeurCourante(this.bascule);

        if(valCourSupr == valCourante) {
            if(egalNoeud(this.noeud, a.getNoeud())) {
                if(this.filsGauche == null) {
                    this.filsDroit.setBascule(basculePrecedente());
                    this = this.filsDroit;
                } else {
                    if(this.filsDroit == null) {
                        this.filsGauche.setBascule(basculePrecedente());
                        this = this.filsGauche;
                    } else {
                        this.filsDroit.inserer(this.filsGauche.getFilsDroit());
                        this.filsGauche.setFilsDroit(this.filsDroit);
                        this.filsGauche.setBascule(basculePrecedente());
                        this = this.filsGauche;
                    }
                }
                res  = true;
            }
        }

        if(valCourante > valCourSupr) {
            this.filsGauche.suprimer(a);
        } else if(valCourante < valCourSupr) {
            this.filsDroit.suprimer(a);
        }

        return res;
    }
}