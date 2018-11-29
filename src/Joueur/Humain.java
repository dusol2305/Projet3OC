package Joueur;

import java.util.Scanner;

public class Humain implements Joueur {
    @Override
    public String demandeCombinaison() {
        StringBuilder combinaisonTemp = new StringBuilder();
        String combinaison;
        Scanner sc = new Scanner(System.in);

        System.out.println("Entrer une combinaison :");

        combinaisonTemp.append(sc.nextInt());
        combinaison = combinaisonTemp.toString();

        return combinaison;
    }

    @Override
    public String demandeCombinaisonAleatoire() {
        StringBuilder combinaisonTemp = new StringBuilder();
        String combinaison;

        combinaisonTemp.append((int) (Math.random() * 9 + 0));
        combinaisonTemp.append((int) (Math.random() * 9 + 0));
        combinaisonTemp.append((int) (Math.random() * 9 + 0));
        combinaisonTemp.append((int) (Math.random() * 9 + 0));

        combinaison = combinaisonTemp.toString();
        System.out.println("Aleatoire : " + combinaison); //del
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
