package Joueur;

public class Humain implements Joueur {
    @Override
    public String demandeCombinaison() {
        System.out.println("combinaison");
        return null;
    }

    @Override
    public String demandeCombinaisonAleatoire() {
        System.out.println("Combinaison aleatoire");
        return null;
    }

    @Override
    public void affichageIndice(String indice) {
        System.out.println("Réponse : "+ indice);
    }

    @Override
    public void affichageResultatPartie(boolean aGagne) {
        if (aGagne){
            System.out.println("Gagné");
        } else {
            System.out.println("Perdu");
        }
    }
}
