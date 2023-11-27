package Tests;

import BeansModele.FonctionsTravBean;

public class FonctionsTravBeanTest {
    public static void main(String[] args) {
        testFonctionsTravBean();
    }

    public static void testFonctionsTravBean() {
        // Création d'une fonction
        FonctionsTravBean fonction = new FonctionsTravBean(1, "Gerant");

        // Affichage de la fonction initiale
        System.out.println("Fonction initiale :");
        System.out.println(fonction);

        // Utilisation des setters pour modifier les valeurs de la fonction
        fonction.setType(2);
        fonction.setFonction("Caissier");

        // Affichage des attributs avec les getters
        System.out.println("Fonction après modification :");
        System.out.println("Type : " + fonction.getType());
        System.out.println("Fonction : " + fonction.getFonction());

        // Affichage des méthodes toString, toStringTitres et toStringLigne
        System.out.println("Méthode toString :");
        System.out.println(fonction.toString());

        System.out.println("Méthode toStringTitres :");
        System.out.println(FonctionsTravBean.toStringTitres());

        System.out.println("Méthode toStringLigne :");
        System.out.println(fonction.toStringLigne());
    }
}
