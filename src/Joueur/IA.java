package Joueur;

import java.util.Scanner;

public class IA implements Joueur{
    @Override
    public String demandeCombinaison() {
        StringBuilder combinaisonTemp = new StringBuilder();
        String combinaison;
        Scanner sc = new Scanner(System.in);

        System.out.println("Entrer une combinaison :");

        //boucle for avec generation en fonction de l'indice precedent
        combinaison = combinaisonTemp.toString();

        return combinaison;
    }

    @Override
    public String demandeCombinaisonAleatoire() {
        return null;
    }

    @Override
    public void affichageIndice(String indice) {

    }

    @Override
    public void affichageResultatPartie(boolean aGagne) {

    }
}
