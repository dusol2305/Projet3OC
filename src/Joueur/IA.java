package Joueur;

public class IA implements Joueur {
    private String combinaison = "4455";

    @Override
    public String demandeCombinaison(String indice) {;
        char[] iaComb = combinaison.toCharArray();
        System.out.println("IA entre un combinaison de 4 chiffres:");
        int i = indice.length() - 1;
        while (i > -1) {
            if (indice.charAt(i) == '-') {
                iaComb[i]--;
                combinaison = String.valueOf(iaComb);
            } else if (indice.charAt(i) == '+') {
                iaComb[i]++;
                combinaison = String.valueOf(iaComb);
            }
            i--;
        }
        System.out.println("Proposition : " + combinaison);
        return combinaison;
    }

    @Override
    public String demandeCombinaisonAleatoire() {
        StringBuilder combinaisonTemp = new StringBuilder();
        String combinaison;

        for (int i = 4; i > 0; i--) {
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
            System.out.println("IA Gagné");
        } else {
            System.out.println("IA Perdu");
        }
    }
}