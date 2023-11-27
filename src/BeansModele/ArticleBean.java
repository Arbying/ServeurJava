package BeansModele;

public class ArticleBean {
    private int id;
    private String intitule;
    private float prix;
    private int stock;
    private String image;

    // Constructeur par défaut
    public ArticleBean() {
    }

    // Constructeur d'initialisation
    public ArticleBean(int id, String intitule, float prix, int stock, String image) {
        this.id = id;
        this.intitule = intitule;
        this.prix = prix;
        this.stock = stock;
        this.image = image;
    }

    // Getters et setters pour les propriétés

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    // Méthode toString pour afficher l'objet
    @Override
    public String toString() {
        return "ArticleBean{" +
                "id=" + id +
                ", intitule='" + intitule + '\'' +
                ", prix=" + prix +
                ", stock=" + stock +
                ", image='" + image + '\'' +
                '}';
    }

    // Méthode pour afficher les titres des colonnes
    public static String toStringTitres() {
        return String.format("%-5s %-20s %-10s %-10s", "ID", "Intitule", "Prix", "Stock");
    }

    // Méthode pour afficher les valeurs de l'objet sous forme de ligne
    public String toStringLigne() {
        return String.format("%-5d %-20s %-10.2f %-10d", id, intitule, prix, stock);
    }
}
