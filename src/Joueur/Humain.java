package Joueur;

import java.util.Scanner;

public class Humain implements Joueur {
    private String combinaison;
    @Override
    public String demandeCombinaison(String indice) {
        StringBuilder combinaisonTemp = new StringBuilder();
        Scanner sc = new Scanner(System.in);

        System.out.print("Proposition : ");

        combinaisonTemp.append(sc.nextInt());
        combinaison = combinaisonTemp.toString();

        return combinaison;
    }

    @Override
    public String demandeCombinaisonAleatoire() {
        StringBuilder combinaisonTemp = new StringBuilder();
        String combinaison;

        for (int i = 4; i>0; i--){
            combinaisonTemp.append((int) (Math.random() * 9 + 0));
        }

        combinaison = combinaisonTemp.toString();
        System.out.println("NB_Aleatoire : " + combinaison); //dell
        return combinaison;
    }

    @Override
    public void affichageIndice(String indice) {
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
