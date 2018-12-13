package Joueur;

import java.util.Scanner;

public class Humain implements Joueur {
    private String combinaison;
    @Override
    public String demandeCombinaison() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Proposition : ");
        combinaison = sc.nextLine();

        return combinaison;
    }

    @Override
    public String demandeCombinaisonAleatoire() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Entrez une combinaison : ");
        combinaison = sc.nextLine();

        return combinaison;
    }

    @Override
    public void envoyerIndice(String indice) {
        System.out.println("Proposition : " + combinaison + " -> Réponse : " + indice + "\n");
    }

    @Override
    public void affichageResultatPartie(boolean aGagne) {
        if (aGagne) {
            System.out.println("Gagné");
        } else {
            System.out.println("Perdu");
        }
    }
}
