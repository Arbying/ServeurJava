package Protocoles;

import BeansModele.*;
import database.*;
import java.sql.SQLException;
import java.util.*;


public class VESPAP {
    public DataBaseBean dataBaseBean;
    public FonctionsTravDao fonctionTrav;
    public EmployeDao employeDao;
    public ClientDao clientDao;
    public FactureDao factureDao;

    public VESPAP() {
        try {
            // Initialisez ici votre instance de DataBaseBean
            dataBaseBean = new DataBaseBean("PourStudent", "Student", "PassStudent1_");
            fonctionTrav = new FonctionsTravDao(dataBaseBean);
            employeDao = new EmployeDao(dataBaseBean, fonctionTrav);
            clientDao = new ClientDao(dataBaseBean);
            factureDao = new FactureDao(dataBaseBean, clientDao);


        } catch (SQLException e) {
            // Gérez l'exception ici, par exemple, affichez une erreur ou enregistrez-la
            e.printStackTrace();
        }
    }

    public int VESPAP(String input) {
        String[] elements = input.split("#");
        String command = elements[0]; // La première partie est la commande

        int codeRetour = 0; // Par défaut, considérez que tout se passe bien

        // Utilisez un switch pour traiter les différentes commandes
        switch (command) {
            case "LOGIN":
                codeRetour = 1;
                break;
            case "GETFACTURE":
                codeRetour = 2;
                System.out.println("On est dans le cas 2 ----");
            break;
            case "PAYFAC":
                codeRetour = 3;
                System.out.println("On est dans le cas 3 ----");
                break;
            case "FACCOMPLETE":
                codeRetour = 4;
                break;
            case "LOGOUT":
                codeRetour = 5;
                break;
            default:
                // Commande inconnue
                codeRetour = -1; // Utilisez une valeur négative pour indiquer une erreur
        }

        return codeRetour;
    }

    public EmployeBean JeMeLogue(String données) {
        // Divisez les données en éléments séparés par '#'
        String[] elements = données.split("#");

        // Assurez-vous qu'il y a suffisamment d'éléments dans le tableau
        if (elements.length >= 3) {
            String login = elements[1]; // Récupérez le login
            String mdp = elements[2];   // Récupérez le mot de passe

            try {
                // Recherchez l'employé dans la base de données
                EmployeBean employe = employeDao.rechercheEmploye(login, mdp);

                // Vérifiez si l'employé existe et que sa fonction est de type 2
                if (employe != null && employe.getTypeEmploye().getType() == 2) {
                    // L'employé existe et a une fonction de type 2
                   // employe.toStringLigne();
                    System.out.println("------------------");
                    return employe;
                } else {
                    // L'employé n'existe pas ou sa fonction n'est pas de type 2
                    // Gérez cette situation comme nécessaire, par exemple, retournez null ou affichez un message d'erreur
                    return null;
                }
            } catch (SQLException e) {
                // Gérez l'exception SQLException ici, par exemple, affichez une erreur ou enregistrez-la
                e.printStackTrace();
                EmployeBean EmployeNull = new EmployeBean();
                return EmployeNull; // Ou gérez l'erreur de manière appropriée
            }
        } else {
            EmployeBean EmployeNull = new EmployeBean();
            return EmployeNull;
        }
    }

