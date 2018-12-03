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
        this.noeud = n;
        this.bascule = b;
    }

    public Noeud getNoeud() {
        return noeud;
    }

    public TdArbre getFilsGauche() {
        return filsGauche;
    }

    public void setFilsGauche(TdArbre filsGauche) {
        this.filsGauche = filsGauche;
    }

    public TdArbre getFilsDroit() {
        return filsDroit;
    }

    public void setFilsDroit(TdArbre filsDroit) {
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
      }else{
        if(this.bascule == 2) {
          return this.noeud.getValeur2();
        }else{
          return this.noeud.getValeur3();
        }
      }
    }

    public int valeurCourante(int b) {
      if(b == 1) {
        return this.noeud.getValeur1();
      }else{
        if(b == 2) {
          return this.noeud.getValeur2();
        }else{
          return this.noeud.getValeur3();
        }
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
      }else{
        if(b == 2) {
          return n.getValeur2();
        }else{
          return n.getValeur2();
        }
      }
    }

    public boolean egalNoeud(Noeud n1, Noeud n2) {
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

    public Noeud max(int d){
      Noeud mG = null;
      if(this.filsGauche != null){
        mG = this.filsGauche.max(d);
      }
      Noeud mD = null;
      if(this.filsDroit != null){
        mD = this.filsDroit.max(d);
      }
      int vG = ((mG != null)? ((d == 1)? mG.getValeur1() : ((d == 2)? mG.getValeur2() : mG.getValeur3() ) ) : -1 );
      int vD = ((mD != null)? ((d == 1)? mD.getValeur1() : ((d == 2)? mD.getValeur2() : mD.getValeur3() ) ) : -1 );
      int v = this.valeurCourante(d);
      if(v > vG && v > vD){
        return this.noeud;
      }else{
        if(vG > vD){
          return mG;
        }else{
          return mD;
        }
      }
    }

  // public boolean suprimer(TdArbre a) {
  //   boolean res = false;
  //
  //   int valCourante = this.valeurCourante();
  //   int valCourSupr = a.valeurCourante(this.bascule);
  //
  //   if(valCourSupr == valCourante) {
  //     if(egalNoeud(this.noeud, a.getNoeud())) {
  //       if(this.filsGauche == null) {
  //         this.filsDroit.setBascule(basculePrecedente());
  //         this = this.filsDroit;
  //       } else {
  //         if(this.filsDroit == null) {
  //           this.filsGauche.setBascule(basculePrecedente());
  //           this = this.filsGauche;
  //         } else {
  //           this.filsDroit.inserer(this.filsGauche.getFilsDroit());
  //           this.filsGauche.setFilsDroit(this.filsDroit);
  //           this.filsGauche.setBascule(basculePrecedente());
  //           this = this.filsGauche;
  //         }
  //       }
  //       res  = true;
  //     }
  //   }
  //
  //   if(valCourante > valCourSupr) {
  //     this.filsGauche.suprimer(a);
  //   } else if(valCourante < valCourSupr) {
  //     this.filsDroit.suprimer(a);
  //   }
  //
  //   return res;
  // }

}
