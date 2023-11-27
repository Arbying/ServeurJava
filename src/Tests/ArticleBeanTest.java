package Tests;

import BeansModele.ArticleBean;

public class ArticleBeanTest {
    public static void main(String[] args) {
        // Création de l'article
        ArticleBean article = new ArticleBean(1, "Carottes", 2.16f, 9, "carottes.jpg");

        // Affichage de l'article initial
        System.out.println("Article initial :");
        System.out.println(article);

        // Utilisation des setters pour modifier les valeurs de l'article
        article.setId(2);
        article.setIntitule("Pommes");
        article.setPrix(2.50f);
        article.setStock(15);
        article.setImage("pommes.jpg");

        // Affichage des attributs avec les getters
        System.out.println("Article après modification :");
        System.out.println("ID : " + article.getId());
        System.out.println("Intitulé : " + article.getIntitule());
        System.out.println("Prix : " + article.getPrix());
        System.out.println("Stock : " + article.getStock());
        System.out.println("Image : " + article.getImage());

        // Affichage des méthodes toString, toStringTitres et toStringLigne
        System.out.println("Méthode toString :");
        System.out.println(article.toString());

        System.out.println("Méthode toStringTitres :");
        System.out.println(ArticleBean.toStringTitres());

        System.out.println("Méthode toStringLigne :");
        System.out.println(article.toStringLigne());
    }
}
