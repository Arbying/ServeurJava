package Tests;

import BeansModele.ClientBean;
import database.ClientDao;
import database.DataBaseBean;

public class TestClientDao {

    public static void main(String[] args) {
        try {
            // Initialisation du DataBaseBean
            DataBaseBean dataBaseBean = new DataBaseBean("PourStudent", "Student", "PassStudent1_");
            ClientDao clientDao = new ClientDao(dataBaseBean);

            // Création d'un nouveau client
            ClientBean newClient = new ClientBean(-1, "NouveauClient", "password123");
            clientDao.ajoutClient(newClient);
            System.out.println("Client ajouté avec succès.");

            // Mise à jour du client
            newClient.setNom("ClientModifié");
            newClient.setMdp("nouveauPassword");
            clientDao.modifClient(newClient);
            System.out.println("Client modifié avec succès.");

            // Récupération du client
            ClientBean retrievedClient = clientDao.selectClient(newClient.getId());
            if (retrievedClient != null) {
                System.out.println("Client récupéré : " + retrievedClient);
            }

            // Suppression du client
            clientDao.supprimeClient(newClient);
            System.out.println("Client supprimé avec succès.");

            // Fermeture de la connexion à la base de données
            dataBaseBean.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
