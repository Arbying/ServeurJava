package Tests;

import BeansModele.ClientBean;

public class ClientBeanTest {
    public static void main(String[] args) {
        testClientBean();
    }

    public static void testClientBean() {
        // Création du client
        ClientBean client = new ClientBean(1, "Pimousse", "motdepasse");

        // Affichage du client initial
        System.out.println("Client initial :");
        System.out.println(client);

        // Utilisation des setters pour modifier les valeurs du client
        client.setId(2);
        client.setNom("Vovo");
        client.setMdp("nouveaumotdepasse");

        // Affichage des attributs avec les getters
        System.out.println("Client après modification :");
        System.out.println("ID : " + client.getId());
        System.out.println("Nom : " + client.getNom());

        // Affichage des méthodes toString, toStringTitres et toStringLigne
        System.out.println("Méthode toString :");
        System.out.println(client.toString());

        System.out.println("Méthode toStringTitres :");
        System.out.println(ClientBean.toStringTitres());

        System.out.println("Méthode toStringLigne :");
        System.out.println(client.toStringLigne());
    }
}
