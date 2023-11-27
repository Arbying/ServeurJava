package Tests;

import BeansModele.ClientBean;
import BeansModele.FactureBean;

public class FactureBeanTest {
    public static void main(String[] args) {
        testFactureBean();
    }

    public static void testFactureBean() {
        // Création d'objets ClientBean
        ClientBean client1 = new ClientBean(1, "Pimousse", "mdpPimousse");
        ClientBean client2 = new ClientBean(2, "Vovo", "mdpVovo");

        // Création de factures
        FactureBean facture1 = new FactureBean(101, client1, 150.0f, false, "2023-01-10");
        FactureBean facture2 = new FactureBean(102, client2, 200.0f, true, "2023-01-11");

        // Affichage des factures initiales
        System.out.println("Facture 1 initiale:");
        System.out.println(facture1);
        System.out.println("Facture 2 initiale:");
        System.out.println(facture2);

        // Modification des factures
        facture1.setMontant(155.0f);
        facture1.setPaye(true);
        facture2.setDate("2023-01-12");

        // Affichage des factures après modification
        System.out.println("\nFacture 1 après modification:");
        System.out.println(facture1);
        System.out.println("Facture 2 après modification:");
        System.out.println(facture2);
    }
}
