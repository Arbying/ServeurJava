package database;

import BeansModele.EmployeBean;
import BeansModele.FonctionsTravBean;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeDao {
    private DataBaseBean dataBaseBean;
    private FonctionsTravDao fonctionsTravDao;

    public EmployeDao(DataBaseBean dataBaseBean, FonctionsTravDao fonctionsTravDao) {
        this.dataBaseBean = dataBaseBean;
        this.fonctionsTravDao = fonctionsTravDao;
    }

    public synchronized void ajoutEmploye(EmployeBean employe) throws SQLException {
        String sql = "INSERT INTO Employes (Nom, MDP, Type, Actif) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = dataBaseBean.getConnection().prepareStatement(sql)) {
            statement.setString(1, employe.getNom());
            statement.setString(2, employe.getMdp());
            statement.setInt(3, employe.getTypeEmploye().getType());
            statement.setString(4, employe.getActif());
            statement.executeUpdate();
        }
    }

    public synchronized void modifEmploye(EmployeBean employe) throws SQLException {
        String sql = "UPDATE Employes SET Nom = ?, MDP = ?, Type = ?, Actif = ? WHERE IdEmploye = ?";
        try (PreparedStatement statement = dataBaseBean.getConnection().prepareStatement(sql)) {
            statement.setString(1, employe.getNom());
            statement.setString(2, employe.getMdp());
            statement.setInt(3, employe.getTypeEmploye().getType());
            statement.setString(4, employe.getActif());
            statement.setInt(5, employe.getIdEmploye());
            statement.executeUpdate();
        }
    }

    public synchronized void supprimeEmploye(EmployeBean employe) throws SQLException {
        String sql = "DELETE FROM Employes WHERE IdEmploye = ?";
        try (PreparedStatement statement = dataBaseBean.getConnection().prepareStatement(sql)) {
            statement.setInt(1, employe.getIdEmploye());
            statement.executeUpdate();
        }
    }

    public synchronized EmployeBean selectEmploye(int idEmploye) throws SQLException {
        String sql = "SELECT * FROM Employes WHERE IdEmploye = ?";
        try (PreparedStatement statement = dataBaseBean.getConnection().prepareStatement(sql)) {
            statement.setInt(1, idEmploye);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    FonctionsTravBean fonction = fonctionsTravDao.selectFonction(resultSet.getInt("Type"));
                    return new EmployeBean(
                            resultSet.getInt("IdEmploye"),
                            resultSet.getString("Nom"),
                            resultSet.getString("MDP"),
                            fonction,
                            resultSet.getString("Actif")
                    );
                }
            }
        }
        return null; // ou gérer autrement si aucun employé n'est trouvé
    }

    public synchronized EmployeBean rechercheEmploye(String login, String mdp) throws SQLException {
        String sql = "SELECT * FROM Employes WHERE Nom = ? AND MDP = ?";
        try (PreparedStatement statement = dataBaseBean.getConnection().prepareStatement(sql)) {
            statement.setString(1, login);
            statement.setString(2, mdp);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    FonctionsTravBean fonction = fonctionsTravDao.selectFonction(resultSet.getInt("Type"));
                    return new EmployeBean(
                            resultSet.getInt("IdEmploye"),
                            resultSet.getString("Nom"),
                            resultSet.getString("MDP"),
                            fonction,
                            resultSet.getString("Actif")
                    );
                }
            }
        }
        return null; // ou gérer autrement si aucun employé correspondant n'est trouvé
    }

}
