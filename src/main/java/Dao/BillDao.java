package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import Connection.ConnectionFactory;
import Model.Bill;
public class BillDao {
    protected static final Logger LOGGER = Logger.getLogger(ClientDAO.class.getName());
    private static final String insertStatementString = "INSERT INTO bill (idComanda,ClientName,ProdusName,Pret)"
            + " VALUES (?,?,?,?)";
    private final static String selectAllStatementString = "SELECT * FROM bill";
    public static int insert(Bill bill) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        int insertedId = -1;
        try {
            Statement query = dbConnection.createStatement() ;

            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setInt(1,bill.idComanda());
            insertStatement.setString(2, bill.ClientName());
            insertStatement.setString(3, bill.ProdusName());
            insertStatement.setInt(4, bill.Pret());
            insertStatement.executeUpdate();

            query.executeUpdate(insertStatementString) ;

            ResultSet rs = insertStatement.getGeneratedKeys();
            if (rs.next()) {
                insertedId = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "BillDAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
        return insertedId;
    }
    public static ArrayList<Bill> selectAll() {
        Connection dbConnection = ConnectionFactory.getConnection();
        ArrayList<Bill> array = new ArrayList<>() ;

        try {
            Statement query = dbConnection.createStatement();
            ResultSet resultSet = query.executeQuery(selectAllStatementString);

            while(resultSet.next()) {
                int idComanda = resultSet.getInt("idComanda");
                String ClientName = resultSet.getString("ClientName");
                String ProdusName = resultSet.getString("ProdusName");
                int Pret = resultSet.getInt("Pret");
                array.add(new Bill(idComanda,ClientName,ProdusName,Pret)) ;
            }

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "BillDAO:selectAll " + e.getMessage());
        } finally {
            ConnectionFactory.close(dbConnection);
        }
        return array ;
    }
}
