package Joueur;

public class IAmastermind implements Joueur {
    private String combinaison;

    @Override
    public String demandeCombinaison() {
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
        System.out.println(combinaison); // dell
        return combinaison;
    }

    @Override
    public void envoyerIndice(String indice) {
    }

    @Override
    public void affichageResultatPartie(boolean aGagne) {
        if (aGagne) {
            System.out.println("IAmastermind Gagné");
        } else {
            System.out.println("IAmastermind Perdu");
        }
    }
}
