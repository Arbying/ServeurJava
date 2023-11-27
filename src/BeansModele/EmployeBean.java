package BeansModele;

public class EmployeBean {
    private int idEmploye;
    private String nom;
    private String mdp;
    private FonctionsTravBean typeEmploye; // Objet représentant la fonction de l'employé
    private String actif;

    // Constructeur par défaut
    public EmployeBean() {
    }

    // Constructeur d'initialisation
    public EmployeBean(int idEmploye, String nom, String mdp, FonctionsTravBean typeEmploye, String actif) {
        this.idEmploye = idEmploye;
        this.nom = nom;
        this.mdp = mdp;
        this.typeEmploye = typeEmploye;
        this.actif = actif;
    }

    // Getters et setters
    public int getIdEmploye() {
        return idEmploye;
    }

    public void setIdEmploye(int idEmploye) {
        this.idEmploye = idEmploye;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public FonctionsTravBean getTypeEmploye() {
        return typeEmploye;
    }

    public void setTypeEmploye(FonctionsTravBean typeEmploye) {
        this.typeEmploye = typeEmploye;
    }

    public String getActif() {
        return actif;
    }

    public void setActif(String actif) {
        this.actif = actif;
    }

    // Méthode toString
    @Override
    public String toString() {
        return "EmployeBean{" +
                "idEmploye=" + idEmploye +
                ", nom='" + nom + '\'' +
                ", mdp='" + mdp + '\'' +
                ", typeEmploye=" + (typeEmploye != null ? typeEmploye.toString() : "null") +
                ", actif='" + actif + '\'' +
                '}';
    }

    // Méthodes pour l'affichage des titres et des lignes
    public static String toStringTitres() {
        return "ID    Nom             MDP      Type      Actif";
    }

    public String toStringLigne() {
        return String.format("%-5d %-15s %-8s %-10s %-5s",
                idEmploye, nom, mdp,
                (typeEmploye != null ? typeEmploye.getFonction() : "null"),
                actif);
    }
}
