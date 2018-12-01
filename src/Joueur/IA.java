package Joueur;

public class IA implements Joueur {
    /*@Override
    public String demandeCombinaison(String indice) {
        String combinaison = "4455";
        int charValue;
        char charIndice;
        System.out.println("IA entre une combinaison de 4 chiffres:");

        for (int i = indice.length() - 1; i > -1; i--) {
            if (indice.charAt(i) == '-') {
                charValue = combinaison.charAt(i) - 1;
                charIndice = (char) charValue;
                combinaison.charAt(i) = charIndice;
            } else if (indice.charAt(i) == '+') {
                charValue = combinaison.charAt(i) + 1;
                charIndice = (char) charValue;
                combinaison.charAt(i) = charIndice;
            }
        }

        return combinaison;
    }*/

    @Override
    public String demandeCombinaison(String indice) {
        String combinaison = "4455"; //gneeeee
        char[] iaComb = combinaison.toCharArray();
        System.out.println("==== \n IA entre un combinaison de 4 chiffres:");
        int i = indice.length() - 1;
        while (i > -1) {
            System.out.println("i = " + i); //dell
            if (indice.charAt(i) == '-') {
                iaComb[i]--;
                combinaison = String.valueOf(iaComb);
            } else if (indice.charAt(i) == '+') {
                iaComb[i]++;
                combinaison = String.valueOf(iaComb);
            }
            i--;
        }
        System.out.println("combinaison entrée : " + combinaison + "\n====");
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
        System.out.println("NB_Aleatoire : " + combinaison); //del
        return combinaison;
    }

    @Override
    public void affichageIndice(String indice) {
        System.out.println("Réponse : " + indice);
    }

    @Override // classe pas utile pour IA
    public void affichageResultatPartie(boolean aGagne) {
        if (aGagne) {
            System.out.println("IA Gagné");
        } else {
            System.out.println("IA Perdu");
        }
    }
}