    public String facturesClient(String input) {
        String[] elements = input.split("#");
        Vector<FactureBean> mesFactures = null;
        if (elements.length >= 2) {
            try {
                int id = Integer.parseInt(elements[1]);
                System.out.println("ID : " + id);
                try {
                    mesFactures = factureDao.mesFactures(id);
                } catch (SQLException e) {
                    return "NBFACTURES#-1";
                }
                if (mesFactures != null) {
                    StringBuilder mareponseBuilder = new StringBuilder("NBFACTURES#");
                    mareponseBuilder.append(mesFactures.size()).append("#"); // Nombre d'éléments dans le vecteur

                    for (FactureBean facture : mesFactures) {
                        mareponseBuilder
                                .append(facture.getId()).append("#") // ID de la facture
                                .append(facture.getMontant()).append("#") // Montant de la facture
                                .append(facture.isPaye() ? 1 : 0).append("#") // Statut de paiement (0 pour non payé, 1 pour payé)
                                .append(facture.getDate()).append("#"); // Date de la facture
                    }

                    String mareponse = mareponseBuilder.toString();
                    return mareponse;
                } else {
                    // Gérer le cas où mesFactures est null
                    String mareponse = "NBFACTURES#0#"; // Aucune facture trouvée
                    return mareponse;
                }

            } catch (NumberFormatException e) {
                System.out.println("Erreur de conversion en entier.");
                return "NBFACTURES#-1";
            }
        } else {
            System.out.println("Le format de la chaîne n'est pas valide.");
            return "NBFACTURES#-1";

        }
    }


    public ClientBean clientSelectionne(String input) {
        String[] elements = input.split("#");
        ClientBean client = null;
        if (elements.length >= 2) {
            try {
                int id = Integer.parseInt(elements[1]);
                System.out.println("ID : " + id);
                try {
                    client = clientDao.selectClient(id);
                    return client;
                } catch (SQLException e) {
                    return client;
                }
            } catch (NumberFormatException e) {
                return client;
            }

        }
        return client;
    }


    public String paiementfacture (String input)
    {
        int paiementOk = 0;
        int dejaPaye = 0;
        int idFacture = 0;
        String TitulaireCarte = "";
        String numCarte = "";


        String[] elements = input.split("#");
        if (elements.length >= 4) {
            try {
                idFacture = Integer.parseInt(elements[1]);
                System.out.println("Facture à payer : " + idFacture);
                TitulaireCarte = elements[2];
                System.out.println("Titulaire carte : " + TitulaireCarte);
                numCarte = elements[3];
                System.out.println("Carte numero : " + numCarte);

                numCarte = elements[3];
            } catch (NumberFormatException e)
            {
                System.out.println("Erreur de parcing");
                    return "REPPAIEMENT#KO";
            }
        }
        else
        {
           System.out.println("Mauvais paramettres");
            return "REPPAIEMENT#KO";
        }

        try
        {
            dejaPaye = factureDao.VerifPaiement(idFacture);
            System.out.println("Etat de paiement (0 pas payée) : " + dejaPaye);
        } catch (SQLException e) {
            System.out.println("Erreur de consultat facture à payer");
            return "REPPAIEMENT#KO";
        }

        if (dejaPaye == 0)
        {

            if (estValideLuhn(numCarte))
            {
                System.out.println("Vérification carte réussie");
                paiementOk = 1;
            }

            if (paiementOk == 1) {
                try {
                    paiementOk = factureDao.paiementFait(idFacture);
                }
                catch (SQLException e)
                {
                    return "REPPAIEMENT#OK";
                }

               if (paiementOk == 1)
               {
                   return "REPPAIEMENT#OK";
               }
               else
               {
                   return "REPPAIEMENT#KO";
               }
            }
            else
            {
                System.out.println("Mauvais paramettres L223. Deja payé = " + dejaPaye);
                return "REPPAIEMENTKO#CARTEERREUR";
            }

        }
        else
        {
            System.out.println("Mauvais paramettres L 230");
            return "REPPAIEMENT#KO";
        }

        //return "REPPAIEMENT#KO";
    }

    private boolean estValideLuhn(String numCarte) {
        int sum = 0;
        boolean doubleDigit = false;

        // Parcourez le numéro de carte en partant de la droite
        for (int i = numCarte.length() - 1; i >= 0; i--) {
            int digit = Character.getNumericValue(numCarte.charAt(i));

            if (doubleDigit) {
                digit *= 2;
                if (digit > 9) {
                    digit -= 9;
                }
            }

            sum += digit;
            doubleDigit = !doubleDigit;
        }

        return sum % 10 == 0;
    }

}



