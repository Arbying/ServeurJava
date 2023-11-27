package BeansModele;

public class VenteBean {
    private FactureBean facture;
    private ArticleBean article;
    private int quantite;

    // Constructeur par défaut
    public VenteBean() {
    }

    // Constructeur d'initialisation
    public VenteBean(FactureBean facture, ArticleBean article, int quantite) {
        this.facture = facture;
        this.article = article;
        this.quantite = quantite;
    }

    // Getters et setters
    public FactureBean getFacture() {
        return facture;
    }

    public void setFacture(FactureBean facture) {
        this.facture = facture;
    }

    public ArticleBean getArticle() {
        return article;
    }

    public void setArticle(ArticleBean article) {
        this.article = article;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    // Méthode toString
    @Override
    public String toString() {
        return "VenteBean{" +
                "facture=" + (facture != null ? facture.toString() : "null") +
                ", article=" + (article != null ? article.toString() : "null") +
                ", quantite=" + quantite +
                '}';
    }


}
