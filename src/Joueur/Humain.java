package Joueur;

import java.util.Scanner;

public class Humain implements Joueur {
    @Override
    public String demandeCombinaison(String indice) {
        this.affichageIndice(indice);
        StringBuilder combinaisonTemp = new StringBuilder();
        String combinaison;
        Scanner sc = new Scanner(System.in);

        System.out.println("Entrer une combinaison de 4 chiffres:");

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
        System.out.println("NB_Aleatoire : " + combinaison); //del
        return combinaison;
    }

    @Override
    public void affichageIndice(String indice) {
        System.out.println("Réponse : " + indice);
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
