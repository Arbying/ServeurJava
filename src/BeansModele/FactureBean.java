package BeansModele;

public class FactureBean {
    private int id;
    private ClientBean client; // Remplace idClient par un objet ClientBean
    private float montant;
    private boolean paye;
    private String date;

    // Constructeur par défaut
    public FactureBean() {
    }

    // Constructeur d'initialisation
    public FactureBean(int id, ClientBean client, float montant, boolean paye, String date) {
        this.id = id;
        this.client = client;
        this.montant = montant;
        this.paye = paye;
        this.date = date;
    }

    // Getters et setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ClientBean getClient() {
        return client;
    }

    public void setClient(ClientBean client) {
        this.client = client;
    }

    public float getMontant() {
        return montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    public boolean isPaye() {
        return paye;
    }

    public void setPaye(boolean paye) {
        this.paye = paye;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    // Méthode toString
    @Override
    public String toString() {
        return "FactureBean{" +
                "id=" + id +
                ", client=" + (client != null ? client.toString() : "null") +
                ", montant=" + montant +
                ", paye=" + paye +
                ", date='" + date + '\'' +
                '}';
    }

    // Vous pouvez ajouter des méthodes supplémentaires si nécessaire
}
