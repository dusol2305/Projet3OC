package Joueur;

public class IAmastermind implements Joueur {
    String indice;
    private String combinaison = "0000"; //1bp 2 présent
    private String combinaison2 = "1956"; // 1bp 2présent

    @Override
    public String demandeCombinaison() {
        combinaison = demandeCombinaisonAleatoire();
        // mettre code avant
        System.out.print("Proposition : " + combinaison + " -> ");
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
        return combinaison;
    }

    @Override
    public void envoyerIndice(String indice) {
        System.out.println(indice);
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
