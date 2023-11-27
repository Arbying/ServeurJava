package BeansModele;

public class FonctionsTravBean {
    private int type;
    private String fonction;

    // Constructeur par défaut
    public FonctionsTravBean() {
    }

    // Constructeur d'initialisation
    public FonctionsTravBean(int type, String fonction) {
        this.type = type;
        this.fonction = fonction;
    }

    // Getters et setters
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getFonction() {
        return fonction;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }

    // Méthode toString pour afficher l'objet
    @Override
    public String toString() {
        return "FonctionsTravBean{" +
                "type=" + type +
                ", fonction='" + fonction + '\'' +
                '}';
    }

    // Méthode pour afficher les titres des colonnes
    public static String toStringTitres() {
        return "Type    Fonction";
    }

    // Méthode pour afficher les valeurs de l'objet sous forme de ligne
    public String toStringLigne() {
        return String.format("%-5d %-20s", type, fonction);
    }
}
