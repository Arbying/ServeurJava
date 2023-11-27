package database;

import BeansModele.ArticleBean;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ArticleDao {
    private DataBaseBean dataBaseBean;

    public ArticleDao(DataBaseBean dataBaseBean) {
        this.dataBaseBean = dataBaseBean;
    }

    public synchronized void ajoutArticle(ArticleBean article) throws SQLException {
        String sql = "INSERT INTO UNIX_FINAL (intitule, prix, stock, image) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = dataBaseBean.getConnection().prepareStatement(sql)) {
            statement.setString(1, article.getIntitule());
            statement.setFloat(2, article.getPrix());
            statement.setInt(3, article.getStock());
            statement.setString(4, article.getImage());
            statement.executeUpdate();
        }
    }

    public synchronized void modifArticle(ArticleBean article) throws SQLException {
        String sql = "UPDATE UNIX_FINAL SET intitule = ?, prix = ?, stock = ?, image = ? WHERE id = ?";
        try (PreparedStatement statement = dataBaseBean.getConnection().prepareStatement(sql)) {
            statement.setString(1, article.getIntitule());
            statement.setFloat(2, article.getPrix());
            statement.setInt(3, article.getStock());
            statement.setString(4, article.getImage());
            statement.setInt(5, article.getId());
            statement.executeUpdate();
        }
    }

    public synchronized void supprimeArticle(ArticleBean article) throws SQLException {
        String sql = "DELETE FROM UNIX_FINAL WHERE id = ?";
        try (PreparedStatement statement = dataBaseBean.getConnection().prepareStatement(sql)) {
            statement.setInt(1, article.getId());
            statement.executeUpdate();
        }
    }

    public synchronized ArticleBean selectArticle(int id) throws SQLException {
        String sql = "SELECT * FROM UNIX_FINAL WHERE id = ?";
        try (PreparedStatement statement = dataBaseBean.getConnection().prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new ArticleBean(
                            resultSet.getInt("id"),
                            resultSet.getString("intitule"),
                            resultSet.getFloat("prix"),
                            resultSet.getInt("stock"),
                            resultSet.getString("image")
                    );
                }
            }
        }
        return null; // ou gérer autrement si aucun article n'est trouvé
    }
}
