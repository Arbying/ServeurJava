package database;

import BeansModele.ClientBean;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientDao {
    private DataBaseBean dataBaseBean;

    public ClientDao(DataBaseBean dataBaseBean) {
        this.dataBaseBean = dataBaseBean;
    }

    public synchronized void ajoutClient(ClientBean client) throws SQLException {
        String sql = "INSERT INTO CLIENTS (nom, mdp) VALUES (?, ?)";
        try (PreparedStatement statement = dataBaseBean.getConnection().prepareStatement(sql)) {
            statement.setString(1, client.getNom());
            statement.setString(2, client.getMdp());
            statement.executeUpdate();
        }
    }

    public synchronized void supprimeClient(ClientBean client) throws SQLException {
        String sql = "DELETE FROM CLIENTS WHERE id = ?";
        try (PreparedStatement statement = dataBaseBean.getConnection().prepareStatement(sql)) {
            statement.setInt(1, client.getId());
            statement.executeUpdate();
        }
    }

    public synchronized void modifClient(ClientBean client) throws SQLException {
        String sql = "UPDATE CLIENTS SET nom = ?, mdp = ? WHERE id = ?";
        try (PreparedStatement statement = dataBaseBean.getConnection().prepareStatement(sql)) {
            statement.setString(1, client.getNom());
            statement.setString(2, client.getMdp());
            statement.setInt(3, client.getId());
            statement.executeUpdate();
        }
    }

    public synchronized ClientBean selectClient(int id) throws SQLException {
        String sql = "SELECT * FROM CLIENTS WHERE id = ?";
        try (PreparedStatement statement = dataBaseBean.getConnection().prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new ClientBean(
                            resultSet.getInt("id"),
                            resultSet.getString("nom"),
                            resultSet.getString("mdp")
                    );
                }
            }
        }
        return null; // ou gérer autrement si aucun client n'est trouvé
    }


}
