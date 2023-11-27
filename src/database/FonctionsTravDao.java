package database;

import BeansModele.FonctionsTravBean;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FonctionsTravDao {
    private DataBaseBean dataBaseBean;

    public FonctionsTravDao(DataBaseBean dataBaseBean) {
        this.dataBaseBean = dataBaseBean;
    }

    public synchronized void ajoutFonction(FonctionsTravBean fonction) throws SQLException {
        String sql = "INSERT INTO FonctionsTrav (Type, Fonction) VALUES (?, ?)";
        try (PreparedStatement statement = dataBaseBean.getConnection().prepareStatement(sql)) {
            statement.setInt(1, fonction.getType());
            statement.setString(2, fonction.getFonction());
            statement.executeUpdate();
        }
    }

    public synchronized void modifFonction(FonctionsTravBean fonction) throws SQLException {
        String sql = "UPDATE FonctionsTrav SET Fonction = ? WHERE Type = ?";
        try (PreparedStatement statement = dataBaseBean.getConnection().prepareStatement(sql)) {
            statement.setString(1, fonction.getFonction());
            statement.setInt(2, fonction.getType());
            statement.executeUpdate();
        }
    }

    public synchronized void supprimeFonction(FonctionsTravBean fonction) throws SQLException {
        String sql = "DELETE FROM FonctionsTrav WHERE Type = ?";
        try (PreparedStatement statement = dataBaseBean.getConnection().prepareStatement(sql)) {
            statement.setInt(1, fonction.getType());
            statement.executeUpdate();
        }
    }

    public synchronized FonctionsTravBean selectFonction(int type) throws SQLException {
        String sql = "SELECT * FROM FonctionsTrav WHERE Type = ?";
        try (PreparedStatement statement = dataBaseBean.getConnection().prepareStatement(sql)) {
            statement.setInt(1, type);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new FonctionsTravBean(
                            resultSet.getInt("Type"),
                            resultSet.getString("Fonction")
                    );
                }
            }
        }
        return null; // ou gérer autrement si aucune fonction n'est trouvée
    }
}
