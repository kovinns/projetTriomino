package structRecherche;

import java.util.ArrayList;

import plateau.Triomino;

public interface StructRecherche{

  boolean ajouterTriomino(Triomino t);

  boolean supprimerTriomino(Triomino t);

  ArrayList<Triomino> rechercher(Triomino t);

  String toString();

}
