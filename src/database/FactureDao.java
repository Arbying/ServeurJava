package database;

import BeansModele.ClientBean;
import BeansModele.FactureBean;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class FactureDao {
    private DataBaseBean dataBaseBean;
    private ClientDao clientDao;

    public FactureDao(DataBaseBean dataBaseBean, ClientDao clientDao) {
        this.dataBaseBean = dataBaseBean;
        this.clientDao = clientDao;
    }

    public synchronized void ajoutFacture(FactureBean facture) throws SQLException {
        String sql = "INSERT INTO FACTURE (IdClient, Montant, Paye, Date) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = dataBaseBean.getConnection().prepareStatement(sql)) {
            statement.setInt(1, facture.getClient().getId());
            statement.setFloat(2, facture.getMontant());
            statement.setBoolean(3, facture.isPaye());
            statement.setString(4, facture.getDate());
            statement.executeUpdate();
        }
    }

    public synchronized int paiementFait(int numFac) throws SQLException {
        // Vérifie si la facture existe
        int verifPaiement = VerifPaiement(numFac);
        if (verifPaiement == -1) {
            // Facture inexistante
            return -1;
        } else if (verifPaiement == 1) {
            // La facture est déjà payée
            return -1;
        }

        // Met à jour le paiement de la facture en mettant "oui" (payé)
        String sql = "UPDATE FACTURE SET Paye = ? WHERE id = ?";
        try (PreparedStatement statement = dataBaseBean.getConnection().prepareStatement(sql)) {
            statement.setBoolean(1, true);
            statement.setInt(2, numFac);
            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                // Mise à jour réussie, retourne 1
                return 1;
            }
        }

        // En cas d'échec de la mise à jour, retourne -1
        return -1;
    }

    public synchronized void modifFacture(FactureBean facture) throws SQLException {
        String sql = "UPDATE FACTURE SET IdClient = ?, Montant = ?, Paye = ?, Date = ? WHERE id = ?";
        try (PreparedStatement statement = dataBaseBean.getConnection().prepareStatement(sql)) {
            statement.setInt(1, facture.getClient().getId());
            statement.setFloat(2, facture.getMontant());
            statement.setBoolean(3, facture.isPaye());
            statement.setString(4, facture.getDate());
            statement.setInt(5, facture.getId());
            statement.executeUpdate();
        }
    }
    public synchronized int VerifPaiement(int numFac) throws SQLException {
        String sql = "SELECT Paye FROM FACTURE WHERE id = ?";
        try (PreparedStatement statement = dataBaseBean.getConnection().prepareStatement(sql)) {
            statement.setInt(1, numFac);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getBoolean("Paye") ? 1 : 0;
                }
            }
        }
        // Si la facture n'existe pas, retourne -1
        return -1;
    }

    public synchronized void supprimeFacture(FactureBean facture) throws SQLException {
        String sql = "DELETE FROM FACTURE WHERE id = ?";
        try (PreparedStatement statement = dataBaseBean.getConnection().prepareStatement(sql)) {
            statement.setInt(1, facture.getId());
            statement.executeUpdate();
        }
    }

    public synchronized FactureBean selectFacture(int id) throws SQLException {
        String sql = "SELECT * FROM FACTURE WHERE id = ?";
        try (PreparedStatement statement = dataBaseBean.getConnection().prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    ClientBean client = clientDao.selectClient(resultSet.getInt("IdClient"));
                    return new FactureBean(
                            resultSet.getInt("id"),
                            client,
                            resultSet.getFloat("Montant"),
                            resultSet.getBoolean("Paye"),
                            resultSet.getString("Date")
                    );
                }
            }
        }
        return null; // ou gérer autrement si aucune facture n'est trouvée
    }

    public synchronized Vector<FactureBean> mesFactures(int clientId) throws SQLException {
        Vector<FactureBean> factures = new Vector<>();
        String sql = "SELECT * FROM FACTURE WHERE IdClient = ?";
        try (PreparedStatement statement = dataBaseBean.getConnection().prepareStatement(sql)) {
            statement.setInt(1, clientId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    ClientBean client = clientDao.selectClient(resultSet.getInt("IdClient"));
                    FactureBean facture = new FactureBean(
                            resultSet.getInt("id"),
                            client,
                            resultSet.getFloat("Montant"),
                            resultSet.getBoolean("Paye"),
                            resultSet.getString("Date")
                    );
                    factures.add(facture);
                }
            }
        }
        return factures;
    }
}
