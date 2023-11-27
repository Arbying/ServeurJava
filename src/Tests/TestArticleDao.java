package Tests;

import BeansModele.ArticleBean;
import database.ArticleDao;
import database.DataBaseBean;

public class TestArticleDao {

    public static void main(String[] args) {
        try {
            // Initialisation du DataBaseBean
            DataBaseBean dataBaseBean = new DataBaseBean("PourStudent", "Student", "PassStudent1_");
            ArticleDao articleDao = new ArticleDao(dataBaseBean);

            // Création d'un nouvel article
            ArticleBean newArticle = new ArticleBean(-1, "Pommes", 1.50f, 100, "pommes.jpg");
            articleDao.ajoutArticle(newArticle);
            System.out.println("Article ajouté avec succès.");

            // Mise à jour de l'article
            newArticle.setIntitule("Pommes Golden");
            newArticle.setPrix(1.75f);
            articleDao.modifArticle(newArticle);
            System.out.println("Article modifié avec succès.");

            // Récupération de l'article
            ArticleBean retrievedArticle = articleDao.selectArticle(newArticle.getId());
            if (retrievedArticle != null) {
                System.out.println("Article récupéré : " + retrievedArticle);
            }

            // Suppression de l'article
            articleDao.supprimeArticle(newArticle);
            System.out.println("Article supprimé avec succès.");

            // Fermeture de la connexion à la base de données
            dataBaseBean.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
