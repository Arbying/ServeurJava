package BeansModele;

public class ClientBean {
    private int id;
    private String nom;
    private String mdp;

    public ClientBean() {
        // Constructeur par défaut
    }

    public ClientBean(int id, String nom, String mdp) {
        this.id = id;
        this.nom = nom;
        this.mdp = mdp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "ClientBean{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", mdp='" + mdp + '\'' +
                '}';
    }

    public static String toStringTitres() {
        return "ID    Nom                  ";
    }

    public String toStringLigne() {
        return String.format("%-5d %-20s", id, nom);
    }
}
