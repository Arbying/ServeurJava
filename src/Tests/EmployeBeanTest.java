package Tests;

import BeansModele.EmployeBean;
import BeansModele.FonctionsTravBean;

public class EmployeBeanTest {
    public static void main(String[] args) {
        testEmployeBean();
    }

    public static void testEmployeBean() {
        // Création d'un objet FonctionsTravBean pour la fonction de l'employé
        FonctionsTravBean fonctionGerant = new FonctionsTravBean(1, "Gerant");
        FonctionsTravBean fonctionCaissier = new FonctionsTravBean(2, "Caissier");
        FonctionsTravBean fonctionReassortisseur = new FonctionsTravBean(3, "Reassortisseur");

        // Création des employés
        EmployeBean employe1 = new EmployeBean(1, "Pimousse", "mdp1", fonctionGerant, "oui");
        EmployeBean employe2 = new EmployeBean(2, "Vovo", "mdp2", fonctionCaissier, "non");
        EmployeBean employe3 = new EmployeBean(3, "Tippex", "mdp3", fonctionReassortisseur, "oui");

        // Affichage initial des employés sous forme de tableau
        System.out.println("Employés initiaux :");
        System.out.println(EmployeBean.toStringTitres());
        System.out.println(employe1.toStringLigne());
        System.out.println(employe2.toStringLigne());
        System.out.println(employe3.toStringLigne());

        // Modification des employés
        employe1.setNom("PimousseModifié");
        employe2.setMdp("mdp2Modifié");
        employe3.setTypeEmploye(fonctionGerant);

        // Affichage des employés après modification sous forme de tableau
        System.out.println("\nEmployés après modification :");
        System.out.println(EmployeBean.toStringTitres());
        System.out.println(employe1.toStringLigne());
        System.out.println(employe2.toStringLigne());
        System.out.println(employe3.toStringLigne());
    }
}
