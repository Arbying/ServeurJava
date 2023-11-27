package Tests;

import BeansModele.ArticleBean;
import BeansModele.ClientBean;
import BeansModele.FactureBean;
import BeansModele.VenteBean;

public class VenteBeanTest {
    public static void main(String[] args) {
        testVenteBean();
    }

    public static void testVenteBean() {
        // Création d'un objet ClientBean
        ClientBean client = new ClientBean(1, "Pimousse", "mdpPimousse");

        // Création d'un objet FactureBean
        FactureBean facture = new FactureBean(101, client, 150.0f, true, "2023-01-10");

        // Création de deux objets ArticleBean
        ArticleBean article1 = new ArticleBean(1, "Carottes", 2.16f, 10, "carottes.jpg");
        ArticleBean article2 = new ArticleBean(2, "Pommes", 1.50f, 20, "pommes.jpg");

        // Création de deux ventes
        VenteBean vente1 = new VenteBean(facture, article1, 5);
        VenteBean vente2 = new VenteBean(facture, article2, 10);

        // Affichage des ventes initiales
        System.out.println("Vente 1 initiale:");
        System.out.println(vente1);
        System.out.println("Vente 2 initiale:");
        System.out.println(vente2);

        // Modification des ventes
        vente1.setQuantite(7);
        vente2.setArticle(new ArticleBean(3, "Bananes", 2.00f, 15, "bananes.jpg"));

        // Affichage des ventes après modification
        System.out.println("\nVente 1 après modification:");
        System.out.println(vente1);
        System.out.println("Vente 2 après modification:");
        System.out.println(vente2);
    }
}
